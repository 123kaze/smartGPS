package com.sky.logistics.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageResponse<T> {

    private List<T> content;
    private Integer page;
    private Integer size;
    private Long totalElements;
    private Integer totalPages;

    public static <T> PageResponse<T> of(List<T> content, Integer page, Integer size) {
        int safePage = page == null || page < 1 ? 1 : page;
        int safeSize = size == null || size < 1 ? 20 : size;
        long total = content == null ? 0 : content.size();
        int pages = total == 0 ? 0 : (int) Math.ceil((double) total / safeSize);
        return new PageResponse<>(content, safePage, safeSize, total, pages);
    }
}
