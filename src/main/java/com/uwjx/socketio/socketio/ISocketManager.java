package com.uwjx.socketio.socketio;

import com.corundumstudio.socketio.SocketIOClient;
import com.uwjx.socketio.SocketIOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class ISocketManager {

    private static Map<String , SocketIOClient> clientMap = new HashMap<>();

    public void addClient(SocketIOClient client){
        clientMap.put(SocketIOUtil.getId(client) , client);
    }

    public void removeClient(SocketIOClient client){
        clientMap.remove(SocketIOUtil.getId(client));
    }

    public Map<String , SocketIOClient> getClientMap(){
        return clientMap;
    }

    public void printClientMap(){
        clientMap.forEach((key , client) -> {

        });
    }
}
