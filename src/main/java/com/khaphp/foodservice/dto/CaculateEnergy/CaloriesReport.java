package com.khaphp.foodservice.dto.CaculateEnergy;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CaloriesReport {
    private double tdee;
    private double bmr;
    private String r;
}
