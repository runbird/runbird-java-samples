Java 8 日期时间 API 的重点

通过这些例子，你肯定已经掌握了 Java 8 日期时间 API 的新知识点。现在来回顾一下这个优雅 API 的使用要点：

1）提供了 javax.time.ZoneId 获取时区。

2）提供了 LocalDate 和 LocalTime 类。

3）Java 8 的所有日期和时间 API 都是不可变类并且线程安全，而现有的 Date 和 Calendar API 中的 java.util.Date 和SimpleDateFormat 是非线程安全的。

4）主包是 java.time, 包含了表示日期、时间、时间间隔的一些类。里面有两个子包 java.time.format 用于格式化， java.time.temporal 用于更底层的操作。

5）时区代表了地球上某个区域内普遍使用的标准时间。每个时区都有一个代号，格式通常由区域/城市构成（Asia/Tokyo），在加上与格林威治或 UTC 的时差。例如：东京的时差是 +09:00。

6）OffsetDateTime 类实际上组合了 LocalDateTime 类和 ZoneOffset 类。用来表示包含和格林威治或 UTC 时差的完整日期（年、月、日）和时间（时、分、秒、纳秒）信息。

7）DateTimeFormatter 类用来格式化和解析时间。与 SimpleDateFormat 不同，这个类不可变并且线程安全，需要时可以给静态常量赋值。 DateTimeFormatter 类提供了大量的内置格式化工具，同时也允许你自定义。在转换方面也提供了 parse() 将字符串解析成日期，如果解析出错会抛出 DateTimeParseException。DateTimeFormatter 类同时还有format() 用来格式化日期，如果出错会抛出 DateTimeException异常。

8）再补充一点，日期格式“MMM d yyyy”和“MMM dd yyyy”有一些微妙的不同，第一个格式可以解析“Jan 2 2014”和“Jan 14 2014”，而第二个在解析“Jan 2 2014”就会抛异常，因为第二个格式里要求日必须是两位的。如果想修正，你必须在日期只有个位数时在前面补零，就是说“Jan 2 2014”应该写成 “Jan 02 2014”。