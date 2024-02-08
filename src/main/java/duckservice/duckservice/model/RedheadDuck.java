package duckservice.duckservice.model;

public class RedheadDuck extends Duck{
    public RedheadDuck(int id, DuckType type) {
        super(id, type);
    }

    @Override
    public void display() {
        System.out.println("It looks like a redhead");
    }
}
