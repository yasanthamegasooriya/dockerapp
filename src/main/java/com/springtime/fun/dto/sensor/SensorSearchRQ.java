package com.springtime.fun.dto.sensor;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SensorSearchRQ {

    private String sensorID;

    private String fromDateStr;

    private String toDateStr;
}
