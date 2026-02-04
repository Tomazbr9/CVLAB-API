package com.tomazbr9.buildprice.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "tb_projects")
public class Project  implements Serializable {

    private static final long seriaVersionUID = 1L;

    @Id
    @Column(nullable = false, updatable = true)
    @GeneratedValue
    private UUID id;

    @Column(name = "name_work", nullable = false)
    private String nameWork;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(nullable = false, precision = 5, scale = 2)
    private BigDecimal bdi;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Project(){

    }

    public Project(UUID id, String nameWork, Instant createdAt, BigDecimal bdi, User user) {
        this.id = id;
        this.nameWork = nameWork;
        this.createdAt = createdAt;
        this.bdi = bdi;
        this.user = user;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNameWork() {
        return nameWork;
    }

    public void setNameWork(String nameWork) {
        this.nameWork = nameWork;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public BigDecimal getBdi() {
        return bdi;
    }

    public void setBdi(BigDecimal bdi) {
        this.bdi = bdi;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @PrePersist
    public void prePersist(){
        if(bdi == null){
            bdi = BigDecimal.ZERO;
        }
    }
}
