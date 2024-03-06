package com.uwjx.socketio.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ISocketIOEvent {

    private String clientId;
    private String event;
    private Object content;
}
