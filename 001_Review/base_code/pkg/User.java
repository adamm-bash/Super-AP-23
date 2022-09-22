package pkg;
import java.util.*;
import java.io.*;

public class User {
	public String username;
	public String password;
	// Creates a User with empty name and password.
	public User() {
		username = null;
		password = null;
	}

	// Creates a User with given username and password.
	public User(String usr, String pwd) {
		username = usr;
		password = pwd;

	}

	// Returns the username
	public String getUsername(){
		return username;
	}

	public String getPassword(){
		return password;
		
	}

	// Returns true if the stored username/password matches the parameters. Otherwise returns false.
	// Note that, even with a User with empty name and password, this is actually a valid User object (it is the default User), 
	// This function must still return false if given an empty username string.  
	public boolean check(String usr, String psd){
	
		if(!username.equals(null) && usr.equals(username) && psd.equals(password))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	// Sets a new password.
	// This function should only set the password if the current (old) password is passed in.
	// Also, a default User cannot have its password changed. 
	// Return true if password changed, return false if not.
	public boolean setPassword(String oldPass, String newPass){
		//i like to think of this as the back-end of the setPassword method
		//the one in BBoard interacts with the User
		//this one makes sure that the old password is right and changes the password on the code side
		if(username == null)
		{
			System.out.println("Default users are not allowed to change passwords.");
			return false;
		}
		else if(oldPass.equals(password))
		{
			password = newPass;
			return true;
		}
		else
		{
			return false;
		}
	}
}
