package com.simplebanking.sob.MessageConsumer;

import com.simplebanking.sob.Constants.RouteKey;
import com.simplebanking.sob.Model.SOBMessage;

public interface MessageConsumer {
    void processMessage(SOBMessage message);

    void enqueueMessage(SOBMessage message);

    RouteKey getRouteKey();

    void startConsumer();
}
