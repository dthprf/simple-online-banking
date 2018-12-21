package com.simplebanking.sob.MessageConsumer;

import com.simplebanking.sob.Model.SOBMessage;

import java.util.Queue;

public interface MessageConsumer {
    void processMessage(SOBMessage message);
    void setQueue(Queue<SOBMessage> queue);
}
