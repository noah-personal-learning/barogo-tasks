package com.barogo.api.global.error;

import java.util.HashMap;

public enum Errors {

    NOT_FOUND_USER("존재하지 않는 사용자 정보", "다른 아이디와 비밀번호로 다시 시도해주세요."),
    NOT_FOUND_ORDER("존재하지 않는 주문 정보", "다른 주문 정보로 다시 시도해주세요."),
    NOT_FOUND_DELIVERY("존재하지 않는 배송 정보", "다른 배송 정보로 다시 시도해주세요."),
    NOT_UPDATE_ADDRESS("주소 변경 실패", "주문 배송이 시작되어 변경이 불가합니다."),

    ALREADY_USER("이미 가입되어 있는 아이디", "다른 아이디로 다시 시도해주세요."),

    INVALID_PASSWORD("잘못된 패스워드", "비밀번호는 영어 대문자, 소문자, 특수문자 중 3종류 이상으로 12자리 이상의 문자열로 생성해야합니다."),
    INVALID_FORMAT("잘못된 요청", "파라미터를 확인해주세요."),

    MAX_BEFORE_DAY("검색 기간 초과", "최대 검색 기간은 3일 입니다.")
    ;

    private final String code;
    private final String description;

    Errors(String code, String description) {
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
