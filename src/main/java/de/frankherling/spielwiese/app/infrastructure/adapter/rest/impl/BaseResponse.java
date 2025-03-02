package de.frankherling.spielwiese.app.infrastructure.adapter.rest.impl;

import lombok.Data;

@Data
public class BaseResponse {
    private Object data;
    private String message;

    public BaseResponse(Object data, String message) {
        this.data = data;
        this.message = message;
    }
}
