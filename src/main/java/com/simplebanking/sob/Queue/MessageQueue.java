package com.simplebanking.sob.Queue;

import com.simplebanking.sob.MessageConsumer.MessageConsumer;
import com.simplebanking.sob.Model.SOBMessage;

import java.util.Queue;
import java.util.concurrent.BlockingQueue;

public class MessageQueue {
    private String routingKey;
    private BlockingQueue<SOBMessage> queue;
    private MessageConsumer consumer;

    public MessageQueue(String routingKey, BlockingQueue<SOBMessage> queue, MessageConsumer consumer) {
        this.routingKey = routingKey;
        this.queue = queue;
        this.consumer = consumer;
        this.consumer.setQueue(queue);
    }

    public String getRoutingKey() {
        return routingKey;
    }

    public void setRoutingKey(String routingKey) {
        this.routingKey = routingKey;
    }

    public Queue<SOBMessage> getQueue() {
        return queue;
    }

    public void setQueue(BlockingQueue<SOBMessage> queue) {
        this.queue = queue;
    }

    public MessageConsumer getConsumer() {
        return consumer;
    }

    public void setConsumer(MessageConsumer consumer) {
        this.consumer = consumer;
    }
}
