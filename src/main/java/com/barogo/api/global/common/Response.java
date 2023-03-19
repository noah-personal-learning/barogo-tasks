package com.barogo.api.global.common;

import lombok.Builder;
import lombok.Getter;
import org.springframework.validation.annotation.Validated;

@Builder
@Getter
@Validated
public class Response {

    @Builder.Default
    private String code = "0000";

    @Builder.Default
    private String message = "success";

    @Builder(builderMethodName = "successResponse")
    public Response() {
        this.code = "0000";
        this.message = "success";
    }

    @Builder(builderMethodName = "exceptionResponse")
    public Response(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
