package com.athanasius.droneservice.validators;

import com.athanasius.droneservice.dto.DronesDto;
import com.athanasius.droneservice.dto.MedicationDto;
import com.athanasius.droneservice.exception.BadRequestException;
import org.springframework.stereotype.Service;

/**
 * Created by Athanasius.Lawrence on 26/04/2022.
 */
@Service
public class Validator {

    public void validateDrones(DronesDto dronesDto) throws BadRequestException {

        if (isNullOrEmptyString(dronesDto.getSerialNo())) {
            throw new BadRequestException("Serial Number is required");
        }

        if (dronesDto.getSerialNo().length() > 100) {
            throw new BadRequestException("Serial Number should not exceed 100 characters");
        }

        if (isNullOrEmptyString(dronesDto.getModel())) {
            throw new BadRequestException("Drone model is required");
        }

        if (isNullOrEmptyString(dronesDto.getState())) {
            throw new BadRequestException("Drone state is required");
        }

        if (dronesDto.getBatteryCapacity() == null || dronesDto.getBatteryCapacity() < 1) {
            throw new BadRequestException("Battery capacity is required");
        }

        if (dronesDto.getWeightLimit() == null) {
            throw new BadRequestException("Weight limit is required");
        }

        if (dronesDto.getWeightLimit() > 500) {
            throw new BadRequestException("Weight limit should not exceed 500");
        }
    }

    public void validateMedication(MedicationDto medicationDto) throws BadRequestException {

        if (isNullOrEmptyString(medicationDto.getName())) {
            throw new BadRequestException("Name is required");
        }

        // no special xters
        if (medicationDto.getName().length() > 100) {
            throw new BadRequestException("Serial Number should not exceed 100 characters");
        }

        //code should be only Uppercase, underscore and numbers

    }

    public static boolean isNullOrEmptyString(String stringValue) {
        return (null == stringValue || stringValue.trim().equals(""));
    }

}

