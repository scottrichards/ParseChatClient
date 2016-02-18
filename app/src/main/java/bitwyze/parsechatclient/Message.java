package bitwyze.parsechatclient;

import com.parse.ParseClassName;
import com.parse.ParseObject;

import org.json.JSONArray;

/**
 * Created by srichard on 2/17/16.
 */
@ParseClassName("Message")

public class Message extends ParseObject {
    public static final String USER_NAME = "userName";
    public static final String MESSAGE_KEY = "message";

    public String getUserId() {
        return getString(USER_NAME);
    }

    public String getBody() {
        return getString(MESSAGE_KEY);
    }

    public void setUserId(String userId) {
        put(USER_NAME, userId);
    }

    public void setMessage(String body) {
        put(MESSAGE_KEY, body);
    }
}


//public class Message {
//    public String getMessageText() {
//        return messageText;
//    }
//
//    public String getUserName() {
//        return userName;
//    }
//
//    String messageText;
//    String userName;
//
//}
