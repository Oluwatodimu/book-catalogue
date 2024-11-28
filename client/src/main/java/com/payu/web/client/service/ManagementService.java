package com.payu.web.client.service;

import com.payu.web.client.data.dto.Book;
import com.payu.web.client.data.dto.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

@Component
@RequiredArgsConstructor
public class ManagementService {

    @Value("${management.base-url}")
    private String baseUrl;

    private final Client client;

    public BaseResponse listAllBooks() {
        try {

            Response response = client.target(baseUrl)
                    .request(MediaType.APPLICATION_JSON_VALUE)
                    .get();
            return response.readEntity(BaseResponse.class);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public BaseResponse findBookByIsbnNumber(String isbn) {
        try {

            Response response = client.target(baseUrl)
                    .path(isbn)
                    .request(MediaType.APPLICATION_JSON_VALUE)
                    .get();
            return response.readEntity(BaseResponse.class);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public BaseResponse addNewBook(Book request) {
        try {

            Response response = client.target(baseUrl)
                    .request(MediaType.APPLICATION_JSON_VALUE)
                    .post(Entity.entity(request, javax.ws.rs.core.MediaType.APPLICATION_JSON_TYPE));
            return response.readEntity(BaseResponse.class);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public BaseResponse editExistingBook(Book book) {
        try {

            Response response = client.target(baseUrl)
                    .request(MediaType.APPLICATION_JSON_VALUE)
                    .put(Entity.entity(book, javax.ws.rs.core.MediaType.APPLICATION_JSON_TYPE));
            return response.readEntity(BaseResponse.class);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Integer deleteCurrentBook(String isbn) {
        try {

            Response response = client.target(baseUrl)
                    .path(isbn)
                    .request()
                    .delete();
            return response.getStatus();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public BaseResponse getPaginatedList(int pageNumber) {
        try {

            Response response = client.target(baseUrl)
                    .path("page/" + pageNumber)
                    .request(MediaType.APPLICATION_JSON_VALUE)
                    .get();
            return response.readEntity(BaseResponse.class);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
