package android.latest_android_project_marshmallow;

/**
 * Created by Vusi Ngwenya on 6/21/2016.
 */
public class Message {
    String username;
    String message;
    String messageType;
    //The constructor for the message sent when telling the story
    public Message(String message,String messageType){
        this.username=username;
        this.message=message;
        this.messageType=messageType;
    }
    public String getUsername(){
        return this.username;
    }
    public String getMessage(){
        return this.message;
    }
    public String getMessageType(){
        return this.messageType;
    }
}
