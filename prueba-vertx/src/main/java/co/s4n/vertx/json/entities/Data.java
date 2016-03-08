package co.s4n.vertx.json.entities;

import fj.data.optic.Lens;

public final class Data {

    static public Lens<Data, String> nameLens = Lens.lens(
            data -> data.name, // Get
            nName -> data -> new Data(data.id, nName, data.age, data.salary) // Set
    );

    static public Lens<Data, Integer> ageLens = Lens.lens(
            data -> data.age, // Get
            nAge -> data -> new Data(data.id, data.name, nAge, data.salary) // Set
    );

    static public Lens<Data, Double> salaryLens = Lens.lens(
            data -> data.salary, // Get
            nSalary -> data -> new Data(data.id, data.name, data.age, nSalary) // Set
    );

    private final long id;
    private final String name;
    private final int age;
    private final double salary;

    public Data(long id, String name, int age, double salary) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }
}
