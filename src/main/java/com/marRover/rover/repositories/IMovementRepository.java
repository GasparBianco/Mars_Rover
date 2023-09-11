package com.marRover.rover.repositories;

import com.marRover.rover.models.MovementsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMovementRepository extends JpaRepository<MovementsEntity, Long> {
}
