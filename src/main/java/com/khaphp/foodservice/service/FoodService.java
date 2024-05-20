package com.khaphp.foodservice.service;

import com.khaphp.common.dto.ResponseObject;
import com.khaphp.foodservice.dto.Food.FoodDTOcreate;
import com.khaphp.foodservice.dto.Food.FoodDTOupdate;
import com.khaphp.foodservice.dto.Food.UpdateStatusFood;
import org.springframework.web.multipart.MultipartFile;

public interface FoodService {

    ResponseObject<Object> getAll(int pageSize, int pageIndex);
    ResponseObject<Object> getDetail(String id);
    ResponseObject<Object> create(FoodDTOcreate object);
    ResponseObject<Object> update(FoodDTOupdate object);
    ResponseObject<Object> updateImage(String id, MultipartFile file);
    ResponseObject<Object> updateStatus(UpdateStatusFood object);
    ResponseObject<Object> delete(String id);
}
