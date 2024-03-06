package com.uwjx.socketio.service.impl;

import com.corundumstudio.socketio.SocketIOServer;
import com.uwjx.socketio.service.ISocketIOService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ISocketIOServiceImpl implements ISocketIOService , InitializingBean , DisposableBean {

    @Value("${socketio.enable}")
    private boolean enable;
    @Resource
    SocketIOServer socketIOServer;

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
