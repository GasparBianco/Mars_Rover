package com.marRover.rover.repositories;

import com.marRover.rover.models.RoverEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoverRepository extends JpaRepository<RoverEntity, Long> {
}
