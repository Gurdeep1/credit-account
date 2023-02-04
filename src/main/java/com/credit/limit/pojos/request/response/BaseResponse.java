package com.credit.limit.pojos.request.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class BaseResponse<T> implements Serializable {

    private static final long serialVersionUID = 1L;
    private String status;
    private T data;
    public BaseResponse(T data, String status) {
        this.status = status;
        this.data = data;
    }
}