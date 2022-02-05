package ru.job4j.ood.lsp;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

class PhoneNumber {

    private int countryCode;

    private int cityCode;

    private int number;

    public PhoneNumber(int countryCode, int cityCode, int number) {
        this.countryCode = countryCode;
        this.cityCode = cityCode;
        this.number = number;
    }

    public int getCountryCode() {
        return countryCode;
    }

    public int getCityCode() {
        return cityCode;
    }

    public int getNumber() {
        return number;
    }
}

class Subscriber {

    private PhoneNumber phoneNumber;

    public Subscriber(PhoneNumber phoneNumber) {
        validate(phoneNumber);
        this.phoneNumber = phoneNumber;
    }

    protected void validate(PhoneNumber phoneNumber) {
        if (phoneNumber.getCountryCode() < 1 || phoneNumber.getCountryCode() > 999) {
            throw new IllegalArgumentException("Invalid country code!");
        }
        if (phoneNumber.getCityCode() < 1 || phoneNumber.getCityCode() > 999) {
            throw new IllegalArgumentException("Invalid city code!");
        }
        if (phoneNumber.getNumber() < 1 || phoneNumber.getNumber() > 999_999_999) {
            throw new IllegalArgumentException("Invalid number!");
        }
    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(PhoneNumber phoneNumber) {
        validate(phoneNumber);
        this.phoneNumber = phoneNumber;
    }
}

class SomeOperatorSubscriber extends Subscriber {

    public SomeOperatorSubscriber(PhoneNumber phoneNumber) {
        super(phoneNumber);
    }

    @Override
    public void setPhoneNumber(PhoneNumber phoneNumber) {
        setPhoneNumber(phoneNumber);
    }
}

public class ThirdRule {
    public static void main(String[] args) {
        Subscriber subscriber = new SomeOperatorSubscriber(
                new PhoneNumber(+1, 111, 111_111_111)
        );
        subscriber.setPhoneNumber(
                new PhoneNumber(-1, 111, 111_111_111)
        );
    }
}

class ReadFile {
    private String path;

    public ReadFile(String path) {
        validate(path);
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void validate(String path) {
        File fileIn = Paths.get(path).toFile();
        if (!fileIn.exists()) {
            throw new IllegalArgumentException(
                    String.format("Not exist %s", fileIn.getAbsoluteFile()));
        }
        if (!fileIn.isDirectory()) {
            throw new IllegalArgumentException(
                    String.format("Not directory %s", fileIn.getAbsolutePath()));
        }
    }

    public void read(String path) {
        validate(path);
        String rsl = null;
        try {
            rsl = Files.readString(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(rsl);
    }
}

class ReadFirstDirectory extends ReadFile {

    public ReadFirstDirectory(String path) {
        super(path);
    }

    @Override
    public void read(String path) {
        super.read(path);
    }
}
