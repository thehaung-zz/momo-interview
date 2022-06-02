# Momo Interview (SOF & Open Platform)
Explain and BA Case Study Provided, and some stuff

## Actor
- Guest
- Machine

## Related Topic
- Dagger2
  + Have a lot concept related to Dependency Injection
  + Have some annotations like: @Component, @Module, @Binds, @Inject ...
  + What is annotations do
  + @Component: tells Dagger to implement an interface or abstract class that creates and returns one or more application objects.
  + @Inject on a constructor tells Dagger how to instantiate that class. We’ll see more shortly.
  + @Binds methods are one way to tell Dagger how to construct an instance. They are abstract methods on modules that associate one type that Dagger already knows how to construct (the method’s parameter) with a type that Dagger doesn’t yet know how to construct (the method’s return type).
  + @Modules are classes or interfaces that act as collections of instructions for Dagger on how to construct dependencies. They’re called modules because they are modular: you can mix and match modules in different applications and contexts.
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
  + If counter is enough and check lucky is equal to config, check lucky for each product qualified with condition
  + If guest got lucky and promotion budget equals or greater than price of product, add one for free
- Guest confirm order list
- Guest end session and get excess cash back
 
## Run Program
- This is not fully implementation Dagger2
- Prefer using IntelliJ IDEA for run this CommandLineMachine.java (have generated source configured)
- Type command in CommandConstant.java has been declared