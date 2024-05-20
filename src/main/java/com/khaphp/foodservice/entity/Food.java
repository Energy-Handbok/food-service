package com.khaphp.foodservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Food {
    @Id
    @UuidGenerator
    @Column(columnDefinition = "VARCHAR(36)")
    private String id;
    private String name;
    private String unit;
    private float stock;
    private float price;
    private short sale; //from 1 to 100
    private String img;
    private String location;
    private Date updateDate;
    private String status;
    @Column(columnDefinition = "VARCHAR(36)")
    private String employeeId;
    @ManyToOne
    @JsonIgnore
    private FoodEncylopedia foodEncylopedia;
}
