package com.sliora.boardserver.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {
    public enum SortStatus {
        CATEGORIES, NEWEST, OLDEST, HIGHPRICE, LOWPRICE, GRADE
    }
    private int id;
    private String name;
    private SortStatus sortStatus;
    private int searchCount;
    private int pagingStartOffset;

}
