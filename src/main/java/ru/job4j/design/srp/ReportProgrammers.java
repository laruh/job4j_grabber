package ru.job4j.design.srp;

import java.util.List;

public class ReportProgrammers implements ReportDepartment {
    @Override
    public String report(List<Employee> list) {
        StringBuilder text = new StringBuilder();
        text.append("""
                <html>
                <head>
                <title>Name; Hired; Fired; Salary;</title>
                </head>
                <body>
                """);
        for (Employee em : list) {
            text.append(em.getName()).append(";")
                    .append(em.getHired()).append(";")
                    .append(em.getFired()).append(";")
                    .append(em.getSalary()).append(";")
                    .append(System.lineSeparator());
        }
        text.append("</body>\n</html>");
        return text.toString();
    }
}
