package main.generals.authentication;

import java.sql.ResultSet;

import main.generals.authentication.exceptions.IncorrectPasswordException;
import main.generals.authentication.exceptions.UserNameNotFoundException;
import main.generals.authentication.models.User;
import main.generals.database.maridb.MariaDBConnector;

// TODO write document
// TODO write tests
public class Authenticator {
	public static User login(User user) throws Exception {
		String selectTemplate = "SELECT username,password FROM users WHERE username='%s'";
		String selectQuery = String.format(selectTemplate, user.getUserName());
		ResultSet set = MariaDBConnector.get(selectQuery);
		if (set.next()) {
			if (set.getString("password").equals(user.getPassword()))
				return user;
			else
				throw new IncorrectPasswordException();
		} else {
			throw new UserNameNotFoundException();
		}
	}
}
