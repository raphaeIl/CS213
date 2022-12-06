package edu.rutgers.rupizzeria.main.pizzafactory;

/**
 * Blueprint for a pizza factory
 * who is responsible for creating different core.types of pizzas,
 * implements the "Abstract Factory" design pattern
 * @author Michael Liu, Genfu Liu
 */
public interface PizzaFactory {

    /**
     * Creates a pizza that is Deluxe flavor
     * @return the pizza created
     */
    Pizza createDeluxe();

    /**
     * Creates a pizza that is Meatzza flavor
     * @return the pizza created
     */
    Pizza createMeatzza();

    /**
     * Creates a pizza that is BBQChicken flavor
     * @return the pizza created
     */
    Pizza createBBQChicken();

    /**
     * Creates a pizza that is Custom flavor
     * @return the pizza created
     */
    Pizza createBuildYourOwn();
}