package android.finaspellapp;

/**
 * Created by Vusi Ngwenya on 6/14/2016.
 */

public class PersonProfile {
    String name;
    String surname;
    int picture;
    //The constructor for setting the values
    public PersonProfile(String name,String surname,int picture){
        this.name=name;
        this.surname=surname;
        this.picture=picture;
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
}
