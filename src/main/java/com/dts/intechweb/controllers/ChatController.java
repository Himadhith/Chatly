package com.dts.intechweb.controllers;
import com.dts.intechweb.model.ChatMessage;
import com.dts.intechweb.services.ChatMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import com.dts.intechweb.services.FileStorageService;
import java.util.UUID;
import java.util.Base64;
import java.nio.charset.StandardCharsets;


@Controller
public class ChatController {
    private final ChatMessageService chatMessageService;
    
    @Autowired
    private final FileStorageService fileStorageService;

    @Autowired
    public ChatController(ChatMessageService chatMessageService,  FileStorageService fileStorageService) {
        this.chatMessageService = chatMessageService;
        this.fileStorageService = fileStorageService;
    }

    @SendTo("/topic/public")
    @MessageMapping("${chat.app.send-message-mapping}")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        System.out.println(chatMessage);
        chatMessageService.saveMessage(chatMessage);
        return chatMessage;
    }


    @SendTo("/topic/public")
    @MessageMapping("${chat.app.add-user-mapping}")
    public ChatMessage addUser(@Payload ChatMessage chatMessage) {
        chatMessageService.saveMessage(chatMessage);
        return chatMessage;
    }

    @RequestMapping({"/","/index","/index.html","/chat"})
    public String index(Model model,
                        @Value("${chat.app.ws-endpoint-path}") String wsEndpointPath,
                        @Value("${chat.app.topic.public}") String topicPublic,
                        @Value("${chat.app.application-destination-prefix}") String applicationDestinationPrefix,
                        @Value("${chat.app.add-user-mapping}") String addUserMapping,
                        @Value("${chat.app.send-message-mapping}") String sendMessageMapping,
                        @Value("${chat.app.send-file-mapping}") String sendFileMapping) 
        {
        model.addAttribute("wsEndpointPath", wsEndpointPath.toCharArray());
        model.addAttribute("topicPublic", topicPublic);
        model.addAttribute("addUserMapping", applicationDestinationPrefix + addUserMapping);
        model.addAttribute("sendMessageMapping", applicationDestinationPrefix + sendMessageMapping);
        model.addAttribute("sendFileMapping", applicationDestinationPrefix + sendFileMapping);
        return "chat";
    }


@SendTo("/topic/public")
// @MessageMapping("${chat.app.send-file-mapping}")
    // public ChatMessage sendFileToAllExceptSender(@Payload ChatMessage chatMessage) {
    //     chatMessage.setType(ChatMessage.MessageType.FILE);
    //     chatMessage.setContent("File shared by " + chatMessage.getAuthor() + ": " + chatMessage.getFileName());
    //     if (chatMessage.getFileData() != null) {
    //         chatMessage.setFileType(chatMessage.getFileType());
    //         System.out.println(chatMessage.getFileData());
    //         // byte[] fileData = Base64.getDecoder().decode(chatMessage.getFileData());
    //         // byte[] fileData = Base64.getMimeDecoder().decode(chatMessage.getFileData());
    //         String base64String = new String(chatMessage.getFileData(), StandardCharsets.UTF_8);
    //         String cleanedBase64 = base64String.replaceAll("[\\s+]", "");
    //         byte[] fileData = Base64.getDecoder().decode(cleanedBase64);

    //         //String filename = UUID.randomUUID().toString() + "." + chatMessage.getFileName();
    //         Resource fileResource = new ByteArrayResource(fileData);
           
    
    //         // chatMessage.setFileData(fileData);
    //         chatMessage.setFileName(chatMessage.getFileName());
    //        fileStorageService.storeFile(chatMessage.getFileName(), fileResource);
    //    }
    
    //     chatMessageService.saveMessage(chatMessage);
    //     return chatMessage;
    // }
    @MessageMapping("${chat.app.send-file-mapping}")
public ChatMessage sendFileToAllExceptSender(@Payload ChatMessage chatMessage) {
    chatMessage.setType(ChatMessage.MessageType.FILE);
    chatMessage.setContent("File shared by " + chatMessage.getAuthor() + ": " + chatMessage.getFileName());
    byte[] fileData = chatMessage.getFileData();

    if (fileData != null) {
        chatMessage.setFileType(chatMessage.getFileType());

        // Store the file
        Resource fileResource = new ByteArrayResource(fileData);
        chatMessage.setFileName(chatMessage.getFileName());
        fileStorageService.storeFile(chatMessage.getFileName(), fileResource);
    }

    chatMessageService.saveMessage(chatMessage);
    return chatMessage;
}

    
    }

