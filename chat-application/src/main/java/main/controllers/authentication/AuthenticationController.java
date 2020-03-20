package main.controllers.authentication;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import main.generals.authentication.Authenticator;
import main.generals.authentication.models.User;

// TODO add session
// TODO add filter by session then by cookie
// TODO write document
// TODO write tag because of long length of methods
@Controller
public class AuthenticationController {
	
	public static final String AUTHENTICATION_PATH = "/authentication";
	public static final String CHAT_ROOM_PATH = "/chat/index.html";
	public static final String USER_NAME_COOKKIE_NAME = "userName";
	public static final String PASSWORD_COOKKIE_NAME = "password";
	public static final String ERROR_MESSAGE_ATTRIBUTE = "error_message";
	public static final int COOKIES_LIFE_TIME = 4 * 24 * 60 * 60;
	
	@RequestMapping("/authentication")
	public String authentication() {
		return AUTHENTICATION_PATH;
	}
	
	@RequestMapping("/login")
	public String login(@RequestParam(name=USER_NAME_COOKKIE_NAME,required=true) String userName,
			@RequestParam(name= PASSWORD_COOKKIE_NAME,required=true) String password,
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
			
			model.addAttribute(user);
			
			return "redirect:"+CHAT_ROOM_PATH;
		}catch(Exception e) {
			model.addAttribute(ERROR_MESSAGE_ATTRIBUTE, e.getMessage());
			return AUTHENTICATION_PATH;
		}
	}
	
	@RequestMapping("/register")
	public String register(@RequestParam(name=USER_NAME_COOKKIE_NAME,required=true) String userName,
			@RequestParam(name=PASSWORD_COOKKIE_NAME,required=true) String password,
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
			
			model.addAttribute(user);
			
			return "redirect:"+CHAT_ROOM_PATH;
		}catch(Exception e) {
			model.addAttribute(ERROR_MESSAGE_ATTRIBUTE, e.getMessage());
			return AUTHENTICATION_PATH;
		}
	}
	
}
