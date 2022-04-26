package com.athanasius.droneservice.response;

import com.athanasius.droneservice.model.Drones;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DronesResponse extends BaseResponse{
    Drones drone;
    List<Drones> drones;
    Integer count;

    public Integer getCount() {
        return count;
    }

    public DronesResponse(String code, String message, Drones drone) {
        super(code, message);
        this.drone = drone;
    }

    public DronesResponse(Drones drone) {
        this.drone = drone;
    }

    public DronesResponse(String code, String message) {
        super(code, message);
    }

    public DronesResponse(String code, String message, List<Drones> drones) {
        super(code, message);
        this.drones = drones;
        this.count = drones.size();
    }

}
