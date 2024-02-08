package duckservice.duckservice.model;

public class MuteQuack implements QuackBehavior{
    @Override
    public void quack() {
        System.out.println("CAN't Quack!");
    }
}
