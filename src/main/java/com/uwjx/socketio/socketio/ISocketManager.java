package com.uwjx.socketio.socketio;

import com.corundumstudio.socketio.SocketIOClient;
import com.uwjx.socketio.util.SocketIOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class ISocketManager {

    private static Map<String, SocketIOClient> clientMap = new HashMap<>();

    public void addClient(SocketIOClient client) {
        clientMap.put(SocketIOUtil.getId(client), client);
    }

    public void removeClient(SocketIOClient client) {
        clientMap.remove(SocketIOUtil.getId(client));
    }

    public Map<String, SocketIOClient> getClientMap() {
        return clientMap;
    }

    public SocketIOClient getClientById(String id) {
        return clientMap.get(id);
    }

    public void joinRoom(String id , String roomName){
        clientMap.get(id).joinRoom(roomName);
    }

    public void printClientMap() {
        clientMap.forEach((key, client) ->
                log.warn("key -> {} , " + "UUID -> {}" + "rooms -> {}",
                        key, client.getSessionId().toString(), client.getAllRooms()));
    }
}
