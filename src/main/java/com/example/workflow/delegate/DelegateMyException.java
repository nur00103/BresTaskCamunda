package com.example.workflow.delegate;

import com.example.workflow.exception.MyException;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component("delegateMyException")
public class DelegateMyException implements JavaDelegate {


    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String code= (String) delegateExecution.getVariable("globalErrorCode");
        String message= (String) delegateExecution.getVariable("globalErrorMessage");
        throw new MyException(Integer.valueOf(code),message);
    }
}
