package mao;

import com.rabbitmq.client.Channel;
import mao.tools.RabbitMQ;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeoutException;

/**
 * Project name(项目名称)：rabbitMQDirect交换机实现日志的定向发送
 * Package(包名): mao
 * Class(类名): Logger
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2022/4/22
 * Time(创建时间)： 22:39
 * Version(版本): 1.0
 * Description(描述)： 封装
 */


public class Logger
{
    //交换机
    private static final String EXCHANGE_NAME = "log_exchange";
    //信道
    private Channel channel;
    //全包名
    private final String packageName;
    //日期格式化类
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd  HH:mm:ss");

    /**
     * Instantiates a new Logger.
     *
     * @param clazz the clazz
     */
    public Logger(Class<?> clazz)
    {
        packageName = clazz.getName();
        try
        {
            channel = RabbitMQ.getChannel();
        }
        catch (IOException | TimeoutException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 获得时间
     *
     * @return String
     */
    private String getLocalTime()
    {
        Date date = new Date();
        return simpleDateFormat.format(date);
    }

    /**
     * 将调试信息写入消息队列.
     *
     * @param message the message
     */
    public void debug(String message)
    {
        String localTime = getLocalTime();
        String messages = localTime + ":  " + packageName + ": ---debug---:   " + message;
        try
        {
            channel.basicPublish(EXCHANGE_NAME, "debug", null, messages.getBytes(StandardCharsets.UTF_8));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 将info信息写入消息队列.
     *
     * @param message the message
     */
    public void info(String message)
    {
        String localTime = getLocalTime();
        String messages = localTime + ":  " + packageName + ": ---info---:   " + message;
        try
        {
            channel.basicPublish(EXCHANGE_NAME, "info", null, messages.getBytes(StandardCharsets.UTF_8));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 将警告信息写入消息队列.
     *
     * @param message the message
     */
    public void warn(String message)
    {
        String localTime = getLocalTime();
        String messages = localTime + ":  " + packageName + ": ---warn---:   " + message;
        try
        {
            channel.basicPublish(EXCHANGE_NAME, "warn", null, messages.getBytes(StandardCharsets.UTF_8));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 将错误信息写入消息队列.
     *
     * @param message the message
     */
    public void error(String message)
    {
        String localTime = getLocalTime();
        String messages = localTime + ":  " + packageName + ": ---error---:   " + message;
        try
        {
            channel.basicPublish(EXCHANGE_NAME, "error", null, messages.getBytes(StandardCharsets.UTF_8));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 将trace信息写入消息队列.
     *
     * @param message the message
     */
    public void trace(String message)
    {
        String localTime = getLocalTime();
        String messages = localTime + ":  " + packageName + ": ---trace---:   " + message;
        try
        {
            channel.basicPublish(EXCHANGE_NAME, "trace", null, messages.getBytes(StandardCharsets.UTF_8));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
