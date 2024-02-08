package duckservice.duckservice.model;

public class RubberDuck extends Duck{
    public RubberDuck(int id, DuckType type) {
        super(id, type);
    }
    @Override
    public void display() {
        System.out.println("It looks like a rubber duck");
    }
}
