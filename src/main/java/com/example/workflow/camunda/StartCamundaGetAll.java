package com.example.workflow.camunda;
import com.example.workflow.dto.response.CustomerResponse;
import com.example.workflow.dto.response.ResponseModel;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.variable.VariableMap;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("startCamundaBean")
public class StartCamundaGetAll {
    private final RuntimeService runtimeService;

    public StartCamundaGetAll(RuntimeService runtimeService) {
        this.runtimeService = runtimeService;
    }

    public ResponseModel<List<CustomerResponse>> startBpmnGetAll(String bpmnName){
       VariableMap variableMap=runtimeService.createProcessInstanceByKey(bpmnName)
               .executeWithVariablesInReturn()
               .getVariables();
       return  variableMap.getValue("getCustomers", ResponseModel.class);

    }



}
