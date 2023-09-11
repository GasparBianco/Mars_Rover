package com.marRover.rover.models;

import jakarta.persistence.*;

@Entity(name = "movements")
public class MovementsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private Long id;

    @Column(name = "direction",nullable = false)
    private int direction;
    @ManyToOne
    @JoinColumn(name = "rover_id")
    private RoverEntity roverEntity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }
}
