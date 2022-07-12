# Task
 
Implement service that collects user experiences and calculates new user levels.

## Domain

System has configuration that consists of user experience that corresponds to level:
Example:

| Level | Minimum Experience |
| ----- | --------------- |
|   2   |             100 |
|   3   |             500 |
|   4   |            1000 |
|  ...  |            ...  |
|  10   |          500000 |


## Functional requirements

Service has to support the following functions:

- Add user experience. 
- Calculate new level based on current experience. 
- Send LevelChangedEvent to external systems when user has collected enough experience for transition to next level. 
    (Notice: If user got enough experience to jump from e.g. level 1 to level 3, two level up events should be sent, for each level up: 1 -> 2, 2 -> 3)

   Event should be sent only once per transition.

- Allow several external systems to subscribe to service to receive events 
    Once a system subscribes, it will receive all subsequent events. It should not receive events generated before it subscribed.

## Technical aspects

- Configuration is supplied at runtime via an instance of *ConfigurationProvider* interface. Implementing this interface is not required.

- Experience delta added for each user is expected to be non-negative value. Exceptional logic could be designed at will, but not required. 
 
- State of each user's level and experience should be stored in memory.

- Requests for adding experience will be called in a multithreaded environment.

## Solution requirements

You can implement *com.playtika.level.operations.DefaultUserLevelOperations* or design own component.

You can use existing classes and change them at will, provided that all requirements above are still met.

We are going to rate 3 categories:
1. Design
2. Unit testing technique
3. Concurrency aspects

*UserLevelOperationsConcurrentTest* already contains some end-to-end scenarios made to verify your code. 
Please, don't waste time on investigating them. 
However, a correct implementation is expected to pass all of them successfully.