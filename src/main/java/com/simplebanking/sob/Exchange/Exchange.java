package com.simplebanking.sob.Exchange;

import com.simplebanking.sob.MessageConsumer.MessageConsumer;
import com.simplebanking.sob.Model.SOBMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class Exchange {

    private Map<String, MessageConsumer> routeMap = new ConcurrentHashMap<>();

    @Autowired
    public void setRouteMap(Set<MessageConsumer> consumers) {
        this.routeMap = consumers.stream()
        .collect(Collectors.toMap(MessageConsumer::getRouteName, Function.identity()));
    }

    public Map<String, MessageConsumer> getRouteMap() {
        return routeMap;
    }

    public void routeMessage(SOBMessage message) {
        routeMap.get(message.getRoute()).enqueueMessage(message);
    }

    public void printSizeOfRoute() {
        System.out.println(routeMap.size());
        System.out.println("IS EMPTY:" + routeMap.isEmpty());
    }
}
