package ru.job4j.design.srp;

import java.time.LocalDateTime;
import java.util.Calendar;
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

    public static void main(String[] args) {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        Employee worker1 = new Employee("Igor", now, now, 200);
        store.add(worker);
        store.add(worker1);
        ReportXml reportXml = new ReportXml();
        Report engine1 = new ReportEngine(store, reportXml);
        System.out.println(engine1.generate(employee -> true));
        System.out.println(LocalDateTime.ofInstant(now.toInstant(), now.getTimeZone().toZoneId()));
    }
}
