package com.simplebanking.sob.Model;

import java.util.HashMap;

public class SOBMessage {
    private Object deferredResult;
    private Object requestBody;
    private String method;
    private String route;
    private HashMap<String, String> pathParams;

    public SOBMessage(Object deferredResult, Object requestBody, String method, String route, HashMap<String, String> pathParams) {
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

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public HashMap<String, String> getPathParams() {
        return pathParams;
    }

    public void setPathParams(HashMap<String, String> pathParams) {
        this.pathParams = pathParams;
    }
}
