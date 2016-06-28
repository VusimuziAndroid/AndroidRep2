package android.latest_android_project_marshmallow;

/**
 * Created by Vusi Ngwenya on 6/23/2016.
 */
public class ProfileLikes  {
    int picture;
    String likes;

    public ProfileLikes(int picture,String likes){
        this.picture=picture;
        this.likes=likes;
    }
    public int getPicture(){
        return this.picture;
    }
    public String getLikes(){
        return this.likes;
    }
}
