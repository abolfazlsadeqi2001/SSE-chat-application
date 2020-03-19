package main.generals.authentication.exceptions;

public class UserNameNotFoundException extends Exception {
	public UserNameNotFoundException() {
		super("the username not found");
	}
}
