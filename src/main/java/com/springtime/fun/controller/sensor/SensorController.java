package com.springtime.fun.controller.sensor;

import com.springtime.fun.dto.common.ResponseDTO;
import com.springtime.fun.dto.sensor.*;
import com.springtime.fun.exception.AppsException;
import com.springtime.fun.service.sensor.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/sensor")
public class SensorController {

    @Autowired
    private SensorService sensorService;

    /**
     * Allows the user to upload sensor data (sensor id and temperature value)
     *
     * @param sensorRecordDTO
     * @return
     */
    @PostMapping(value = "/addSensorRecord", headers = "Accept=application/json")
    public ResponseEntity<ResponseDTO<SensorRecordDTO>> addSensorRecord(@RequestBody SensorRecordDTO sensorRecordDTO) {
        ResponseDTO<SensorRecordDTO> response = new ResponseDTO<>();
        HttpStatus httpStatus;

        try {
            SensorRecordDTO sensorRecord = this.sensorService.addSensorRecord(sensorRecordDTO);

            response.setResult(sensorRecord);
            response.setStatus("SUCCESS");
            httpStatus = HttpStatus.CREATED;

        } catch (AppsException e) {
            response.setStatus("FAILED");
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            response.setAppsErrorMessages(e.getAppsErrorMessages());
        }

        return new ResponseEntity<>(response, httpStatus);
    }

    /**
     * Allows the user to query the average temperature for a specific sensor between two dates
     *
     * @param searchRQ
     * @return
     */
    @PostMapping(value = "/getSensorAverageTemperature", headers = "Accept=application/json")
    public ResponseEntity<ResponseDTO<SensorSearchRS>> getSensorAverageTemperature(@RequestBody SensorSearchRQ searchRQ) {
        ResponseDTO<SensorSearchRS> response = new ResponseDTO<>();
        HttpStatus httpStatus;

        try {
            SensorSearchRS searchRS = this.sensorService.getSensorAverageTemperature(searchRQ);

            response.setResult(searchRS);
            response.setStatus("SUCCESS");
            httpStatus = HttpStatus.CREATED;

        } catch (AppsException e) {
            response.setStatus("FAILED");
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            response.setAppsErrorMessages(e.getAppsErrorMessages());
        }

        return new ResponseEntity<>(response, httpStatus);
    }

    /**
     * Allows the user to query the average temperature across all sensors between two dates
     *
     * @param searchRQ
     * @return
     */
    @PostMapping(value = "/getAllSensorsAverageTemperature", headers = "Accept=application/json")
    public ResponseEntity<ResponseDTO<SensorsSearchRS>> getAllSensorsAverageTemperature(@RequestBody SensorsSearchRQ searchRQ) {
        ResponseDTO<SensorsSearchRS> response = new ResponseDTO<>();
        HttpStatus httpStatus;

        try {
            SensorsSearchRS searchRS = this.sensorService.getAllSensorsAverageTemperature(searchRQ);

            response.setResult(searchRS);
            response.setStatus("SUCCESS");
            httpStatus = HttpStatus.CREATED;

        } catch (AppsException e) {
            response.setStatus("FAILED");
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            response.setAppsErrorMessages(e.getAppsErrorMessages());
        }

        return new ResponseEntity<>(response, httpStatus);
    }
}
