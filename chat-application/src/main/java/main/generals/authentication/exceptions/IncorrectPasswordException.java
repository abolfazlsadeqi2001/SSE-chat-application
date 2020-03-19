package main.generals.authentication.exceptions;

public class IncorrectPasswordException extends Exception {
	public IncorrectPasswordException() {
		super("incorrect password");
	}
}
