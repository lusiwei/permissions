package com.lusiwei.common;


import lombok.*;

import java.util.List;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageCommon<T> {

    private Integer curPage;

    private Integer pageSize=3;

    private long totalCount;

    private Integer pageCount;

    private List<T> pageList;

    public Integer getPageCount() {
        return (int) Math.ceil((totalCount+0.0)/pageSize);
    }
}
