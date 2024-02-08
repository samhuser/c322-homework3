package duckservice.duckservice.model;

public abstract class Duck {
    FlyBehavior flyBehavior;
    QuackBehavior quackBehavior;

    private DuckType type;

    private int id;

    public Duck(int id, DuckType type) {
        this.id = id;
        this.type = type;
    }

    public void swim(){
        System.out.println("Paddle");
    }

    public abstract void display();

    public void setFlyBehavior(FlyBehavior flyBehavior) {
        this.flyBehavior = flyBehavior;
    }
    public void setQuackBehavior(QuackBehavior quackBehavior) {
        this.quackBehavior = quackBehavior;
    }

    public void performFly(){
        flyBehavior.fly();
    }
    public void performQuack(){
        quackBehavior.quack();
    }

    public int getId() {
        return id;
    }
    public DuckType getType(){
        return type;
    }

    @Override
    public String toString() {
        return id + "," + type;
    }
}
