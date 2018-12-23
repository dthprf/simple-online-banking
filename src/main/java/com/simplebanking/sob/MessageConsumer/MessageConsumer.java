package com.simplebanking.sob.MessageConsumer;

import com.simplebanking.sob.Model.SOBMessage;

public interface MessageConsumer {
    void processMessage(SOBMessage message);
    void enqueueMessage(SOBMessage message);
    String getRouteKey();
    void startConsumer();
}
