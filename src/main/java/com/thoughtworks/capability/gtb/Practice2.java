package com.thoughtworks.capability.gtb;

import java.time.DayOfWeek;
import java.time.LocalDate;

/**
 * 对任意日期获取下一个工作日, 不考虑节假日
 *
 * @author itutry
 * @create 2020-05-15_17:20
 */
public class Practice2 {
  public final static int dayValueOfThursday = 4;
  public final static int dayValueOfSunDay = 7;

  public static LocalDate getNextWorkDate(LocalDate date) {

    int dayValue = date.getDayOfWeek().getValue();

    if (isAfterThursday(dayValue)){
      int hasNonWorkDaysBeforeMonday= dayValueOfSunDay - dayValue;
      date = date.plusDays(hasNonWorkDaysBeforeMonday);
    }
    return date.plusDays(1);
  }

  static boolean isAfterThursday(int dayValue){
    if (dayValue > dayValueOfThursday) {
      return true;
    }
      return false;
  }
}
