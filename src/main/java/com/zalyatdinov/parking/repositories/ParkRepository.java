package com.zalyatdinov.parking.repositories;

import com.zalyatdinov.parking.domain.entity.ParkPlace;
import com.zalyatdinov.parking.domain.entity.ParkStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkRepository extends JpaRepository<ParkPlace, Long> {
    int countAllByParkStatus(ParkStatus parkStatus);

}
