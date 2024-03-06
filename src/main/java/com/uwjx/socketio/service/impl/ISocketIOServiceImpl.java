package com.uwjx.socketio.service.impl;

import com.corundumstudio.socketio.AckCallback;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.uwjx.socketio.domain.ISocketIOEvent;
import com.uwjx.socketio.service.ISocketIOService;
import com.uwjx.socketio.socketio.ISocketManager;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ISocketIOServiceImpl implements ISocketIOService , InitializingBean , DisposableBean {

    @Value("${socketio.enable}")
    private boolean enable;
    @Resource
    SocketIOServer socketIOServer;
    @Resource
    ISocketManager socketManager;

    @Override
    public void startServer() {
        socketIOServer.start();
        log.warn("SocketIO Server 已启动");
    }

    @Override
    public void stopServer() {
        socketIOServer.stop();
        log.warn("SocketIO Server 已停止");
    }

    @Override
    public void sendEvent(ISocketIOEvent event) {
        log.warn("发送数据 : {}" , event);
        SocketIOClient client = socketManager.getClientById(event.getClientId());
        log.warn("client isChannelOpen : {}" , client.isChannelOpen());
        log.warn("client isWritable : {}" , client.isWritable());
        if(client.isWritable()){
            client.sendEvent(event.getEvent(), event.getContent());
        }

    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if(enable){
            startServer();
        }else {
            log.warn("未启动 SocketIO Server");
        }
    }

    @Override
    public void destroy() throws Exception {
        stopServer();
    }
}
