package android.latest_android_project_marshmallow;

/**
 * Created by ABVN237 on 6/18/2016.
 */
public class PersonProfile {
    String name;
    String surname;
    int picture;
    int picture2;
    int picture3;
    int picture4;
    int picture5;
    //The constructor for setting the values
    public PersonProfile(int picture,int picture2,int picture3,int picture4,int picture5){
        /*this.name=name;
        this.surname=surname;*/
        this.picture=picture;
        this.picture2=picture2;
        this.picture3=picture3;
        this.picture4=picture4;
        this.picture5=picture5;
    }
    //The method for returning the name
    public String getName(){
        return this.name;
    }
    //The method for returning the surname
    public String getSurname(){
        return this.surname;
    }
    //the method for returning the picture
    public int getPicture(){
        return this.picture;
    }
    public int getPicture2(){
        return this.picture2;
    }

    public int getPicture3(){
        return this.picture3;
    }
    public int getPicture4(){
        return this.picture4;
    }
    public int getPicture5(){
        return this.picture5;
    }
}

