package ru.job4j.design.srp;

import java.util.List;

public class ReportHR implements ReportDepartment {

    @Override
    public String report(List<Employee> list) {
        list.sort(((o1, o2) -> Double.compare(o2.getSalary(), o1.getSalary())));
        StringBuilder text = new StringBuilder();
        text.append("Name; Salary;");
        for (Employee em : list) {
            text.append(em.getName()).append(";")
                    .append(em.getSalary()).append(";")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
