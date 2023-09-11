package com.marRover.rover.repositories;

import com.marRover.rover.models.MapEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMapRepository extends JpaRepository<MapEntity, Long> {
}
