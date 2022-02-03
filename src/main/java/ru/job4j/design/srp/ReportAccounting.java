package ru.job4j.design.srp;

import java.util.List;

public class ReportAccounting implements ReportType {
    public static final double CONST = 1.5;

    @Override
    public String report(List<Employee> list) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary changed;");
        for (Employee em : list) {
            text.append(em.getName()).append(";")
                    .append(em.getHired()).append(";")
                    .append(em.getFired()).append(";")
                    .append(em.getSalary() * CONST).append(";")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
