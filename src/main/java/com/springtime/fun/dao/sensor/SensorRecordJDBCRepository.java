package com.springtime.fun.dao.sensor;

import com.springtime.fun.dto.sensor.SensorRecordDTO;
import com.springtime.fun.dto.sensor.SensorSearchRQ;
import com.springtime.fun.dto.sensor.SensorsSearchRQ;
import com.springtime.fun.exception.AppsException;
import com.springtime.fun.util.CalendarUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Query the data by using row mysql queries
 */
@Repository
public class SensorRecordJDBCRepository {

    @Autowired
    protected NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<SensorRecordDTO> getSensorDataBetweenTwoDates(SensorSearchRQ searchRQ) throws AppsException {
        final List<SensorRecordDTO> results = new ArrayList<>();
        Map<String, Object> params = new HashMap<>();

        StringBuilder SQL = new StringBuilder();

        SQL.append("SELECT sr.sensor_record_id, \n");
        SQL.append("       sr.sensor_id, \n");
        SQL.append("       sr.temperature \n");
        SQL.append("FROM sensor_record sr \n");
        SQL.append(" WHERE sr.sensor_record_id IS NOT NULL \n");

        if (StringUtils.isNotEmpty(searchRQ.getSensorID())) {
            SQL.append("      AND sr.sensor_id = :sensorID \n");
            params.put("sensorID", searchRQ.getSensorID());
        }
        if (StringUtils.isNotEmpty(searchRQ.getFromDateStr())) {
            SQL.append("      AND sr.CREATED_DATE >= :fromDateTime \n");
            params.put("fromDateTime", CalendarUtil.getDefaultParsedDateTimeOnly(searchRQ.getFromDateStr()));
        }
        if (StringUtils.isNotEmpty(searchRQ.getToDateStr())) {
            SQL.append("      AND sr.CREATED_DATE <= :toDateTime \n");
            params.put("toDateTime", CalendarUtil.getDefaultParsedDateTimeOnly(searchRQ.getToDateStr()));
        }

        return namedParameterJdbcTemplate.query(SQL.toString(), params, rs -> {
            while (rs.next()) {
                SensorRecordDTO sensorRecordDTO = new SensorRecordDTO();

                sensorRecordDTO.setSensorRecordID(rs.getInt("sensor_record_id"));
                sensorRecordDTO.setSensorID(rs.getString("sensor_id"));
                sensorRecordDTO.setTemperature(rs.getDouble("temperature"));

                results.add(sensorRecordDTO);
            }
            return results;
        });
    }

    public Map<String, Double> getAllSensorsAverageTemperature(SensorsSearchRQ searchRQ) throws AppsException {
        final Map<String, Double> results = new HashMap<>();
        Map<String, Object> params = new HashMap<>();

        StringBuilder SQL = new StringBuilder();

        SQL.append("SELECT sr.sensor_id, \n");
        SQL.append("       avg(sr.temperature) AS avg_temp \n");
        SQL.append("FROM sensor_record sr \n");
        SQL.append(" WHERE sr.sensor_record_id IS NOT NULL \n");

        if (StringUtils.isNotEmpty(searchRQ.getFromDateStr())) {
            SQL.append("      AND sr.CREATED_DATE >= :fromDateTime \n");
            params.put("fromDateTime", CalendarUtil.getDefaultParsedDateTimeOnly(searchRQ.getFromDateStr()));
        }
        if (StringUtils.isNotEmpty(searchRQ.getToDateStr())) {
            SQL.append("      AND sr.CREATED_DATE <= :toDateTime \n");
            params.put("toDateTime", CalendarUtil.getDefaultParsedDateTimeOnly(searchRQ.getToDateStr()));
        }

        SQL.append(" GROUP BY sr.sensor_id ");

        namedParameterJdbcTemplate.query(SQL.toString(), params, rs -> {
            while (rs.next()) {
                String sensorID = rs.getString("sensor_id");
                double avgTemp = rs.getDouble("avg_temp");
                results.putIfAbsent(sensorID, avgTemp);
            }
        });

        return results;
    }
}
