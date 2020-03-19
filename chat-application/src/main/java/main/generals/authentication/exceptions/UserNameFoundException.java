package main.generals.authentication.exceptions;

public class UserNameFoundException extends Exception {
	public UserNameFoundException() {
		super("another username is in used");
	}
}
