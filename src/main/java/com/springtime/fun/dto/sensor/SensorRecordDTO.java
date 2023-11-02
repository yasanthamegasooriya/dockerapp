package com.springtime.fun.dto.sensor;

import com.springtime.fun.entity.sensor.SensorRecord;
import com.springtime.fun.util.CalendarUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data transfer object of sensor record entity
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SensorRecordDTO {

    private Integer sensorRecordID;

    private String sensorID;

    private Double temperature;

    private String createdDateStr;

    public SensorRecordDTO(SensorRecord sensorRecord) {
        this.sensorRecordID = sensorRecord.getSensorRecordID();
        this.sensorID = sensorRecord.getSensorID();
        this.temperature = sensorRecord.getTemperature();
        this.createdDateStr = CalendarUtil.getDefaultFormattedDateTimeMaskNull(sensorRecord.getCreatedDate());
    }
}
