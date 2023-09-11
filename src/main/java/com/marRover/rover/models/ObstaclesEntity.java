package com.marRover.rover.models;

import jakarta.persistence.*;

@Entity(name = "obstacle")
public class ObstaclesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private Long id;

    @Column(name = "x",nullable = false)
    private int x;
    @Column(name = "y",nullable = false)
    private int y;
    @ManyToOne
    @JoinColumn(name = "map_id")
    private MapEntity mapEntity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
