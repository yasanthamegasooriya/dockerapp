package com.springtime.fun;

import com.springtime.fun.dto.sensor.*;
import com.springtime.fun.service.sensor.SensorService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

// @SpringBootTest
// @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
// class SpringtimeFunApplicationTests {

//     @Autowired
//     private SensorService sensorService;

//     @Test
//     void contextLoads() {
//     }

    // @Test
    // @Order(1)
    // public void addSensorRecord() throws Exception {
    //     SensorRecordDTO sensorRecordDTO = new SensorRecordDTO();

    //     sensorRecordDTO.setSensorID("EH-4");
    //     sensorRecordDTO.setTemperature(23.56);

    //     SensorRecordDTO result = this.sensorService.addSensorRecord(sensorRecordDTO);
    //     assertNotNull(result.getSensorRecordID());
    // }

    // @Test
    // @Order(2)
    // public void getSensorAverageTemperature() throws Exception {
    //     SensorSearchRQ searchRQ = new SensorSearchRQ();

    //     searchRQ.setSensorID("XYZ");
    //     searchRQ.setFromDateStr("01/10/2023 20:20:20");
    //     searchRQ.setToDateStr("30/11/2023 20:20:20");

    //     SensorSearchRS searchRS = this.sensorService.getSensorAverageTemperature(searchRQ);
    //     assertNotNull(searchRS.getAverageTemperature());
    // }

    // @Test
    // @Order(3)
    // public void getAllSensorsAverageTemperature() throws Exception {
    //     SensorsSearchRQ searchRQ = new SensorsSearchRQ();

    //     searchRQ.setFromDateStr("01/10/2023 20:20:20");
    //     searchRQ.setToDateStr("30/11/2023 20:20:20");

    //     SensorsSearchRS searchRS = this.sensorService.getAllSensorsAverageTemperature(searchRQ);
    //     assertNotNull(searchRS.getSensorWiseAverageTempMap());
    // }
}
