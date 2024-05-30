package com.khaphp.foodservice.service.kafka;

import com.khaphp.common.dto.food.FoodDTOupdate;
import com.khaphp.foodservice.entity.Food;
import com.khaphp.foodservice.repo.FoodRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaMessageListener {
    public static final String ROLLBACK_FOR_THE_STOCK_OF_FOOD_MSG = "Rollback for the stock ({}) of Food ({})";
    private final Logger log = LoggerFactory.getLogger(KafkaMessageListener.class);
    private final FoodRepository foodRepository;

    @KafkaListener(topics = "UPDATE_STOCK_FOOD", groupId = "energy-handbook-group", containerFactory = "objectListener")
    public void updateStockFood(FoodDTOupdate object) {
        log.info("rollback consume the message json : {}", object.toString());
        try{
            //check exist
            Food food = foodRepository.findById(object.getId()).orElse(null);
            if(food != null){
                food.setStock(food.getStock() + object.getStock());
                foodRepository.save(food);
            }
            log.info(ROLLBACK_FOR_THE_STOCK_OF_FOOD_MSG + " successfully", object.getStock(), object.getId());
        }catch (Exception e){
            log.error(ROLLBACK_FOR_THE_STOCK_OF_FOOD_MSG + " failed with error {}", object.getStock(), object.getId(), e.getMessage());
        }
    }
}
