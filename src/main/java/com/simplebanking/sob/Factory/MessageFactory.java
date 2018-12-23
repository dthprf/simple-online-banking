package com.simplebanking.sob.Factory;

import com.simplebanking.sob.Constants.MethodType;
import com.simplebanking.sob.Constants.RouteKey;
import com.simplebanking.sob.Model.SOBMessage;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Set;

@Component
@Scope(value = "prototype")
public class MessageFactory {

    public SOBMessage createMessage(MethodType method, RouteKey route, Object requestBody, Object deferredResult) {
        return new SOBMessage(deferredResult, requestBody, method, route, null, null);
    }

    public SOBMessage createMessage(MethodType method, RouteKey route, Object requestBody,
                                    Object deferredResult, HashMap<String, Long> params) {
        return new SOBMessage(deferredResult, requestBody, method, route, params, null);
    }

    public SOBMessage createMessage(MethodType method, RouteKey route, Object requestBody,
                                    Object deferredResult, HashMap<String, Long> params, Set<String> filterParams) {
        return new SOBMessage(deferredResult, requestBody, method, route, params, filterParams);
    }
}
