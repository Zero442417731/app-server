package com.example.wzs.myapplication.event;


public class MessageEvent<T> {
    public String friendUserId;

    public T messageContent;

    public MessageEvent(String friendUserId, T messageContent) {
        this.friendUserId = friendUserId;
        this.messageContent = messageContent;
    }

    public MessageEvent(String friendUserId) {
        this.friendUserId = friendUserId;
    }

    public T getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(T messageContent) {
        this.messageContent = messageContent;
    }

    public String getFriendUserId() {
        return friendUserId;
    }

    public void setFriendUserId(String friendUserId) {
        this.friendUserId = friendUserId;
    }
}
