package com.uwjx.socketio.socketio.listeners;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.DisconnectListener;
import com.uwjx.socketio.util.SocketIOUtil;
import com.uwjx.socketio.socketio.ISocketManager;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ISocketIODisconnectListener implements DisconnectListener {

    @Resource
    ISocketManager socketManager;

    @Override
    public void onDisconnect(SocketIOClient client) {
        log.warn("设备断开连接 : {}" , SocketIOUtil.getId(client));
        socketManager.removeClient(client);
    }
}
