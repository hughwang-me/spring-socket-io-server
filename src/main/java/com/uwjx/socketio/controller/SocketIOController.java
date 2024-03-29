package com.uwjx.socketio.controller;

import com.corundumstudio.socketio.SocketIOClient;
import com.uwjx.socketio.domain.ISocketIOEvent;
import com.uwjx.socketio.service.ISocketIOService;
import com.uwjx.socketio.socketio.ISocketManager;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping(value = "socketio")
@Slf4j
public class SocketIOController {

    @Resource
    ISocketManager socketManager;
    @Resource
    ISocketIOService socketIOService;

    @GetMapping(value = "listClients")
    public Map<String, UUID> listClients() {
        Map<String, UUID> map = new HashMap<>();
        socketManager.getClientMap().forEach((item, client) -> {
            map.put(item, client.getSessionId());
        });
        return map;
    }

    @PostMapping(value = "sendEvent")
    public String sendEvent(@RequestBody ISocketIOEvent event) {
        socketIOService.sendEvent(event);
        return "ok";
    }

    @GetMapping(value = "joinRoom")
    public String joinRoom(@RequestParam("id") String id, @RequestParam("room") String room) {
        socketIOService.joinRoom(id , room);
        return "ok";
    }

//    @GetMapping(value = "printClients")
//    public String printClients() {
//        socketManager.printClientMap();
//        return "ok";
//    }
}
