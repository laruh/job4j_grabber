package ru.job4j.ood.srp.bad;

import java.time.LocalDateTime;
import java.util.Date;

public interface TimeConverter {
    LocalDateTime convert(Date date);
}
