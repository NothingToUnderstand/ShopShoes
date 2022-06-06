package com.viktor.task.shoes.manager.web.dto;

import java.util.Collection;

import com.viktor.task.shoes.manager.web.model.User;

public class ResponseUser {
    private final Collection<User> userList;
    private final int totalPages;
    private final long totalItems;

    public ResponseUser(Collection<User> userList, int totalPages, long totalItems) {
        this.userList = userList;
        this.totalPages = totalPages;
        this.totalItems = totalItems;
    }

    public Collection<User> getUserList() {
        return this.userList;
    }


    public int getTotalPages() {
        return this.totalPages;
    }


    public long getTotalItems() {
        return this.totalItems;
    }


}
