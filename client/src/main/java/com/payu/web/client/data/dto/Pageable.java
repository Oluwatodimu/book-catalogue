package com.payu.web.client.data.dto;

import lombok.Data;

@Data
public class Pageable {
	private boolean paged;
	private int pageNumber;
	private int offset;
	private int pageSize;
	private boolean unpaged;
	private Sort sort;
}