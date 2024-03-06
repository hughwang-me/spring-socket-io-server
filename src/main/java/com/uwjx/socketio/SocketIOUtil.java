package com.uwjx.socketio;

import com.corundumstudio.socketio.SocketIOClient;

public class SocketIOUtil {

    public static String getIp(SocketIOClient client){
        return client.getRemoteAddress().toString();
    }

    public static String getId(SocketIOClient client){
        return client.getSessionId().toString();
    }

}
