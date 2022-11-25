package com.example.workflow.exception;

import com.example.workflow.enums.EnumStatus;
import com.example.workflow.enums.ExceptionEnum;

public class MyException extends RuntimeException {
    private final int code;
    private final String message;
    public MyException(ExceptionEnum enumStatus){
        super(enumStatus.getMessage());
        this.code= enumStatus.getCode();
        this.message= enumStatus.getMessage();
    }
    public MyException(int code, String message){
        super(message);
        this.code= code;
        this.message= message;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
