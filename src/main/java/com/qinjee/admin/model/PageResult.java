package com.qinjee.admin.model;

import com.github.pagehelper.Page;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
public class PageResult<T> implements Serializable {
    private List<T> list;

    private long total;

    public PageResult(List<T> list){
        if (list instanceof Page) {
            Page page = (Page)list;
            this.total = page.getTotal();
        }
        this.list = list;
    }


}
