package android.latest_android_project_marshmallow;

/**
 * Created by Vusi Ngwenya on 6/27/2016.
 */
public class MessageList {
    String message;
    String username;
    int profilePicture;

    public MessageList(int profilePicture,String username,String message){
        this.profilePicture = profilePicture;
        this.username=username;
        this.message=message;
    }
    //The method for returning the messages
    public String getMessage(){
        return this.message;
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
