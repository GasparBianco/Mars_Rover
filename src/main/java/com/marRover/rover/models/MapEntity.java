package com.marRover.rover.models;

import jakarta.persistence.*;

import java.util.List;

@Entity(name = "map")
public class MapEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private Long id;
    @Column(name = "x",nullable = false)
    private int x;
    @Column(name = "y",nullable = false)
    private int y;
    @OneToMany(mappedBy = "mapEntity", cascade = CascadeType.ALL)
    private List<ObstaclesEntity> obstacles;

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
    public List<ObstaclesEntity> getObstacles() {
        return obstacles;
    }

    public void setObstacles(List<ObstaclesEntity> obstacles) {
        this.obstacles = obstacles;
    }
}

