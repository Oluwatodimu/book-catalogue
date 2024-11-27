package com.payu.backend.management.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class PaginationUtils {

    public static final int PAGE = 0;
    public static final int SIZE = 30;

    public static Pageable createSortedPageableObject(Integer pageNumber, Integer pageSize) {
        Pageable paging;

        if (pageNumber == null || pageSize == null) {
            paging = PageRequest.of(PAGE, SIZE);
        } else {
            paging = PageRequest.of(pageNumber, pageSize);
        }

        return  paging;
    }
}
