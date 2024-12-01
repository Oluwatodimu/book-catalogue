package com.payu.web.client.service;

import com.payu.web.client.data.dto.BaseResponse;
import com.payu.web.client.data.dto.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ManagementClientTest {

    @Mock private Client mockClient;
    @Mock private WebTarget mockWebTarget;
    @Mock private Invocation.Builder mockInvocationBuilder;
    @Mock private Response mockResponse;
    @InjectMocks private ManagementClient managementClient;

    @BeforeEach
    public void setup() {
        managementClient = new ManagementClient("http://localhost:8080", mockClient);
    }

    @Test
    void testListAllBooks() {
        when(mockClient.target(anyString())).thenReturn(mockWebTarget);
        when(mockWebTarget.request(anyString())).thenReturn(mockInvocationBuilder);
        when(mockInvocationBuilder.get()).thenReturn(mockResponse);
        when(mockResponse.readEntity(BaseResponse.class)).thenReturn(new BaseResponse());

        BaseResponse response = managementClient.listAllBooks();
        assertNotNull(response);
        verify(mockInvocationBuilder, times(1)).get();
    }

    @Test
    void testFindBookByIsbnNumber() {
        String isbn = "1-23456-789-0";
        when(mockClient.target(anyString())).thenReturn(mockWebTarget);
        when(mockWebTarget.path(isbn)).thenReturn(mockWebTarget);
        when(mockWebTarget.request(anyString())).thenReturn(mockInvocationBuilder);
        when(mockInvocationBuilder.get()).thenReturn(mockResponse);
        when(mockResponse.readEntity(BaseResponse.class)).thenReturn(new BaseResponse());

        BaseResponse response = managementClient.findBookByIsbnNumber(isbn);
        assertNotNull(response);
        verify(mockInvocationBuilder, times(1)).get();
    }

    @Test
    void testAddNewBook() {
        Book book = new Book();
        when(mockClient.target(anyString())).thenReturn(mockWebTarget);
        when(mockWebTarget.request(anyString())).thenReturn(mockInvocationBuilder);
        when(mockInvocationBuilder.post(any())).thenReturn(mockResponse);
        when(mockResponse.readEntity(BaseResponse.class)).thenReturn(new BaseResponse());

        BaseResponse response = managementClient.addNewBook(book);
        assertNotNull(response);
        verify(mockInvocationBuilder, times(1)).post(any());
    }

    @Test
    void testEditExistingBook() {
        Book book = new Book();
        when(mockClient.target(anyString())).thenReturn(mockWebTarget);
        when(mockWebTarget.request(anyString())).thenReturn(mockInvocationBuilder);
        when(mockInvocationBuilder.put(any())).thenReturn(mockResponse);
        when(mockResponse.readEntity(BaseResponse.class)).thenReturn(new BaseResponse());

        BaseResponse response = managementClient.editExistingBook(book);
        assertNotNull(response);
        verify(mockInvocationBuilder, times(1)).put(any());
    }

    @Test
    void testDeleteCurrentBook() {
        String isbn = "1-23456-789-0";
        when(mockClient.target(anyString())).thenReturn(mockWebTarget);
        when(mockWebTarget.path(isbn)).thenReturn(mockWebTarget);
        when(mockWebTarget.request()).thenReturn(mockInvocationBuilder);
        when(mockInvocationBuilder.delete()).thenReturn(mockResponse);
        when(mockResponse.getStatus()).thenReturn(204);

        Integer status = managementClient.deleteCurrentBook(isbn);
        assertEquals(204, status);
        verify(mockInvocationBuilder, times(1)).delete();
    }

    @Test
    void testGetPaginatedList() {
        int pageNumber = 1;
        when(mockClient.target(anyString())).thenReturn(mockWebTarget);
        when(mockWebTarget.path("page/" + pageNumber)).thenReturn(mockWebTarget);
        when(mockWebTarget.request(anyString())).thenReturn(mockInvocationBuilder);
        when(mockInvocationBuilder.get()).thenReturn(mockResponse);
        when(mockResponse.readEntity(BaseResponse.class)).thenReturn(new BaseResponse());

        BaseResponse response = managementClient.getPaginatedList(pageNumber);
        assertNotNull(response);
        verify(mockInvocationBuilder, times(1)).get();
    }
}