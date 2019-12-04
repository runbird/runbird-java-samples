package com.scy.java8.dateDemo;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * 类名： Demo1 <br>
 * 描述：TODO <br>
 * 创建日期： 2018/7/4 <br>
 *
 * @author suocaiyuan
 * @version V1.0
 */
public class Demo1 {

    public static void main(String[] args) {
        demoFunction1();
        demoFunction2();
        demoFunction3();
        demoFunction4();
        demoFunction5();
        demoFunction6();
        demoFunction7();
        demoFunction8();
        demoFunction9();
        demoFunction10();
    }


    //获取今天的日期
    private static void demoFunction1() {
        LocalDate now = LocalDate.now();
        System.out.println(now);
    }

    //在 Java 8 中获取年、月、日信息
    private static void demoFunction2() {
        LocalDate now = LocalDate.now();

        int year = now.getYear();
        int dayOfYear = now.getDayOfYear();

        int month = now.getMonthValue();
        int dayOfMonth = now.getDayOfMonth();

        DayOfWeek week = now.getDayOfWeek();
        System.out.printf("year = %d, month = %d, day = %d %n", year, month, dayOfMonth);
    }

    //在 Java 8 中处理特定日期
    private static void demoFunction3() {
        LocalDate localDate = LocalDate.of(2018, 7, 4);
        System.out.println(localDate);
    }

    //在 Java 8 中判断两个日期是否相等
    private static void demoFunction4() {
        LocalDate now = LocalDate.now();
        LocalDate localDate = LocalDate.of(2018, 7, 4);
        if (now.equals(localDate)) {
            System.out.println("《=============是同一天");
        }
    }

    //    Java 中另一个日期时间的处理就是检查类似每月账单、结婚纪念日、EMI日或保险缴费日这些周期性事件。如果你在电子商务网站工作，
//    那么一定会有一个模块用来在圣诞节、感恩节这种节日时向客户发送问候邮件。Java 中如何检查这些节日或其它周期性事件呢？
//    答案就是 MonthDay 类。这个类组合 了月份和日，去掉了年，这意味着你可以用它判断每年都会发生事件。和这个类相似的还有一个 YearMonth 类。
//    这些类也都是不可变并且线程安全的值类型。下面我们通过 MonthDay 来检查周期性事件：
    //在 Java 8 中检查像生日这种周期性事件
    private static void demoFunction5() {
        LocalDate now = LocalDate.now();
        LocalDate dayOfBirth = LocalDate.of(2018, 7, 5);
        MonthDay birthday = MonthDay.of(dayOfBirth.getDayOfMonth(), dayOfBirth.getDayOfMonth());
        MonthDay currentMonthDay = MonthDay.from(now);
        if (currentMonthDay.equals(birthday)) {
            System.out.println("Happy Birthday");
        } else {
            System.out.println("Sorry, today is not your birthday");
        }

    }

    //在 Java 8 中获取当前时间
    private static void demoFunction6() {
        LocalTime now = LocalTime.now();
        System.out.println(now);//00:06:25.850  可以看到当前时间就只包含时间信息，没有日期
        //如何在现有的时间上增加小时
        LocalTime plusHours = now.plusHours(2);
        System.out.println(plusHours);//02:06:25.850  增加了两小时
    }

    //如何计算一周后的日期
    private static void demoFunction7() {
        LocalDate now = LocalDate.now();
        //LocalDate 日期不包含时间信息，它的 plus()方法用来增加天、周、月，ChronoUnit 类声明了这些时间单位。由于 LocalDate 也是不变类型，返回后一定要用变量赋值。
        LocalDate plus = now.plus(1, ChronoUnit.WEEKS);//chrono 计时
        System.out.println(now + "," + plus);//现在和一周后
        //计算一年前或一年后的日期
        LocalDate minusOfYear = now.minus(1, ChronoUnit.YEARS);
        LocalDate plusOfYear = now.plus(1, ChronoUnit.YEARS);
        System.out.println(minusOfYear + "," + plusOfYear);//一年前和一年后
    }

    //使用 Java 8 的 Clock 时钟类
//    Java 8 增加了一个 Clock 时钟类用于获取当时的时间戳，或当前时区下的日期时间信息。
//    以前用到System.currentTimeInMillis() 和 TimeZone.getDefault() 的地方都可用 Clock 替换。
    private static void demoFunction8() {
        Clock systemUTC = Clock.systemUTC();
        Clock systemDefaultZone = Clock.systemDefaultZone();
        System.out.println(systemUTC + "," + systemDefaultZone);//SystemClock[Z],SystemClock[Asia/Shanghai]
    }

