package com.simplebanking.sob.Exchange;

import com.simplebanking.sob.Constants.RouteKey;
import com.simplebanking.sob.MessageConsumer.MessageConsumer;
import com.simplebanking.sob.Model.SOBMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@Scope(value = "prototype")
public class Exchange {

    private Map<RouteKey, MessageConsumer> routeMap = new ConcurrentHashMap<>();

    @Autowired
    public void setRouteMap(Set<MessageConsumer> consumers) {
        this.routeMap = consumers.stream()
                .collect(Collectors.toMap(MessageConsumer::getRouteKey, Function.identity()));
    }

    public Map<RouteKey, MessageConsumer> getRouteMap() {
        return routeMap;
    }

    public void routeMessage(SOBMessage message) {
        routeMap.get(message.getRoute()).enqueueMessage(message);
    }
}
