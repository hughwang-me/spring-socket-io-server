package com.uwjx.socketio.configuration;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.DataListener;
import com.uwjx.socketio.socketio.interceptor.IClientManagerEventInterceptor;
import com.uwjx.socketio.socketio.interceptor.ILogEventInterceptor;
import com.uwjx.socketio.socketio.listeners.ISocketIOConnectListener;
import com.uwjx.socketio.socketio.listeners.ISocketIODisconnectListener;
import com.uwjx.socketio.socketio.listeners.ITestDataEventListener;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class SocketIoConfiguration {

    @Value("${socketio.hostname}")
    private String hostName;
    @Value("${socketio.port}")
    private Integer port;
    @Value("${socketio.maxFramePayloadLength}")
    private Integer maxFramePayloadLength;
    @Value("${socketio.maxHttpContentLength}")
    private Integer maxHttpContentLength;

    @Resource
    ISocketIOConnectListener socketIOConnectListener;
    @Resource
    ISocketIODisconnectListener socketIODisconnectListener;
    @Resource
    IClientManagerEventInterceptor clientManagerEventInterceptor;
    @Resource
    ILogEventInterceptor logEventInterceptor;
    @Resource
    ITestDataEventListener testDataEventListener;

    @Bean
    SocketIOServer socketIOServer(){
        com.corundumstudio.socketio.Configuration configuration = new com.corundumstudio.socketio.Configuration();
        configuration.setHostname(hostName);
        configuration.setPort(port);
        configuration.setMaxFramePayloadLength(maxFramePayloadLength);
        configuration.setMaxHttpContentLength(maxHttpContentLength);
        SocketIOServer socketIOServer = new SocketIOServer(configuration);
        socketIOServer.addConnectListener(socketIOConnectListener);
        socketIOServer.addDisconnectListener(socketIODisconnectListener);
        socketIOServer.addEventInterceptor(clientManagerEventInterceptor);
        socketIOServer.addEventInterceptor(logEventInterceptor);
        socketIOServer.addEventListener("testDataEvent", String.class, testDataEventListener);
        return socketIOServer;
    }
}
