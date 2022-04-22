package mao;

import com.rabbitmq.client.*;
import mao.tools.RabbitMQ;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

/**
 * Project name(项目名称)：rabbitMQDirect交换机实现日志的定向发送
 * Package(包名): mao
 * Class(类名): WarnConsumer
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2022/4/22
 * Time(创建时间)： 22:23
 * Version(版本): 1.0
 * Description(描述)： 无
 */

public class WarnConsumer
{
    //交换机
    private static final String EXCHANGE_NAME = "log_exchange";
    //队列
    private static final String QUEUE_NAME = "log_warn_queue";

    public static void main(String[] args) throws IOException, TimeoutException
    {
        Channel channel = RabbitMQ.getChannel();
        //声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        //声明交换机
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
        //绑定
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "warn");

        channel.basicConsume(QUEUE_NAME, true, new DeliverCallback()
        {
            @Override
            public void handle(String consumerTag, Delivery message) throws IOException
            {
                byte[] messageBody = message.getBody();
                String message1 = new String(messageBody, StandardCharsets.UTF_8);
                System.out.println("warn进程获得日志：" + message1);
            }
        }, new CancelCallback()
        {
            @Override
            public void handle(String consumerTag) throws IOException
            {
                System.out.println("异常");
            }
        });
    }
}
