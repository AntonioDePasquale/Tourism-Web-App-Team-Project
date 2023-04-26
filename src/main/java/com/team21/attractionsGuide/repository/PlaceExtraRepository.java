package com.team21.attractionsGuide.repository;

import com.team21.attractionsGuide.entity.PlaceExtra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaceExtraRepository extends JpaRepository<PlaceExtra, Long> {
    PlaceExtra findBylocationId(String locationID);
}
