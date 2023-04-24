package com.dts.intechweb.services;

import com.dts.intechweb.model.ChatMessage;

public interface ChatMessageService {
    ChatMessage saveMessage(ChatMessage message);
}
