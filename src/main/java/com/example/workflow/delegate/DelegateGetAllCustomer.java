package com.example.workflow.delegate;

import com.example.workflow.service.CustomerService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component("delegateGetAllCustomer")
public class DelegateGetAllCustomer implements JavaDelegate {

    private final CustomerService customerService;

    public DelegateGetAllCustomer(CustomerService customerService) {
        this.customerService = customerService;
    }


    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        delegateExecution.setVariable("getCustomers",customerService.getAllCustomer());
        System.out.println(delegateExecution.getVariable("Test"));
    }
}
