package ru.job4j.design.srp;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;
import org.junit.Test;
import java.util.Calendar;

public class ReportEngineTest {

    @Test
    public void reportHR() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        Employee worker1 = new Employee("Igor", now, now, 200);
        store.add(worker);
        store.add(worker1);
        ReportHR reportHR = new ReportHR();
        Report engine = new ReportEngine(store, reportHR);
        StringBuilder expect = new StringBuilder()
                .append("Name; Salary;")
                .append(worker1.getName()).append(";")
                .append(worker1.getSalary()).append(";")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(worker.getSalary()).append(";")
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void reportAccounting() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        Employee worker1 = new Employee("Igor", now, now, 200);
        store.add(worker);
        store.add(worker1);
        ReportAccounting accounting = new ReportAccounting();
        Report engine = new ReportEngine(store, accounting);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary changed;")
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(worker.getSalary() * ReportAccounting.CONST).append(";")
                .append(System.lineSeparator())
                .append(worker1.getName()).append(";")
                .append(worker1.getHired()).append(";")
                .append(worker1.getFired()).append(";")
                .append(worker1.getSalary() * ReportAccounting.CONST).append(";")
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void reportProgrammers() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        ReportProgrammers programmers = new ReportProgrammers();
        Report engine = new ReportEngine(store, programmers);
        StringBuilder expect = new StringBuilder()
                .append("""
                <html>
                <head>
                <title>Name; Hired; Fired; Salary;</title>
                </head>
                <body>
                """)
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(worker.getSalary()).append(";")
                .append(System.lineSeparator())
                .append("</body>\n</html>");
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void reportJson() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        ReportJson report = new ReportJson();
        Report engine = new ReportEngine(store, report);
        StringBuilder expect = new StringBuilder()
                .append("[")
                .append("{")
                .append("\"name\":\"").append(worker.getName()).append("\",")
                .append("\"hired\":").append("{").append("\"year\":")
                .append(worker.getHired().get(Calendar.YEAR))
                .append(",")
                .append("\"month\":").append(worker.getHired().get(Calendar.MONTH))
                .append(",")
                .append("\"dayOfMonth\":").append(worker.getHired().get(Calendar.DAY_OF_MONTH))
                .append(",")
                .append("\"hourOfDay\":").append(worker.getHired().get(Calendar.HOUR_OF_DAY))
                .append(",")
                .append("\"minute\":").append(worker.getHired().get(Calendar.MINUTE))
                .append(",")
                .append("\"second\":").append(worker.getHired().get(Calendar.SECOND))
                .append("},")
                .append("\"fired\":").append("{").append("\"year\":").append(worker.getHired()
                        .get(Calendar.YEAR))
                .append(",")
                .append("\"month\":").append(worker.getHired().get(Calendar.MONTH)).append(",")
                .append("\"dayOfMonth\":").append(worker.getHired().get(Calendar.DAY_OF_MONTH))
                .append(",")
                .append("\"hourOfDay\":").append(worker.getHired().get(Calendar.HOUR_OF_DAY))
                .append(",")
                .append("\"minute\":").append(worker.getHired().get(Calendar.MINUTE))
                .append(",")
                .append("\"second\":").append(worker.getHired().get(Calendar.SECOND))
                .append("},")
                .append("\"salary\":").append(worker.getSalary()).append("}")
                .append("]");
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }
}