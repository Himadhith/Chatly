package com.dts.intechweb.listeners;


import com.dts.intechweb.model.ChatMessage;
import com.dts.intechweb.model.User;
import com.dts.intechweb.services.UserService;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.util.Date;

@Component
public class WebSocketEventListener {

    private final SimpMessageSendingOperations messagingTemplate;
    private final UserService userService;
    public WebSocketEventListener(SimpMessageSendingOperations messagingTemplate, UserService userService) {
        this.messagingTemplate = messagingTemplate;
        this.userService = userService;
    }

    @EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event) {

    }
    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        if (event.getUser() != null) {
            ChatMessage chatMessage = new ChatMessage();
            chatMessage.setType(ChatMessage.MessageType.LEAVE);
            chatMessage.setTimestamp(new Date());
            chatMessage.setAuthor(userService.findByUserName(event.getUser().getName()).orElse(null));

            messagingTemplate.convertAndSend("/topic/public", chatMessage);
        }
    }
}
