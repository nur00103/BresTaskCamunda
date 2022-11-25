package com.example.workflow.delegate;

import com.example.workflow.dto.request.CustomerRequest;
import com.example.workflow.service.CustomerService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component("delegateCheckDataRequest")
public class DelegateCheckDataRequest implements JavaDelegate {

    private final CustomerService customerService;

    public DelegateCheckDataRequest(CustomerService customerService) {
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
        delegateExecution.setVariable("checkDataRequest",customerService.checkCustomerDataRequest(customerRequest));
    }
}
