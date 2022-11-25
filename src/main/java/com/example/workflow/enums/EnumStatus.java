package com.example.workflow.enums;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public enum EnumStatus {

    FIZIKI_SHEXS(1,"Fiziki shexs"),
    HUQUQI_SHEXS(2,"Huquqi shexs");

    private int code;
    private String  status;

    EnumStatus(int code, String  status) {
        this.code = code;
        this.status = status;
    }
}
