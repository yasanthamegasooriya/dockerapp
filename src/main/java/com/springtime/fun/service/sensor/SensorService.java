package com.springtime.fun.service.sensor;

import com.springtime.fun.dao.sensor.SensorRecordJDBCRepository;
import com.springtime.fun.dao.sensor.SensorRecordRepository;
import com.springtime.fun.dto.sensor.*;
import com.springtime.fun.entity.sensor.SensorRecord;
import com.springtime.fun.exception.AppsException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * This class so the service layer functionality
 */
@Service
public class SensorService {

    @Autowired
    private SensorRecordRepository sensorRecordRepository;

    @Autowired
    private SensorRecordJDBCRepository sensorRecordJDBCRepository;

    /**
     * Adding sensor record to database
     *
     * @param sensorRecordDTO
     * @return
     * @throws AppsException
     */
    public SensorRecordDTO addSensorRecord(SensorRecordDTO sensorRecordDTO) throws AppsException {
        SensorRecord sensorRecord = new SensorRecord();

        sensorRecord.setSensorID(sensorRecordDTO.getSensorID());
        sensorRecord.setTemperature(sensorRecordDTO.getTemperature());
        sensorRecord.setCreatedDate(new Date());

        sensorRecord = this.sensorRecordRepository.saveAndFlush(sensorRecord);

        return new SensorRecordDTO(sensorRecord);
    }

    /**
     * Auery the average temperature for a specific sensor between two dates
     *
     * @param searchRQ
     * @return
     * @throws AppsException
     */
    public SensorSearchRS getSensorAverageTemperature(SensorSearchRQ searchRQ) throws AppsException {
        if (StringUtils.isEmpty(searchRQ.getSensorID())
                || StringUtils.isEmpty(searchRQ.getFromDateStr())
                || StringUtils.isEmpty(searchRQ.getToDateStr())) {
            throw new AppsException("Invalid Inputs");
        }

        // Get all sensor records between these two days
        List<SensorRecordDTO> sensorRecordDTOList =
                this.sensorRecordJDBCRepository.getSensorDataBetweenTwoDates(searchRQ);

        if (sensorRecordDTOList.isEmpty()) {
            throw new AppsException("No record find for this sensor on given dates");
        }

        int count = sensorRecordDTOList.size();
        double totalTemperature = 0.0;

        for (SensorRecordDTO sensorRecordDTO : sensorRecordDTOList) {
            totalTemperature = totalTemperature + sensorRecordDTO.getTemperature();
        }

        // Calculate average temperature
        double averageTemperature = totalTemperature / count;

        SensorSearchRS searchRS = new SensorSearchRS();
        searchRS.setSensorID(searchRQ.getSensorID());
        searchRS.setAverageTemperature(averageTemperature);

        return searchRS;
    }

    /**
     * Query the average temperature across all sensors between two dates
     *
     * @param searchRQ
     * @return
     * @throws AppsException
     */
    public SensorsSearchRS getAllSensorsAverageTemperature(SensorsSearchRQ searchRQ) throws AppsException {
        if (StringUtils.isEmpty(searchRQ.getFromDateStr())
                || StringUtils.isEmpty(searchRQ.getToDateStr())) {
            throw new AppsException("Invalid Inputs");
        }

        final Map<String, Double> sensorWiseAverageTempMap =
                this.sensorRecordJDBCRepository.getAllSensorsAverageTemperature(searchRQ);

        SensorsSearchRS searchRS = new SensorsSearchRS();
        searchRS.setSensorWiseAverageTempMap(sensorWiseAverageTempMap);

        return searchRS;
    }
}
