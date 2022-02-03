package ru.job4j.design.srp;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;
import org.junit.Test;
import java.text.SimpleDateFormat;
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
        String expect = "[{\"name\":\"" + worker.getName()
                + "\",\"hired\":{\"year\":" + worker.getHired().get(Calendar.YEAR)
                + ",\"month\":" + worker.getHired().get(Calendar.MONTH) + ","
                + "\"dayOfMonth\":" + worker.getHired().get(Calendar.DAY_OF_MONTH)
                + ",\"hourOfDay\":" + worker.getHired().get(Calendar.HOUR_OF_DAY)
                + ",\"minute\":" + worker.getHired().get(Calendar.MINUTE)
                + ",\"second\":" + worker.getHired().get(Calendar.SECOND)
                + "},\"fired\":" + "{" + "\"year\":" + worker.getHired().get(Calendar.YEAR)
                + ",\"month\":" + worker.getHired().get(Calendar.MONTH)
                + ",\"dayOfMonth\":" + worker.getHired().get(Calendar.DAY_OF_MONTH)
                + ",\"hourOfDay\":" + worker.getHired().get(Calendar.HOUR_OF_DAY)
                + ",\"minute\":" + worker.getHired().get(Calendar.MINUTE)
                + ",\"second\":" + worker.getHired().get(Calendar.SECOND)
                + "},\"salary\":" + worker.getSalary() + "}]";
        assertThat(engine.generate(em -> true), is(expect));
    }

    @Test
    public void reportXml() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        String dateString = dateFormat.format(now.getTime());
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        ReportXml report = new ReportXml();
        Report engine = new ReportEngine(store, report);
        String expect = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"
                + "\n<employees>"
                + "\n"
                + "    <employees>"
                + "\n"
                + "        <fired>" + dateString + "</fired>"
                + "\n"
                + "        <hired>" + dateString + "</hired>"
                + "\n"
                + "        <name>" + worker.getName() + "</name>"
                + "\n"
                + "        <salary>" + worker.getSalary() + "</salary>"
                + "\n"
                + "    </employees>"
                + "\n</employees>\n";
        assertThat(engine.generate(em -> true), is(expect));
    }
}