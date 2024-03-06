package com.uwjx.socketio.socketio.listeners;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.uwjx.socketio.SocketIOUtil;
import com.uwjx.socketio.socketio.ISocketManager;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ISocketIOConnectListener implements ConnectListener {

    @Resource
    ISocketManager socketManager;

    @Override
    public void onConnect(SocketIOClient client) {
        log.warn("新设备建立连接 : {}  ip -> {}" , SocketIOUtil.getId(client) , SocketIOUtil.getId(client));
        socketManager.addClient(client);
    }
}
