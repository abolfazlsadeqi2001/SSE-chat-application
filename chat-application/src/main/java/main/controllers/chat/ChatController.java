package main.controllers.chat;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import main.controllers.authentication.AuthenticationController;

@RestController
public class ChatController {
	// TODO save messages into database
	public static final String MESSAGE_PARAM = "message";
	
	Set<SseEmitter> emitters = new HashSet<SseEmitter>();
	
	@RequestMapping("/chat/sse")
	public SseEmitter getEmitter() {
		SseEmitter emitter = new SseEmitter();
		
		emitters.add(emitter);
		emitter.onCompletion(()->{
			emitters.remove(emitter);
		});
		
		return emitter;
	}
	
	@RequestMapping("/new/message")
	public void newMessage(@CookieValue(name = AuthenticationController.USER_NAME_COOKKIE_NAME,required = true) String userName,
			@RequestParam(name=MESSAGE_PARAM,required=true) String message) {
		for (SseEmitter emitter : emitters) {
			try {
				emitter.send(SseEmitter.event().data(userName).data(message));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