    //如何用 Java 判断日期是早于还是晚于另一个日期
    private static void demoFunction9() {
        LocalDate now = LocalDate.now();
        //LocalDate.of(2018,7,6)
        LocalDate tomorrow = now.plus(1, ChronoUnit.DAYS);
        LocalDate yesterday = now.minus(1, ChronoUnit.DAYS);
        if (tomorrow.isAfter(now)) {
            System.out.println("Tomorrow comes after today");
        }
        if (yesterday.isBefore(now)) {
            System.out.println("Yesterday is day before today");
        }
    }

    //在 Java 8 中处理时区
    private static void demoFunction10() {
//        Java 8 不仅分离了日期和时间，也把时区分离出来了。
//        现在有一系列单独的类如 ZoneId 来处理特定时区，ZoneDateTime 类来表示某时区下的时间。
//        这在 Java 8 以前都是 GregorianCalendar 类来做的。
        ZoneId america = ZoneId.of("America/New_York");
        LocalDateTime localDateTime = LocalDateTime.now();
        ZonedDateTime dateAndTimeInNewYork = ZonedDateTime.of(localDateTime, america);
        System.out.println(dateAndTimeInNewYork);
    }

    //如何表示信用卡到期这类固定日期，答案就在 YearMonth
    private static void demoFunction11() {
//  与 MonthDay 检查重复事件的例子相似，YearMonth 是另一个组合类，用于表示信用卡到期日、FD 到期日、期货期权到期日等。
//  还可以用这个类得到 当月共有多少天，YearMonth 实例的 lengthOfMonth() 方法可以返回当月的天数，在判断 2 月有 28 天还是 29 天时非常有用。
        YearMonth currentYearMonth = YearMonth.now();
        System.out.printf("Days in month year %s: %d%n", currentYearMonth, currentYearMonth.lengthOfMonth());//Days in month year 2018-06: 30
        YearMonth creditCardExpiry = YearMonth.of(2018, Month.FEBRUARY);
        System.out.printf("Your credit card expires on %s %n", creditCardExpiry);//Your credit card expires on 2018-02
    }

    //如何在 Java 8 中检查闰年
    private static void demoFunction12() {
        // isLeapYear()判断闰年
    }

    //计算两个日期之间的天数和月数
    private static void demoFunction13() {
        LocalDate now = LocalDate.now();
        LocalDate date = LocalDate.of(2019, Month.MARCH, 20);
        Period period = Period.between(now, date);
        System.out.println("离下个时间还有" + period.getMonths() + " 个月");
        System.out.println("离下个时间还有" + period.getDays() + " 天");
    }

    //包含时差信息的日期和时间
    private static void demoFunction14() {
//        在 Java 8 中，ZoneOffset 类用来表示时区，举例来说印度与 GMT 或 UTC 标准时区相差 +05:30，
//        可以通过ZoneOffset.of() 静态方法来获取对应的时区。一旦得到了时差就可以通过传入 LocalDateTime 和 ZoneOffset 来创建一个 OffSetDateTime 对象。
        LocalDateTime datetime = LocalDateTime.of(2014, Month.JANUARY, 14, 19, 30);
        ZoneOffset offset = ZoneOffset.of("+05:30");
        OffsetDateTime date = OffsetDateTime.of(datetime, offset);
        System.out.println("Date and Time with timezone offset in Java : " + date);
    }

    //中获取当前的时间戳
    private static void demoFunction15() {
        //Instant 类有一个静态工厂方法 now() 会返回当前的时间戳，如下所示：
        Instant now = Instant.now();
        System.out.println(now);
//      你可以使用 Date 类和 Instant 类各自的转换方法互相转换，例如：Date.from(Instant) 将 Instant 转换成java.util.Date,
//      Date.toInstant() 则是将 Date 类转换成 Instant 类。
    }

    //把日期转换成字符串
    private static void demoFunction16() {
        LocalDateTime now = LocalDateTime.now();
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMdd yyyy hh:mm a");
            String format = now.format(formatter);
            System.out.printf("now at :  %s %n", format);
        } catch (DateTimeException ex) {
            System.out.printf("%s can't be formatted!%n", now);
            ex.printStackTrace();
        }

    }

    //使用预定义的格式化工具去解析或格式化日期
    private static void demoFunction17() {
//   在 Java 8 以前的世界里，日期和时间的格式化非常诡异，唯一的帮助类 SimpleDateFormat 也是非线程安全的，而且用作局部变量解析和格式化日期时显得很笨重。
//   幸好线程局部变量能使它在多线程环境中变得可用，不过这都是过去时了。Java 8 引入了全新的日期时间格式工具，线程安全而且使用方便。
    }

    //使用自定义格式化工具解析日期
    private static void demoFunction18() {
//    尽管内置格式化工具很好用，有时还是需要定义特定的日期格式。可以调用 DateTimeFormatter 的 ofPattern() 静态方法并传入任意格式返回其实例，
//    格式中的字符和以前代表的一样，M 代表月，m 代表分。如果格式不规范会抛出 DateTimeParseException 异常，不过如果只是把 M 写成 m 这种逻辑错误是不会抛异常的。
    }


}
