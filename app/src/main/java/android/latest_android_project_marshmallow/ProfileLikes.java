package android.latest_android_project_marshmallow;

/**
 * Created by Vusi Ngwenya on 6/23/2016.
 */
public class ProfileLikes  {
    int picture;
    int icon;
    String likes;
    public ProfileLikes(int picture,int icon,String likes){
        this.picture=picture;
        this.icon=icon;
        this.likes=likes;
    }
    //The method for returning the picture
    public int getPicture(){
        return this.picture;
    }
    //The method for returning the icon
    public int getIcon(){return this.icon;}
    //The method for returning the likes
    public String getLikes(){
        return this.likes;
    }
}
