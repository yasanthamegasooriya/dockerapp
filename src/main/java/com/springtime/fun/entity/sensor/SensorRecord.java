package com.springtime.fun.entity.sensor;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * sensor_record table entity representation
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sensor_record")
public class SensorRecord {

    /**
     * This is the unique key of the a sensor record. This hold primary key of the database
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "SENSOR_RECORD_ID")
    private Integer sensorRecordID;

    /**
     * Sensor ID
     */
    @Column(name = "SENSOR_ID", nullable = false)
    private String sensorID;

    /**
     * Temperature value of the sensor read
     */
    @Column(name = "TEMPERATURE", nullable = false)
    private Double temperature;

    /**
     * Record inserted time
     */
    @Column(name = "CREATED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
}
