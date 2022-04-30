package com.athanasius.droneservice.response;

import com.athanasius.droneservice.model.Dispatch;
import com.athanasius.droneservice.model.Drones;
import com.athanasius.droneservice.model.Medication;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DispatchResponse extends BaseResponse{
    Medication medication;
    Drones drones;
    Dispatch dispatch;
    List<Dispatch> dispatches;
    Integer count;


    public DispatchResponse(String code, String message, Dispatch dispatch) {
        super(code, message);
        this.dispatch = dispatch;
    }

    public DispatchResponse(Dispatch dispatch) {
        this.dispatch = dispatch;
    }

    public DispatchResponse(String code, String message) {
        super(code, message);
    }

    public DispatchResponse(String code, String message, List<Dispatch> dispatches) {
        super(code, message);
        this.dispatches = dispatches;
        this.count = dispatches.size();
    }

}
