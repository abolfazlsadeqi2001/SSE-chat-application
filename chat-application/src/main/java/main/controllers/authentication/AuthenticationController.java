package main.controllers.authentication;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import main.generals.authentication.Authenticator;
import main.generals.authentication.models.User;

@Controller
public class AuthenticationController {
	
	private static final String AUTHENTICATION_PATH = "/authentication";
	private static final String CHAT_ROOM_PATH = "/chat";
	private static final String USER_NAME_COOKKIE_NAME = "username";
	private static final String PASSWORD_COOKKIE_NAME = "password";
	private static final int COOKIES_LIFE_TIME = 4 * 24 * 60 * 60;
	
	@RequestMapping("/login")
	public String login(@RequestParam(name="username",required=true) String userName,
			@RequestParam(name="password",required=true) String password,
			HttpServletResponse res,
			Model model) {
		try {
			User user = new User();
			user.setUserName(userName);
			user.setPassword(password);
			
			Authenticator.login(user);
			
			Cookie userNameCookie = new Cookie(USER_NAME_COOKKIE_NAME,user.getUserName());
			Cookie passwordCookie = new Cookie(PASSWORD_COOKKIE_NAME,user.getPassword());
			
			userNameCookie.setMaxAge(COOKIES_LIFE_TIME);
			passwordCookie.setMaxAge(COOKIES_LIFE_TIME);
			
			res.addCookie(userNameCookie);
			res.addCookie(passwordCookie);
			
			return CHAT_ROOM_PATH;
		}catch(Exception e) {
			model.addAttribute("error_message", e.getMessage());
			return AUTHENTICATION_PATH;
		}
	}
	
	@RequestMapping("/register")
	public String register(@RequestParam(name="username",required=true) String userName,
			@RequestParam(name="password",required=true) String password,
			HttpServletResponse res,
			Model model) {
		try {
			User user = new User();
			user.setUserName(userName);
			user.setPassword(password);
			
			Authenticator.register(user);
			
			Cookie userNameCookie = new Cookie(USER_NAME_COOKKIE_NAME,user.getUserName());
			Cookie passwordCookie = new Cookie(PASSWORD_COOKKIE_NAME,user.getPassword());
			
			userNameCookie.setMaxAge(COOKIES_LIFE_TIME);
			passwordCookie.setMaxAge(COOKIES_LIFE_TIME);
			
			res.addCookie(userNameCookie);
			res.addCookie(passwordCookie);
			
			return CHAT_ROOM_PATH;
		}catch(Exception e) {
			model.addAttribute("error_message", e.getMessage());
			return AUTHENTICATION_PATH;
		}
	}
	
}
