package com.barogo.api.global.error;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
public class CustomException extends Exception {

    private String code;

    public CustomException(String code, String msg) {
        super(msg);
        this.code = code;
    }

}
