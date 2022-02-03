package ru.job4j.design.srp;

import com.google.gson.GsonBuilder;

import java.util.List;

public class ReportJson implements ReportType {
    @Override
    public String report(List<Employee> list) {
        var gson = new GsonBuilder().create();
        return gson.toJson(list);
    }
}
