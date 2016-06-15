package android.finaspellapp;

/**
 * Created by Vusi Ngwenya on 6/10/2016.
 */

//The class for saving the User information
public class User {

    String name; // declaring the variable for the name
    String surname; // declaring the variable for the surname
    String username; // declaring the variable for the username
    String password; // declaring the variable for the password
    String confirmPassword; // declaring the variable for the confirm password

    //The constructor for the User class
    public User(String name,String surname,String username,String password,String confirmPassword){

       /* name = this.name;
        surname =this.surname;
        username = this.username;
        password = this.password;
        confirmPassword=this.confirmPassword;*/
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
