package com.springtime.fun.dto.sensor;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SensorsSearchRS {

    private Map<String, Double> sensorWiseAverageTempMap;

    public Map<String, Double> getSensorWiseAverageTempMap() {
        if (sensorWiseAverageTempMap == null) {
            sensorWiseAverageTempMap = new HashMap<>();
        }
        return sensorWiseAverageTempMap;
    }
}
