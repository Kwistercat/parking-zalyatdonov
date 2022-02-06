package com.zalyatdinov.parking.repositories;

import com.zalyatdinov.parking.model.Car;
import com.zalyatdinov.parking.model.ParkPlace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkRepository extends JpaRepository<ParkPlace, Long> {

}
