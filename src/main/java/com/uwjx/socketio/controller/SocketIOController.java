package com.uwjx.socketio.controller;

import com.corundumstudio.socketio.SocketIOClient;
import com.uwjx.socketio.service.ISocketIOService;
import com.uwjx.socketio.socketio.ISocketManager;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping(value = "socketio")
@Slf4j
public class SocketIOController {

    @Resource
    ISocketManager socketManager;

    @GetMapping(value = "listClients")
    public Map<String , UUID> listClients(){
        Map<String  , UUID> map = new HashMap<>();
        socketManager.getClientMap().forEach((item , client) ->{
            map.put(item , client.getSessionId());
        });
        return map;
    }
}
