package org.team1540.robot2017.motion;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.Notifier;

public class MotionProfile {
    private CANTalon.MotionProfileStatus _status = new CANTalon.MotionProfileStatus();
    private CANTalon _talon;
    private int _state;
    private int _loopTimeout = -1;
    private boolean _bStart = false;
    private CANTalon.SetValueMotionProfile _setValue = CANTalon.SetValueMotionProfile.Disable;
    private double[][] _profile;
    private int _totalCnt;
    private int _loaded;
    private static final int kMinPointsInTalon = 5;
    private static final int kNumLoopsTimeout = 10;
    private boolean _isStarting = true;
    
    public MotionProfile(CANTalon talon, double[][] profile, int totalCnt) {
        _talon = talon;
        _profile = profile;
        _totalCnt = totalCnt;
        _loaded = 0;
        
        /*
         * since our MP is 10ms per point, set the control frame rate and the
         * notifer to half that
         */
        _talon.changeMotionControlFramePeriod(5);
    }
    
    public void reset() {
        /*
         * Let's clear the buffer just in case user decided to disable in the
         * middle of an MP, and now we have the second half of a profile just
         * sitting in memory.
         */
        _talon.clearMotionProfileTrajectories();
        /* When we do re-enter motionProfile control mode, stay disabled. */
        _setValue = CANTalon.SetValueMotionProfile.Disable;
        /* When we do start running our state machine start at the beginning. */
        _state = 0;
        _loopTimeout = -1;
        _loaded = 0;
        /*
         * If application wanted to start an MP before, ignore and wait for next
         * button press
         */
        _bStart = false;
        _isStarting = true;
    }
    
    public boolean control() {
        /* Get the motion profile status every loop */
        _talon.getMotionProfileStatus(_status);

        /*
         * track time, this is rudimentary but that's okay, we just want to make
         * sure things never get stuck.
         */
        if (_loopTimeout < 0) {
            /* do nothing, timeout is disabled */
        } else {
            /* our timeout is nonzero */
            if (_loopTimeout == 0) {
            } else {
                --_loopTimeout;
            }
        }

        /* first check if we are in MP mode */
        if (_talon.getControlMode() != TalonControlMode.MotionProfile) {
            /*
             * we are not in MP mode. We are probably driving the robot around
             * using gamepads or some other mode.
             */
            _state = 0;
            _loopTimeout = -1;
        } else {
            /*
             * we are in MP control mode. That means: starting Mps, checking Mp
             * progress, and possibly interrupting MPs if thats what you want to
             * do.
             */
            switch (_state) {
                case 0: /* wait for application to tell us to start an MP */
                    if (_bStart) {
                        _bStart = false;
    
                        _setValue = CANTalon.SetValueMotionProfile.Disable;
                        _loaded += startFilling(_profile, _totalCnt);
                        /*
                         * MP is being sent to CAN bus, wait a small amount of time
                         */
                        _state = 1;
                        _loopTimeout = kNumLoopsTimeout;
                    }
                    break;
                case 1: /*
                         * wait for MP to stream to Talon, really just the first few
                         * points
                         */
                    /* do we have a minimum numberof points in Talon */
                    if (_status.btmBufferCnt > kMinPointsInTalon) {
                        /* start (once) the motion profile */
                        _setValue = CANTalon.SetValueMotionProfile.Enable;
                        /* MP will start once the control frame gets scheduled */
                        _state = 2;
                        _loopTimeout = kNumLoopsTimeout;
                    }
                    break;
                case 2: /* check the status of the MP */
                    /*
                     * if talon is reporting things are good, keep adding to our
                     * timeout. Really this is so that you can unplug your talon in
                     * the middle of an MP and react to it.
                     */
                    if (_status.isUnderrun == false) {
                        _loopTimeout = kNumLoopsTimeout;
                    }
                    
                    if (_loaded < _totalCnt) {
                        continueFilling(_profile, _totalCnt, _loaded);
                    }
                    
                    /*
                     * If we are executing an MP and the MP finished, start loading
                     * another. We will go into hold state so robot servo's
                     * position.
                     */
                    if (_status.activePointValid && _status.activePoint.isLastPoint) {
                        /*
                         * because we set the last point's isLast to true, we will
                         * get here when the MP is done
                         */
                        _setValue = CANTalon.SetValueMotionProfile.Hold;
                        _state = 0;
                        _loopTimeout = -1;
                        return true;
                    }
                    
                    break;
            }
        }
        
        return false;
    }
    
    private int startFilling(double[][] profile, int totalCnt) {

        /* create an empty point */
        CANTalon.TrajectoryPoint point = new CANTalon.TrajectoryPoint();

        /* did we get an underrun condition since last time we checked ? */
        if (_status.hasUnderrun) {
            /*
             * clear the error. This flag does not auto clear, this way 
             * we never miss logging it.
             */
            _talon.clearMotionProfileHasUnderrun();
        }
        /*
         * just in case we are interrupting another MP and there is still buffer
         * points in memory, clear it.
         */
        _talon.clearMotionProfileTrajectories();

        /* This is fast since it's just into our TOP buffer */
        for (int i = 0; i < totalCnt; ++i) {
            if (i == _status.topBufferRem) {
                return i;
            }
            
            /* for each point, fill our structure and pass it to API */
            point.position = profile[i][0];
            point.velocity = profile[i][1];
            point.timeDurMs = (int) profile[i][2];
            point.profileSlotSelect = 0; /* which set of gains would you like to use? */
            point.velocityOnly = false; /* set true to not do any position
                                         * servo, just velocity feedforward
                                         */
            point.zeroPos = false;
            if (i == 0)
                point.zeroPos = true; /* set this to true on the first point */

            point.isLastPoint = false;
            if ((i + 1) == totalCnt)
                point.isLastPoint = true; /* set this to true on the last point  */

            _talon.pushMotionProfileTrajectory(point);
        }
        
        return totalCnt;
    }
    
    private int continueFilling(double[][] profile, int totalCnt, int current) {
        /* create an empty point */
        CANTalon.TrajectoryPoint point = new CANTalon.TrajectoryPoint();

        /* This is fast since it's just into our TOP buffer */
        for (int i = current; i < totalCnt; ++i) {
            if (i == _status.topBufferRem) {
                return i-current;
            }
            
            /* for each point, fill our structure and pass it to API */
            point.position = profile[i][0];
            point.velocity = profile[i][1];
            point.timeDurMs = (int) profile[i][2];
            point.profileSlotSelect = 0; /* which set of gains would you like to use? */
            point.velocityOnly = false; /* set true to not do any position
                                         * servo, just velocity feedforward
                                         */
            point.zeroPos = false;
            if (i == 0)
                point.zeroPos = true; /* set this to true on the first point */

            point.isLastPoint = false;
            if ((i + 1) == totalCnt)
                point.isLastPoint = true; /* set this to true on the last point  */

            _talon.pushMotionProfileTrajectory(point);
        }
        
        return totalCnt-current;
    }

    public void startMotionProfile() {
        _bStart = true;
    }

    public CANTalon.SetValueMotionProfile getSetValue() {
        return _setValue;
    }
}
