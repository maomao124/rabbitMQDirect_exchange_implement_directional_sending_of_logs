package mao;

import com.rabbitmq.client.Channel;
import mao.tools.RabbitMQ;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

/**
 * Project name(项目名称)：rabbitMQDirect交换机实现日志的定向发送
 * Package(包名): mao
 * Class(类名): Producer
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2022/4/22
 * Time(创建时间)： 22:18
 * Version(版本): 1.0
 * Description(描述)： 无
 */

public class Producer
{
    //交换机
    private static final String EXCHANGE_NAME = "log_exchange";

    public static void main(String[] args) throws IOException, TimeoutException
    {
        Channel channel = RabbitMQ.getChannel();
        channel.basicPublish(EXCHANGE_NAME, "debug", null, "调试信息：...".getBytes(StandardCharsets.UTF_8));
        channel.basicPublish(EXCHANGE_NAME, "info", null, "基本信息：...".getBytes(StandardCharsets.UTF_8));
        channel.basicPublish(EXCHANGE_NAME, "warn", null, "警告信息：...".getBytes(StandardCharsets.UTF_8));
        channel.basicPublish(EXCHANGE_NAME, "error", null, "错误信息：...".getBytes(StandardCharsets.UTF_8));
        channel.basicPublish(EXCHANGE_NAME, "debug", null, "调试信息1：.....".getBytes(StandardCharsets.UTF_8));
        channel.basicPublish(EXCHANGE_NAME, "trace", null, "错误信息！！！：...".getBytes(StandardCharsets.UTF_8));
    }
}
