package com.khaphp.foodservice.dto.Food;

import com.khaphp.foodservice.util.valid.Status.ValidStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateStatusFood {
    private String id;
    @ValidStatus
    private String status;
}
