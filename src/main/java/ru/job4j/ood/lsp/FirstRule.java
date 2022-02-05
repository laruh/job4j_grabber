package ru.job4j.ood.lsp;

class University {
    private int mark;

    public University(int mark) {
        this.mark = mark;
    }

    public int getMark() {
        return mark;
    }

    public void nextYear(int exam) {
        if (exam <= 2) {
            throw new IllegalArgumentException("Студент будет отчислен");
        }
    }
}

class TomskUniversity extends University {

    public TomskUniversity(int mark) {
        super(mark);
    }

    @Override
    public void nextYear(int exam) {
        if (exam <= 2) {
            throw new IllegalArgumentException("Студент будет отчислен");
        }
        if (getMark() <= 3) {
            throw new IllegalArgumentException("Потеря бюджетного места");
        }
    }
}

class AutoTransport {
    private float fuel;

    public AutoTransport(float fuel) {
        this.fuel = fuel;
    }

    public float getFuel() {
        return fuel;
    }

    public void move(float km) {
        if (km < 0) {
            throw new IllegalArgumentException("Invalid distance!");
        }
        if (fuel < 0) {
            throw new IllegalArgumentException("Need more fuel!");
        }
    }

}

class Bus extends AutoTransport {

    public Bus(float fuel) {
        super(fuel);
    }

    public void move(float km) {
        if (km < 0) {
            throw new IllegalArgumentException("Invalid distance!");
        }
        if (getFuel() < 5) {
            throw new IllegalArgumentException("Need more fuel!");
        }
    }

}

public class FirstRule {
    public static void main(String[] args) {
        AutoTransport bus = new Bus(3);
        bus.move(2);
    }
}
