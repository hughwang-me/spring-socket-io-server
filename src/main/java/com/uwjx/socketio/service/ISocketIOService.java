package com.uwjx.socketio.service;

import com.uwjx.socketio.domain.ISocketIOEvent;

public interface ISocketIOService {

    void startServer();

    void stopServer();

    void sendEvent(ISocketIOEvent event);

    void joinRoom(String id , String room);
}
