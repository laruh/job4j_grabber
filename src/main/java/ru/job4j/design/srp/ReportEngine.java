package ru.job4j.design.srp;

import java.util.List;
import java.util.function.Predicate;

public class ReportEngine implements Report {
    private Store store;
    private ReportType reportType;

    public ReportEngine(Store store, ReportType reportType) {
        this.store = store;
        this.reportType = reportType;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        List<Employee> list = store.findBy(filter);
        return reportType.report(list);
    }
}
