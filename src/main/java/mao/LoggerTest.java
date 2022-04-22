package mao;

/**
 * Project name(项目名称)：rabbitMQDirect交换机实现日志的定向发送
 * Package(包名): mao
 * Class(类名): LoggerTest
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2022/4/22
 * Time(创建时间)： 22:52
 * Version(版本): 1.0
 * Description(描述)： 无
 */

public class LoggerTest
{
    private static final Logger log = new Logger(LoggerTest.class);

    public static void main(String[] args)
    {
        log.debug("调试");
        log.info("123");
        log.warn("456");
        log.error("789");
        log.trace("000");
    }
}
