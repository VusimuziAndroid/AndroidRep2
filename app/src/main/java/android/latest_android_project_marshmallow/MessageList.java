package android.latest_android_project_marshmallow;

import android.graphics.Bitmap;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.sql.Blob;

/**
 * Created by Vusi Ngwenya on 6/27/2016.
 */
public class MessageList {
    String message;
    String username;
    String messageType;
    int profilePicture;
    public MessageList(String username,String message,String messageType,int profilePicture){
        this.username=username;
        this.message=message;
        this.messageType=messageType;
        this.profilePicture = profilePicture;
    }
    //The method for returning the messages
    public String getMessage(){
        return this.message;
    }
    public String getMessageType(){
        return this.messageType;
    }
    //The method for returning the username
    public String getUsername(){
        return this.username;
    }
    //The method for returning the profile picture
    public int getProfilePicture(){
        return this.profilePicture;
    }
}
