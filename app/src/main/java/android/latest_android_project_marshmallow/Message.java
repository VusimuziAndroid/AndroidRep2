package android.latest_android_project_marshmallow;

import java.sql.Blob;

/**
 * Created by Vusi Ngwenya on 6/21/2016.
 */
public class Message {
    String username;
    String message;
    String messageType;
    int picture;
    public Message(String username,String message,String messageType,int picture){
        this.username=username;
        this.message=message;
        this.messageType=messageType;
        this.picture=picture;
    }
    //The method for returning  the username
    public String getUsername(){
        return this.username;
    }
    //The method for returning the message
    public String getMessage(){
        return this.message;
    }
    //The method for returning the message type
    public String getMessageType(){
        return this.messageType;
    }
    //The method for returning the picture
    public int getPicture(){return this.picture;}
}
