package android.latest_android_project_marshmallow;

import android.graphics.Bitmap;

/**
 * Created by Vusi Ngwenya on 6/28/2016.
 */
public class UploadedPicture {
    Bitmap bmp;

    public UploadedPicture(Bitmap bmp){
        this.bmp = bmp;
    }
    //The method for returning the bitmap picture
    public Bitmap getPicture(){
       return this.bmp;
    }
}
