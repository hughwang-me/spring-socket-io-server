package com.uwjx.socketio.socketio.interceptor;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.listener.EventInterceptor;
import com.corundumstudio.socketio.transport.NamespaceClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@Slf4j
public class IClientManagerEventInterceptor implements EventInterceptor {
    @Override
    public void onEvent(NamespaceClient client, String eventName, List<Object> args, AckRequest ackRequest) {
        log.warn("IClientManagerEventInterceptor -> {}" , eventName);
    }
}
