package com.uwjx.socketio;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDate;

@Slf4j
public class FirstSocketIOClient {

    public static void main(String[] args) {
//        String url = "http://localhost:8091";
        String url = "http://192.168.90.149:12451";
        log.warn("URL ->{}", url);
        try {
            IO.Options options = new IO.Options();
            options.transports = new String[]{"websocket"};
            options.reconnectionAttempts = 2;
            options.reconnectionDelay = 1000;
            options.timeout = 500;
            final Socket socket = IO.socket(url , options);

            socket.on(Socket.EVENT_CONNECT, args1 -> socket.send("hello..."));

            // 自定义事件`connected` -> 接收服务端成功连接消息
//            socket.on("connected", objects -> log.debug("服务端:" + objects[0].toString()));

            // 自定义事件`push_data_event` -> 接收服务端消息


            // 自定义事件`myBroadcast` -> 接收服务端广播消息
//            socket.on("myBroadcast", objects -> log.debug("服务端：" + objects[0].toString()));

            socket.connect();
            log.warn("建立连接");

            socket.onAnyIncoming(new Emitter.Listener() {
                @Override
                public void call(Object... objects) {
                    log.warn("任何消息进入 : {} , {}" , objects[0] , objects[1]);
                    if(objects[1].equals("wanghuan")){
                        socket.emit("testDataEvent" ,"测试OK啦，你发的是wanghuan");
                        log.warn("发送完毕");
                    }else if(objects[1].equals("room")){
                        socket.emit("testDataEvent" ,"测试OK啦，你发的是wanghuan");
                        log.warn("发送完毕");
                    }
                }
            });

            socket.on("push_data_event", objects -> log.debug("服务端:" + objects[0].toString()));

            socket.send("ok...");

//            while (true) {
//                Thread.sleep(30000);
//                log.warn("暂定结束");
//                // 自定义事件`push_data_event` -> 向服务端发送消息
////                socket.emit("push_data_event", "发送数据 " + LocalDate.now());
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
