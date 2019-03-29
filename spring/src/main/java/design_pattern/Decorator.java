package design_pattern;

/**
 * @author gavin
 * @date 2019/3/18 11:54
 */
public class Decorator {

    public static void main(String[] args) {
        Beverage beverage = new Espresso();
        System.out.println(beverage.getDescription() + " $ " + beverage.cost());

        Beverage beverage1 = new Espresso();
        beverage1 = new Mocha(beverage1);
        System.out.println(beverage1.getDescription() + " $ " + beverage1.cost());
    }
}

abstract class Beverage {
    String description = "Unknown Beverage";
    public String getDescription() {
        return description;
    }
    abstract double cost();
}

abstract class CondimentDecorator extends Beverage {
    @Override
    public abstract String getDescription();
}

class Espresso extends Beverage {
    public Espresso() {
        description = "Espresso";
    }

    @Override
    public double cost() {
        return 1.99;
    }
}

class HouseBlend extends Beverage {
    public HouseBlend() {
        description = "HouseBlend";
    }
    public double cost() {
        return 0.89;
    }
}

class Mocha extends CondimentDecorator {
    Beverage beverage;

    public Mocha(Beverage beverage) {
        this.beverage = beverage;
    }

    public String getDescription() {
        return beverage.getDescription() + ", Mocha";
    }

    public double cost() {
        return 0.20 + beverage.cost();
    }
}
