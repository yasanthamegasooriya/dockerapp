package com.springtime.fun.dao.sensor;

import com.springtime.fun.entity.sensor.SensorRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Data access object with Sensor entity
 */
@Repository
public interface SensorRecordRepository extends JpaRepository<SensorRecord, Integer> {

}
