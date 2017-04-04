
import math, sys
from subprocess import call

# configure these values
max_acceleration = 12.5 # units per second
max_velocity = 17.0 # units per second
ramp_time = max_velocity / max_acceleration # in seconds
timestep = 0.02 # in seconds

# [(position, velocity, dt)]
points_right = []
points_left = []

def save_to_csv(right, left):
    with open(right, 'w') as r, open(left, 'w') as l:
        for (p, v, dt) in points_right:
            r.write(str(p) + ',' + str(v) + ',' + str(math.floor(dt*1000)) + '\n')
        for (p, v, dt) in points_left:
            l.write(str(p) + ',' + str(v) + ',' + str(math.floor(dt*1000)) + '\n')

def calculate_t2(x, A, t1):
    return (x - (A*t1*t1)) / (A * t1)

def calculate_t1_s(x, A):
    return math.sqrt(x / A)

def trajectory(abs_dist, direction, start_position):
    points = []
    x = abs_dist
    A = max_acceleration
    t1 = ramp_time
    t2 = calculate_t2(x, A, t1)
    dt = timestep
    if t2 > 0:
        for i in range(0, math.ceil(t1/dt)):
            t = i*dt
            position = (1/2) * A * math.pow(t, 2)
            velocity = A * t
            points.append((direction*position+start_position, direction*velocity, dt))
        for i in range(0, math.ceil(t2/dt)):
            t = i*dt
            position = ((1/2) * A * math.pow(t1, 2)) + (A * t1 * t)
            velocity = A * t1
            points.append((direction*position+start_position, direction*velocity, dt))
        for i in range(0, math.ceil(t1/dt)):
            t = i*dt
            position = ((1/2) * A * math.pow(t1, 2)) + (A * t1 * t2) + (A * t1 * t) - ((1/2) * A * math.pow(t, 2))
            velocity = (A * t1) - (A * t)
            points.append((direction*position+start_position, direction*velocity, dt))
    else:
        t1 = calculate_t1_s(x, A)
        for i in range(0, int(math.ceil(t1/dt))):
            t = i*dt
            position = (1/2) * A * math.pow(t, 2)
            velocity = A * t
            points.append((direction*position+start_position, direction*velocity, dt))
        for i in range(0, int(math.ceil(t1/dt))):
            t = i*dt
            position = ((1/2) * A * math.pow(t1, 2)) + (A * t1 * t) - ((1/2) * A * math.pow(t, 2))
            velocity = (A * t1) - (A * t)
            points.append((direction*position+start_position, direction*velocity, dt))
    return points

def move(dist):
    sign = 1
    if dist < 0:
        dist = -dist
        sign = -1
    xr_0 = 0
    xl_0 = 0
    if len(points_right) > 0:
        xr_0 = points_right[-1][0]
    if len(points_left) > 0:
        xl_0 = points_left[-1][0]
    points_right.extend(trajectory(dist, sign, xr_0))
    points_left.extend(trajectory(dist, -sign, xl_0))

def turn(angle):
    sign = 1
    if angle < 0:
        angle = -angle
        sign = -1
    xr_0 = 0
    xl_0 = 0
    if len(points_right) > 0:
        xr_0 = points_right[-1][0]
    if len(points_left) > 0:
        xl_0 = points_left[-1][0]
    points_right.extend(trajectory(angle, sign, xr_0))
    points_left.extend(trajectory(angle, sign, xl_0))

def wait(time):
    xr_0 = 0
    xl_0 = 0
    if len(points_right) > 0:
        xr_0 = points_right[-1][0]
    if len(points_left) > 0:
        xl_0 = points_left[-1][0]
    points_right.extend([(xr_0, 0, timestep)] * int(time / timestep))
    points_left.extend([(xl_0, 0, timestep)] * int(time / timestep))

convert_dist = 5.0 / 24.25
convert_angle = 4.5 / 90.0

base = '/Users/jake/Development/sandbox-workspace/alternative-robot/profiles/'

def traj_center():
    move(-75.0*convert_dist)
    save_to_csv(base + 'gear_center_right.csv', base + 'gear_center_left.csv')
    clear_points()

def traj_left():
    move(-65.0*convert_dist)
    turn(-60.0*convert_angle)
    move(-68.0*convert_dist)
    save_to_csv(base + 'gear_left_right.csv', base + 'gear_left_left.csv')
    clear_points()

def traj_right():
    move(-65.0*convert_dist)
    turn(+60.0*convert_angle)
    move(-68.0*convert_dist)
    save_to_csv(base + 'gear_right_right.csv', base + 'gear_right_left.csv')
    clear_points()

def traj_blue_center_shoot():
    move(-70.8*convert_dist)
    move(20*convert_dist)
    turn((-66.1-8)*convert_angle)
    move(134.6*convert_dist)
    save_to_csv(base + 'gear_blue_center_shoot_right.csv', base + 'gear_blue_center_shoot_left.csv')
    clear_points()

def traj_blue_side_shoot():
    move((-67.6-4)*convert_dist)
    turn(-60.0*convert_angle)
    move(-65.3*convert_dist)
    move(20*convert_dist)
    turn((20.9-3.2)*convert_angle)
    move((98.2+10)*convert_dist)
    save_to_csv(base + 'gear_blue_side_shoot_right.csv', base + 'gear_blue_side_shoot_left.csv')
    clear_points()

def traj_red_center_shoot():
    move(-70.8*convert_dist)
    move(20*convert_dist)
    turn((66.1+8)*convert_angle)
    move(134.6*convert_dist)
    save_to_csv(base + 'gear_red_center_shoot_right.csv', base + 'gear_red_center_shoot_left.csv')
    clear_points()

def traj_red_side_shoot():
    move((-67.6-4)*convert_dist)
    turn(60.0*convert_angle)
    move(-65.3*convert_dist)
    move(20*convert_dist)
    turn((-20.9+3.2)*convert_angle)
    move((98.2+10)*convert_dist)
    save_to_csv(base + 'gear_red_side_shoot_right.csv', base + 'gear_red_side_shoot_left.csv')
    clear_points()

def traj_deploy():
    call(['scp ' + base + '* lvuser@roborio-1540-frc.local:/home/lvuser/profiles'], shell=True)

def traj_clean():
    call(['rm ' + base + '*'], shell=True)

def clear_points():
    points_left = []
    points_right = []

path = sys.argv[1]

globals()['traj_' + path]()
