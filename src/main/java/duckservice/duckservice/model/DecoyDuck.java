package duckservice.duckservice.model;

public class DecoyDuck extends Duck{
    public DecoyDuck(int id, DuckType type) {
        super(id, type);
    }
    @Override
    public void display() {
        System.out.println("It looks like a Decoy duck");
    }
}
