package ru.job4j.design.srp;

import java.util.List;
import java.util.function.Predicate;

public class ReportEngine implements Report {
    private Store store;
    private ReportDepartment department;

    public ReportEngine(Store store, ReportDepartment department) {
        this.store = store;
        this.department = department;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        List<Employee> list = store.findBy(filter);
        return department.report(list);
    }
}
