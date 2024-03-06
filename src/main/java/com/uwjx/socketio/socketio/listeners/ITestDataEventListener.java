package com.uwjx.socketio.socketio.listeners;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.DataListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ITestDataEventListener implements DataListener<String> {


    @Override
    public void onData(SocketIOClient client, String data, AckRequest ackSender) throws Exception {
        log.warn("处理 ITestDataEventListener : {}" , data);
        ackSender.sendAckData("ok");
    }
}
