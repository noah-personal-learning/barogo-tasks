package com.barogo.api.global.common;

import com.sun.istack.NotNull;
import lombok.*;
import org.springframework.validation.annotation.Validated;

@Builder
@Data
@Validated
public class Response {

    @NotNull
    @Builder.Default
    private String code = "0000";

    @NotNull
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