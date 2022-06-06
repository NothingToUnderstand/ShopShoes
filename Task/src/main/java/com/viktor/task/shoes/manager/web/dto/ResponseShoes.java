package com.viktor.task.shoes.manager.web.dto;

import java.util.Collection;
import com.viktor.task.shoes.manager.web.model.Shoes;

public final class ResponseShoes {
    private final Collection<Shoes> listShoes;  //List and Set
    private final int totalPages;
    private final long totalItems;

    public Collection<Shoes> getListShoes() {
        return this.listShoes;
    }


    public int getTotalPages() {
        return this.totalPages;
    }


    public long getTotalItems() {
        return this.totalItems;
    }


    public ResponseShoes(Collection<Shoes> listShoes, int totalPages, long totalItems) {
        this.listShoes = listShoes;
        this.totalPages = totalPages;
        this.totalItems = totalItems;
    }

}
