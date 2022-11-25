package com.example.workflow.service.impl;

import com.example.workflow.dto.request.CustomerRequest;
import com.example.workflow.dto.response.CustomerResponse;
import com.example.workflow.dto.response.ResponseModel;
import com.example.workflow.entity.Customer;
import com.example.workflow.enums.ExceptionEnum;
import com.example.workflow.exception.MyException;
import com.example.workflow.repository.CustomerRepository;
import com.example.workflow.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@RequiredArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {

        private final CustomerRepository customerRepository;
        @Override
        public ResponseModel<List<CustomerResponse>> getAllCustomer() {
            List<Customer>customerList=customerRepository.findAll();
            if (customerList.isEmpty() || customerList==null){
                throw new MyException(ExceptionEnum.EMPTY);
            }
            List<CustomerResponse> customerResponseList=customerList.stream().map(customer -> {
                CustomerResponse customerResponse=convertToResponse(customer);
                return customerResponse;
            }).collect(Collectors.toList());
            return ResponseModel.<List<CustomerResponse>>builder().result(customerResponseList)
                    .status(ExceptionEnum.SUCCESS.getMessage()).code(ExceptionEnum.SUCCESS.getCode()).build();
        }

        @Override
        public ResponseModel<CustomerResponse> getCustomerById(Long customerId) {
            CustomerResponse customerResponse=null;
            if (customerRepository.findById(customerId).isPresent()){
                Customer customer=customerRepository.findById(customerId).get();
                customerResponse=convertToResponse(customer);
            }else{
                throw new MyException(ExceptionEnum.USER_NOT_FOUND);
            }
            return ResponseModel.<CustomerResponse>builder().result(customerResponse).error(false)
                    .status(ExceptionEnum.SUCCESS.getMessage()).code(ExceptionEnum.SUCCESS.getCode()).build();
        }

        @Override
        public ResponseModel<CustomerResponse> addCustomer(CustomerRequest customerRequest) {
            Customer customer=requestToEntity(customerRequest);
            Customer savedCustomer=customerRepository.save(customer);
            CustomerResponse customerResponse=convertToResponse(customer);
            return ResponseModel.<CustomerResponse>builder().result(customerResponse).error(false)
                    .status(ExceptionEnum.SUCCESS.getMessage()).code(ExceptionEnum.SUCCESS.getCode()).build();
        }

        @Override
        public ResponseModel<CustomerResponse> updateCustomer(Long customerId, CustomerRequest customerRequest) {
            Customer customer=customerRepository.findById(customerId).get();
            customer=requestToEntity(customerRequest);
            customer.setId(customerId);
            Customer updatedCustomer=customerRepository.save(customer);
            CustomerResponse customerResponse=convertToResponse(updatedCustomer);
            return ResponseModel.<CustomerResponse>builder().result(customerResponse).error(false)
                    .status(ExceptionEnum.SUCCESS.getMessage()).code(ExceptionEnum.SUCCESS.getCode()).build();

        }
        @Override
        public boolean checkCustomerData(Long customerId, CustomerRequest customerRequest){
            if (customerRequest==null){
                return false;
            }else if (!customerRepository.findById(customerId).isPresent()){
                return false;
            }else {
                return true;
            }
        }

        @Override
        public boolean checkCustomerDataRequest(CustomerRequest customerRequest) {
            if (customerRequest==null){
                return false;
            }else {
                return true;
            }
        }


        @Override
        public ResponseModel<CustomerResponse> deleteCustomer(Long customerId) {
            if (!customerRepository.findById(customerId).isPresent()){
                throw new MyException(ExceptionEnum.USER_NOT_FOUND);
            }else {
                customerRepository.deleteById(customerId);
            }
            return ResponseModel.<CustomerResponse>builder().result(null).error(false)
                    .status(ExceptionEnum.SUCCESS.getMessage()).code(ExceptionEnum.SUCCESS.getCode()).build();
        }

        public CustomerResponse convertToResponse(Customer customer){
            return CustomerResponse.builder().id(customer.getId()).name(customer.getName()).surname(customer.getSurname())
                    .fullName(customer.getFullName()).type(customer.getType().setActive(customer.getActive()));).build();
        }

        public Customer requestToEntity(CustomerRequest customerRequest){
            Customer customer=new Customer();
            customer.setName(customerRequest.getName());
            customer.setSurname(customerRequest.getSurname());
            customer.setUsername(customerRequest.getUsername());
            customer.setPassword(customerRequest.getPassword());
            customer.setEmail(customerRequest.getEmail());
            customer.setAddress(customerRequest.getAddress());
            customer.setPhone(customerRequest.getPhone());
            return customer;
        }
    }

}
