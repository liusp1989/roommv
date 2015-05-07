package com.liusp.roommv.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by jackyliu on 2015/3/20.
 */
@Controller(value = "chatRoomController")
public class ChatRoomController {
    @RequestMapping(value = "chatRoom")
    public  String chatRoom(){
        return "chatroom/chatRoom";
    }
}
