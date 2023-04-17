package org.learning.springilmiofotoalbum.controller;

import org.learning.springilmiofotoalbum.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/messages")
public class MessageController {
    @Autowired
    private MessageService messageService;

    @GetMapping
    public String index(Model model){
        model.addAttribute("messages", messageService.getAllMessages());
        return "messages/index";
    }
}
