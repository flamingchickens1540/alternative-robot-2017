notes: t = time elapsed since start of piecewise segment (when jerk changes)
see https://en.wikipedia.org/wiki/Jerk_(physics) at the bottom to see the graph

t1
- j1 = max_jerk

- a1 = integral j1 dt over t
     = max_jerk * t

- v1 = integral a1 dt over t
     = (1/2) * max_jerk * t**2

- x1 = integral v1 dt over t
     = (1/6) * max_jerk * t**3

t2
- j2 = 0

- a2 = integral j1 dt over t1 + 0
     = max_jerk * t1

- v2 = integral a1 dt over t1 + integral a2 dt over t
     = (1/2) * max_jerk * t1**2
     + (max_jerk * t1) * t

- x2 = integral v1 dt over t1 + integral v2 dt over t
     = (1/6) * max_jerk * t**3)
     + ((1/2) * max_jerk * t1**2) * t + (1/2) * (max_jerk * t1) * t**2

t3
- j3 = -max_jerk

- a3 = integral j1 dt over t1 + 0                      + integral j3 dt over t
     = max_jerk * t1
     + -max_jerk * t

- v3 = integral a1 dt over t1 + integral a2 dt over t2 + integral a3 dt over t
     = (1/2) * max_jerk * t1**2
     + (max_jerk * t1) * t2
     + (max_jerk * t1) * t + (1/2) * -max_jerk * t**2

- x3 = integral v1 dt over t1 + integral v2 dt over t2 + integral v3 dt over t
     = (1/6) * max_jerk * t**3)
     + ((1/2) * max_jerk * t1**2) * t + (1/2) * (max_jerk * t1) * t**2
     + (1/2) * max_jerk * t1**2) * t + (max_jerk * t1) * t2 * t + (1/2) * (max_jerk * t1) * t**2 + (1/6) * -max_jerk * t**3

t4
- j4 = 0

- a4 = integral j1 dt over t1 + 0                      + integral j3 dt over t3 + 0
     = max_jerk * t1
     + -max_jerk * t2

- v4 = integral a1 dt over t1 + integral a2 dt over t2 + integral a3 dt over t3 + integral a4 dt over t
     = (1/2) * max_jerk * t1**2
     + (max_jerk * t1) * t2
     + (max_jerk * t1) * t3 + (1/2) * -max_jerk * t3**2
     + (max_jerk * t1) * t + -max_jerk * t2 * t

- x4 = integral v1 dt over t1 + integral v2 dt over t2 + integral v3 dt over t3 + Integral v4 dt over t
     = (1/6) * max_jerk * t**3)
     + ((1/2) * max_jerk * t1**2) * t + (1/2) * (max_jerk * t1) * t**2
     + (1/2) * max_jerk * t1**2) * t + (max_jerk * t1) * t2 * t3 + (1/2) * (max_jerk * t1) * t3**2 + (1/6) * -max_jerk * t3**3
     + (1/2) * max_jerk * t1 * t**2 + (1/2) * -max_jerk * t2 * t**2

t5
- j5 = -max_jerk

- a5 = integral j1 dt over t1 + 0                      + integral j3 dt over t3 + 0                      + integral j5 dt over t

- v5 = integral a1 dt over t1 + integral a2 dt over t2 + integral a3 dt over t3 + integral a4 dt over t4 + integral a5 dt over t

- x5 = integral v1 dt over t1 + integral v2 dt over t2 + integral v3 dt over t3 + Integral v4 dt over t4 + integral v5 dt over t

t6
- j6 = 0
- a6 = integral j1 dt over t1 + 0                      + integral j3 dt over t3 + 0                      + integral j5 dt over t5 + 0
- v6 = integral a1 dt over t1 + integral a2 dt over t2 + integral a3 dt over t3 + integral a4 dt over t4 + integral a5 dt over t5 + integral a6 dt over t
- x6 = integral v1 dt over t1 + integral v2 dt over t2 + integral v3 dt over t3 + Integral v4 dt over t4 + integral v5 dt over t5 + integral v6 dt over t

t7
- j7 = max_jerk
- a7 = integral j1 dt over t1 + 0                      + integral j3 dt over t3 + 0                      + integral j5 dt over t5 + 0                      + integral j7 dt over t
- v7 = integral a1 dt over t1 + integral a2 dt over t2 + integral a3 dt over t3 + integral a4 dt over t4 + integral a5 dt over t5 + integral a6 dt over t6 + integral a7 dt over t
- x7 = integral v1 dt over t1 + integral v2 dt over t2 + integral v3 dt over t3 + Integral v4 dt over t4 + integral v5 dt over t5 + integral v6 dt over t6 + integral v7 dt over t

x = integral v1 dt over t1 + integral v2 dt over t2 + integral v3 dt over t3 + Integral v4 dt over t4 + integral v5 dt over t5 + integral v6 dt over t6 + integral v7 dt over t7
