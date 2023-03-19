package com.barogo.api.global.error;

import java.util.HashMap;

public enum Errors {

    NOT_FOUND_USER("존재하지 않는 사용자 정보", "다른 아이디와 밀번호로 다시 시도해주세요."),
    ALREADY_USER("이미 가입되어 있는 아이디", "다른 아이디로 다시 시도해주세요."),
    ;

    private final String code;
    private final String description;

    private Errors(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getCode() {
        return code;
    }

    public static HashMap<String, String> getErrors() {

        HashMap<String, String> errors = new HashMap<String, String>();

        for (Errors error : Errors.values()) {
            errors.put(error.getCode(), error.getDescription());
        }

        return errors;
    }
}
