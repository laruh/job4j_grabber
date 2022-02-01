package ru.job4j.design.srp;

import java.util.List;

public class ReportAccounting implements ReportDepartment {

    @Override
    public String report(List<Employee> list) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary changed;");
        for (Employee em : list) {
            text.append(em.getName()).append(";")
                    .append(em.getHired()).append(";")
                    .append(em.getFired()).append(";")
                    .append(em.getSalary() * 1.5).append(";")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
