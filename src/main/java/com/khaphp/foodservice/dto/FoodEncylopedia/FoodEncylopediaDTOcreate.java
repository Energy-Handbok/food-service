package com.khaphp.foodservice.dto.FoodEncylopedia;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class FoodEncylopediaDTOcreate {
    private String employeeId;
    @Size(min=1, max=255, message = "name length from 1 to 255")
    private String name;
    @Size(min=1, max=50, message = "unit length from 1 to 50")
    private String unit;
    @Min(value = 1, message = "calo must be greater than 0")
    private float calo;
}
