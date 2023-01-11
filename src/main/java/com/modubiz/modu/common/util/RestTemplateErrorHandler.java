package com.modubiz.modu.common.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

public class RestTemplateErrorHandler implements ResponseErrorHandler {

    private static final Logger log = LogManager.getLogger(RestTemplateErrorHandler.class);

    @Override
    public boolean hasError(ClientHttpResponse clientHttpResponse) throws IOException {
        return clientHttpResponse.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR
                || clientHttpResponse.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR;
    }

    @Override
    public void handleError(ClientHttpResponse clientHttpResponse) throws IOException {
        log.error("RestTemplateError : {}", clientHttpResponse);
        if(clientHttpResponse.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR){

        } else if (clientHttpResponse.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR){
            if(clientHttpResponse.getStatusCode()==HttpStatus.NOT_FOUND){
//                throw new NotFoundException();
            }
        }
    }
}
