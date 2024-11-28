package com.payu.web.client.data.dto;

import java.util.List;

import com.payu.web.client.data.Book;
import lombok.Data;

@Data
public class PaginatedBookList{
	private int number;
	private boolean last;
	private int size;
	private int numberOfElements;
	private int totalPages;
	private Pageable pageable;
	private Sort sort;
	private List<Book> content;
	private boolean first;
	private int totalElements;
	private boolean empty;
}