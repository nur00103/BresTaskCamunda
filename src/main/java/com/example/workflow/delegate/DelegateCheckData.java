package com.example.workflow.delegate;

import com.example.workflow.dto.request.CustomerRequest;
import com.example.workflow.service.CustomerService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component("delegateCheckData")
public class DelegateCheckData implements JavaDelegate {

    private final CustomerService customerService;

    public DelegateCheckData(CustomerService customerService) {
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
        Boolean test=customerService.checkCustomerData(customerId,customerRequest);
        delegateExecution.setVariable("checkData",test);
    }
}
