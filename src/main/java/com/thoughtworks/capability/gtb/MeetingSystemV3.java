package com.thoughtworks.capability.gtb;

import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * 脑洞会议系统v3.0
 * 1.当前会议时间"2020-04-01 14:30:00"表示伦敦的本地时间，而输出的新会议时间是芝加哥的本地时间
 * 场景：
 * a:上个会议是伦敦的同事定的，他在界面上输入的时间是"2020-04-01 14:30:00"，所以我们要解析的字符串是伦敦的本地时间
 * b:而我们在当前时区(北京时区)使用系统
 * c:我们设置好新会议时间后，要发给芝加哥的同事查看，所以格式化后的新会议时间要求是芝加哥的本地时间
 * 2.用Period来实现下个会议时间的计算
 *
 * @author itutry
 * @create 2020-05-19_18:43
 */
public class MeetingSystemV3 {

    public final static String LONDON_ZONE = "Europe/London";
    public final static String BEIJING_ZONE = "Asia/Shanghai";
    public final static String CHICAGO_ZONE = "America/Chicago";

    public static void main(String[] args) {
        String timeStr = "2020-04-01 14:30:00";
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime meetingTime = LocalDateTime.parse(timeStr, formatter);
        LocalDateTime meetingTimeInBeijing = transferZoneTime(meetingTime, LONDON_ZONE, BEIJING_ZONE);

        LocalDateTime now = LocalDateTime.now();
        if (now.isAfter(meetingTimeInBeijing)) {
            Period period = Period.of(0, 0, 1);
            meetingTimeInBeijing = meetingTimeInBeijing.plus(period);

            LocalDateTime meetingTimeInChicago = transferZoneTime(meetingTimeInBeijing, BEIJING_ZONE, CHICAGO_ZONE);
            String showTimeStr = formatter.format(meetingTimeInChicago);
            System.out.println(showTimeStr);
        } else {
            System.out.println("会议还没开始呢");
        }
    }

    static LocalDateTime transferZoneTime(LocalDateTime currentLocalDateTime, String currentZone, String targetZone) {
        ZonedDateTime zonedDateTimeInCurrentZone = ZonedDateTime.of(currentLocalDateTime, ZoneId.of(currentZone));
        ZonedDateTime zonedDateTimeInTargetZone = zonedDateTimeInCurrentZone.withZoneSameInstant(ZoneId.of(targetZone));
        return zonedDateTimeInTargetZone.toLocalDateTime();
    }
}
