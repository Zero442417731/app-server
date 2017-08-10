package com.example.wzs.myapplication.event;


public class MessageEvent {
    public String friendUserId;

    public Object messageContent;

    public MessageEvent(String friendUserId, Object messageContent) {
        this.friendUserId = friendUserId;
        this.messageContent = messageContent;
    }

    public Object getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(Object messageContent) {
        this.messageContent = messageContent;
    }

    public String getFriendUserId() {

        return friendUserId;
    }

    public void setFriendUserId(String friendUserId) {
        this.friendUserId = friendUserId;
    }
}
