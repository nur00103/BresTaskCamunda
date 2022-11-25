package com.example.workflow.service;

import com.example.workflow.dto.request.CustomerRequest;
import com.example.workflow.dto.response.CustomerResponse;
import com.example.workflow.dto.response.ResponseModel;

import java.util.List;

public interface CustomerService {
    ResponseModel<List<CustomerResponse>> getAllCustomer();

    ResponseModel<CustomerResponse> getCustomerById(Long customerId);

    ResponseModel<CustomerResponse> addCustomer(CustomerRequest customerRequest);

    ResponseModel<CustomerResponse> updateCustomer(Long customerId, CustomerRequest customerRequest);

    ResponseModel<CustomerResponse> deleteCustomer(Long customerId);

    public boolean checkCustomerData(Long customerId, CustomerRequest customerRequest);

    public boolean checkCustomerDataRequest(CustomerRequest customerRequest);
}
