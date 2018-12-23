package com.simplebanking.sob.Factory;

import com.simplebanking.sob.Model.SOBMessage;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
@Scope(value = "prototype")
public class MessageFactory {
    public SOBMessage createMessage(String method, String route, Object requestBody, Object deferredResult) {
        return new SOBMessage(deferredResult, requestBody, method, route, null);
    }

    public SOBMessage createMessage(String method, String route, Object requestBody,
                                    Object deferredResult, HashMap<String, Long> params) {
        return new SOBMessage(deferredResult, requestBody, method, route, params);
    }
}
