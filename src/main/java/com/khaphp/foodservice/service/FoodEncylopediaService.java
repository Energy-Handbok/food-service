package com.khaphp.foodservice.service;

import com.khaphp.common.dto.ResponseObject;
import com.khaphp.foodservice.dto.FoodEncylopedia.FoodEncylopediaDTOcreate;
import com.khaphp.foodservice.dto.FoodEncylopedia.FoodEncylopediaDTOupdate;
import org.springframework.web.multipart.MultipartFile;

public interface FoodEncylopediaService {

    ResponseObject<Object> getAll(int pageSize, int pageIndex);
    ResponseObject<Object> getDetail(String id);
    ResponseObject<Object> create(FoodEncylopediaDTOcreate object);
    ResponseObject<Object> update(FoodEncylopediaDTOupdate object);
    ResponseObject<Object> updateImage(String id, MultipartFile file);
    ResponseObject<Object> delete(String id);
}
