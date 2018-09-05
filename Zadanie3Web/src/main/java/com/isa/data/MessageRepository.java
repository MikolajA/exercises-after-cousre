package com.isa.data;

import javax.ejb.Singleton;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Singleton
public class MessageRepository {

    private Map<LocalDateTime, String> messages = new ConcurrentHashMap<>();

    public void addMessage(String s) {messages.put(LocalDateTime.now(), s); }

    public Map<LocalDateTime, String> getMessages() {return messages; }

}
