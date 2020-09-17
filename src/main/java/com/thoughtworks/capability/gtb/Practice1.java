package com.thoughtworks.capability.gtb;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;

/**
 * 计算任意日期与下一个劳动节相差多少天
 *
 * @author itutry
 * @create 2020-05-15_16:33
 */
public class Practice1 {

  public static long getDaysBetweenNextLaborDay(LocalDate date) {
    int thisYear = date.getYear();
    LocalDate laborDayOfThisYear = LocalDate.of(thisYear,5,1);
    long daysBetweenNextLaborDay;
    if (date.isBefore(laborDayOfThisYear)) {
      daysBetweenNextLaborDay = ChronoUnit.DAYS.between(date,laborDayOfThisYear);
    } else {
      int nextYear = thisYear +1;
      LocalDate laborDayOfNextYear = laborDayOfThisYear.withYear(nextYear);
      daysBetweenNextLaborDay = ChronoUnit.DAYS.between(date,laborDayOfNextYear);
    }


    return daysBetweenNextLaborDay;
  }
}
