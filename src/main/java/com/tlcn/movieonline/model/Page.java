package com.tlcn.movieonline.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Page<T> {
    private int totalPage;
    private List<T> lstData;

    public Page(int totalPage, List<T> lstData) {
        this.totalPage = totalPage;
        this.lstData = lstData;
    }
}
