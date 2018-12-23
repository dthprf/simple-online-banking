package com.simplebanking.sob.Model;

import com.simplebanking.sob.Constants.MethodType;
import com.simplebanking.sob.Constants.RouteKey;

import java.util.HashMap;

public class SOBMessage {
    private Object deferredResult;
    private Object requestBody;
    private MethodType method;
    private RouteKey route;
    private HashMap<String, Long> pathParams;

    public SOBMessage(Object deferredResult, Object requestBody, String method, String route, HashMap<String, Long> pathParams) {
        this.deferredResult = deferredResult;
        this.requestBody = requestBody;
        this.method = method;
        this.route = route;
        this.pathParams = pathParams;
    }

    public Object getDeferredResult() {
        return deferredResult;
    }

    public void setDeferredResult(Object deferredResult) {
        this.deferredResult = deferredResult;
    }

    public Object getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(Object requestBody) {
        this.requestBody = requestBody;
    }

    public MethodType getMethod() {
        return method;
    }

    public void setMethod(MethodType method) {
        this.method = method;
    }

    public RouteKey getRoute() {
        return route;
    }

    public void setRoute(RouteKey route) {
        this.route = route;
    }

    public HashMap<String, Long> getPathParams() {
        return pathParams;
    }

    public void setPathParams(HashMap<String, Long> pathParams) {
        this.pathParams = pathParams;
    }
}
