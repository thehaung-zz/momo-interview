# Momo Interview (SOF & Open Platform)
Explain and BA Case Study Provided, and some stuff

## Actor
- Guest
- Machine

## Related Topic
- Dagger2
  + Have a lot concept related to Dependency Injection
  + Have some annotations like: @Component, @Module, @Binds, @Inject ...
- Dependency Injection

## Feature
- Insert Money into Machine
- Select Product
- Confirm Product Selected
- End Session
- Cronjob (Crontab) scheduled end of day for caculate winning promotion percent for tommorow if today is not reached

# Workflow
- Include .drawio file for design

## Flowchart
![Flowchart](https://github.com/thehaung/momo-interview/blob/master/Flowchart.png)

## Class Diagram
![Class Diagram](https://github.com/thehaung/momo-interview/blob/master/ClassDiagram.png)

## Brainstorm step by step
- Guest insert money (if money type not support, refund)
- Guest pick product
  + If counter to check lucky is equal to config, check lucky for each product qualified with condition
  + If guest got lucky and promotion budget equals or greater than price of product, add one for free
- Guest confirm order list
- Guest end session and get excess cash back
 
## Run Program
- This is not fully implementation Dagger2 process
- Prefer using IntelliJ IDEA for run this CommandLineMachine.java (have generated source configured)
- Type command in CommandConstant.java has been declared