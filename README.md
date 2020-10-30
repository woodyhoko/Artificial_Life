# Artificial_Life
A number of automated evolving creatures trying to survive in a limited resource environment.

## Simulation setup
Every individual have a different Neural Network brain, reacting to the environment according to the response of the blackbox.  
The environment is a playground with randomly distributed grass.  
Every block of grass have it's own unique recovery rate, and creatures can eat grass to sustain its basic consumption.  
Creatures can also consume others as an energy income, but when collision happens, the one with a higher energy will be the predator.  

## Mechanism
Each creature have an inherited brain from well-performing parents(the survivors), and owns the right to have offspring if better than others.  
By collecting information from the three identical antennas, if the brain(Neural Network) is well developed, it can then be able to analyze and determine to:  
- avoid enemy
- chase enemy
- stay longer if food exists
- avoid desert area(area with no food)

## Demo
https://youtu.be/_E16iH62hlA
[![missing gif](https://github.com/woodyhoko/Artificial_Life/blob/main/AL_C.gif)](https://youtu.be/_E16iH62hlA)

https://youtu.be/XocJwLX-YtI
[![missing gif](https://github.com/woodyhoko/Artificial_Life/blob/main/AL_S.gif)](https://youtu.be/XocJwLX-YtI)
