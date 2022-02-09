package com.zalyatdinov.parking.repositories;

import com.zalyatdinov.parking.domain.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarsRepository extends JpaRepository<Car, Long> {

}
