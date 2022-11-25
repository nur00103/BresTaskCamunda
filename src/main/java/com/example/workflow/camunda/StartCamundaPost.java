package com.example.workflow.camunda;

import com.example.workflow.dto.request.CustomerRequest;
import com.example.workflow.dto.response.CustomerResponse;
import com.example.workflow.dto.response.ResponseModel;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.variable.VariableMap;
import org.springframework.stereotype.Service;

@Service
public class StartCamundaPost {

    private final RuntimeService runtimeService;

    public StartCamundaPost(RuntimeService runtimeService) {
        this.runtimeService = runtimeService;
    }

    public ResponseModel<CustomerResponse> startBpmnPost(String bpmnName, CustomerRequest customerRequest){
        VariableMap variableMap= runtimeService.createProcessInstanceByKey(bpmnName)
                .setVariable("name",customerRequest.getName())
                .setVariable("surname",customerRequest.getSurname())
                .setVariable("fullname",customerRequest.getFullname())
                .setVariable("fin",customerRequest.getFin())
                .setVariable("type",customerRequest.getType())
                .executeWithVariablesInReturn()
                .getVariables();
        return variableMap.getValue("postCustomer", ResponseModel.class);
    }
}
