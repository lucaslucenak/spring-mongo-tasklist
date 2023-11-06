package com.unifacisa.tasklist.enums;

public enum StatusEnum {
    TO_DO("TO DO"),
    DOING("DOING"),
    DONE("DONE");

    private String status;

    StatusEnum(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
