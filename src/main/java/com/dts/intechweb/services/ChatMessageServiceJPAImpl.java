package com.dts.intechweb.services;

import com.dts.intechweb.model.ChatMessage;
import com.dts.intechweb.repositories.ChatMessageRepository;
import org.springframework.stereotype.Service;

@Service
public class ChatMessageServiceJPAImpl implements ChatMessageService {
    private final ChatMessageRepository chatMessageRepository;

    public ChatMessageServiceJPAImpl(ChatMessageRepository chatMessageRepository) {
        this.chatMessageRepository = chatMessageRepository;
    }

    @Override
    public ChatMessage saveMessage(ChatMessage message) {
        return chatMessageRepository.save(message);
    }
}
