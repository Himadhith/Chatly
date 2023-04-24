package com.dts.intechweb.repositories;

import com.dts.intechweb.model.ChatMessage;
import com.dts.intechweb.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
}
