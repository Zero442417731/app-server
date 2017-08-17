package com.example.wzs.myapplication.event;


public class MessageEvent<T> {
    public int friendUserId;

    public T messageContent;

    public MessageEvent(int friendUserId, T messageContent) {
        this.friendUserId = friendUserId;
        this.messageContent = messageContent;
    }

    public MessageEvent(int friendUserId) {
        this.friendUserId = friendUserId;
    }

    public T getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(T messageContent) {
        this.messageContent = messageContent;
    }

    public int getFriendUserId() {
        return friendUserId;
    }

    public void setFriendUserId(int friendUserId) {
        this.friendUserId = friendUserId;
    }
}
