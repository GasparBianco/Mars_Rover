package com.marRover.rover.repositories;

import com.marRover.rover.models.ObstaclesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IObstacleRepository extends JpaRepository<ObstaclesEntity, Long> {
}
