package android.latest_android_project_marshmallow;

/**
 * Created by Vusi Ngwenya on 6/18/2016.
 */
public class User {
    String name;
    String surname;
    String username;
    String password;
    String confirmPassword;
    public User(String name,String surname,String username,String password,String confirmPassword){
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }
    //The method for returing the name of the user
    public String getName(){
        return this.name;
    }
    //The method for returning the surname of the user
    public String getSurname(){
        return this.surname;
    }
    //The method for returning the username
    public String getUsername(){
        return this.username;
    }
    //The method for returning the password
    public String getPassword(){
        return this.password;
    }
    //The method for returning the confirm password
    public String getConfirmPassword(){
        return this.confirmPassword;
    }
}

