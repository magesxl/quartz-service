package com.example.pay.util;

import org.joda.time.DateMidnight;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class HardcodedSystemClock implements SystemClock {

    /**
     * Hardcode the date time here. It will be used as the "Sytem Time"
     * for all code that use the SystemClock interface, where this implementation
     * is the one returned by SystemFactory.getClock().
     */
    // This instance set to 9/6/2009 8:00am
    private static final DateTime theDateTime = new DateTime(2009, 9, 6, 14, 30, 0, 0);

    public DateTime getDateTime() {
        return theDateTime;
    }

    public long getTimeInMillis() {
        return theDateTime.getMillis();
    }

    public DateMidnight getDateMidnight() {
        return theDateTime.toDateMidnight();
    }

    public LocalDate getLocalDate() {
        return theDateTime.toLocalDate();
    }

    public LocalTime getLocalTime() {
        return theDateTime.toLocalTime();
    }

    public Calendar getCalendar() {
        return theDateTime.toCalendar(Locale.getDefault());
    }

    public Date getDate() {
        return theDateTime.toDate();
    }

    public static void main(String[] args) throws IOException {
        String orig = "hello world!";
        byte[] bytes = Files.readAllBytes(Paths.get("E:\\789.jpg"));
//        String desc = Base64.getEncoder().encodeToString(orig.getBytes(StandardCharsets.UTF_8));
        String desc = Base64.getEncoder().encodeToString(bytes);
        System.out.println("加密后的字符串为:"+desc);

        String unDecodeStr=new String(Base64.getDecoder().decode(desc),StandardCharsets.UTF_8);
//        System.out.println("解密后的字符串为"+unDecodeStr);

        // 生成md5

    }

}
