
import math

# configure these values
max_acceleration = 4.0 # units per second
max_velocity = 8.0 # units per second
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

convert_dist = 5.0 / 24.25
convert_angle = 4.5 / 90.0

# center
# move(-75.0*convert_dist)

# left
move(-65.0*convert_dist)
turn(-60.0*convert_angle)
move(-68.0*convert_dist)

# right
# move(-65.0*convert_dist)
# turn(+60.0*convert_angle)
# move(-68.0*convert_dist)

base = '/Users/jake/Development/sandbox-workspace/alternative-robot/profiles/'
save_to_csv(base + 'right.csv', base + 'left.csv')
