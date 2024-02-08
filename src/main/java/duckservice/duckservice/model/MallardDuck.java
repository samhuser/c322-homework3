package duckservice.duckservice.model;

public class MallardDuck extends Duck{

    public MallardDuck(int id, DuckType type) {
        super(id, type);
    }

    @Override
    public void display() {
        System.out.println("It looks like a mallard");
    }
}
