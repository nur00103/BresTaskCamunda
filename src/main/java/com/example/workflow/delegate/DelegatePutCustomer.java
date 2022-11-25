package com.example.workflow.delegate;

import com.example.bookshopapi.dto.request.CustomerRequest;
import com.example.bookshopapi.service.CustomerService;
import com.example.workflow.dto.request.CustomerRequest;
import com.example.workflow.service.CustomerService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component("delegatePutCustomer")
public class DelegatePutCustomer implements JavaDelegate {

    private final CustomerService customerService;

    public DelegatePutCustomer(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        CustomerRequest customerRequest=new CustomerRequest();
        customerRequest.setName((String) delegateExecution.getVariable("name"));
        customerRequest.setSurname((String) delegateExecution.getVariable("surname"));
        customerRequest.setFullname((String) delegateExecution.getVariable("fullname"));
        customerRequest.setFin((String) delegateExecution.getVariable("fin"));
        customerRequest.setType((String) delegateExecution.getVariable("type"));
        Long customerId= (Long) delegateExecution.getVariable("customerId");
        delegateExecution.setVariable("putCustomerDa",customerService.updateCustomer(customerId,customerRequest));
    }
}
