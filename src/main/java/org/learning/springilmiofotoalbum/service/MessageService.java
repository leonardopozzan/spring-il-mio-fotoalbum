package org.learning.springilmiofotoalbum.service;

import org.learning.springilmiofotoalbum.model.Message;
import org.learning.springilmiofotoalbum.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {
    @Autowired
    MessageRepository messageRepository;

    public List<Message> getAllMessages(){
        return messageRepository.findAll();
    }

    public void createMessage(Message message) {
        Message newMessage = new Message();
        newMessage.setEmail(message.getEmail());
        newMessage.setText(message.getText());
        messageRepository.save(newMessage);
    }
}
