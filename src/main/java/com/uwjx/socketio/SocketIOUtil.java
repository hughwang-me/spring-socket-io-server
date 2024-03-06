package com.uwjx.socketio;

import com.corundumstudio.socketio.SocketIOClient;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

public class SocketIOUtil {

    public static String getIp(SocketIOClient client){
        return client.getRemoteAddress().toString();
    }

    public static String getId(SocketIOClient client){
        return client.getSessionId().toString();
    }

    private String getParamsByKey(SocketIOClient client , String key) {
        Map<String, List<String>> params = client.getHandshakeData().getUrlParams();
        List<String> userIdList = params.get(key);
        if (!CollectionUtils.isEmpty(userIdList)) {
            return userIdList.get(0);
        }
        return null;
    }

}
