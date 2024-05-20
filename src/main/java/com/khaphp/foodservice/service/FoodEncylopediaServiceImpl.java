package com.khaphp.foodservice.service;

import com.khaphp.common.dto.ResponseObject;
import com.khaphp.common.entity.UserSystem;
import com.khaphp.foodservice.call.UserServiceCall;
import com.khaphp.foodservice.dto.FoodEncylopedia.FoodEncylopediaDTOcreate;
import com.khaphp.foodservice.dto.FoodEncylopedia.FoodEncylopediaDTOupdate;
import com.khaphp.foodservice.entity.FoodEncylopedia;
import com.khaphp.foodservice.exception.ObjectNotFound;
import com.khaphp.foodservice.repo.FoodEncylopediaRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FoodEncylopediaServiceImpl implements FoodEncylopediaService {
    public static final String OBJECT_NOT_FOUND_MSG = "object not found";
    public static final String EXCEPTION_MSG = "Exception: ";
    public static final String SUCCESS_MSG = "Success";
    private final FoodEncylopediaRepository foodEncylopediaRepository;
    private final UserServiceCall userServiceCall;
    private final ModelMapper modelMapper;
    private final FileStore fileStore;

    @Value("${aws.s3.link_bucket}")
    private String linkBucket;

    @Value("${logo.energy_handbook.name}")
    private String logoName;
    @Override
    public ResponseObject<Object> getAll(int pageSize, int pageIndex) {
        Page<FoodEncylopedia> objListPage = null;
        List<FoodEncylopedia> objList = null;
        int totalPage = 0;
        //paging
        if(pageSize > 0 && pageIndex > 0){
            objListPage = foodEncylopediaRepository.findAll(PageRequest.of(pageIndex - 1, pageSize));  //vì current page ở code nó start = 0, hay bên ngoài la 2pga đầu tiên hay 1
            if(objListPage != null){
                totalPage = objListPage.getTotalPages();
                objList = objListPage.getContent();
            }
        }else{ //get all
            objList = foodEncylopediaRepository.findAll();
            pageIndex = 1;
        }
        objList.forEach(object -> object.setImg(linkBucket + object.getImg()));
        return ResponseObject.builder()
                .code(200).message(SUCCESS_MSG)
                .pageSize(objList.size()).pageIndex(pageIndex).totalPage(totalPage)
                .data(objList)
                .build();
    }

    @Override
    public ResponseObject<Object> getDetail(String id) {
        try{
            FoodEncylopedia object = foodEncylopediaRepository.findById(id).orElse(null);
            if(object == null) {
                throw new ObjectNotFound(OBJECT_NOT_FOUND_MSG);
            }
            object.setImg(linkBucket + object.getImg());
            return ResponseObject.builder()
                    .code(200)
                    .message("Found")
                    .data(object)
                    .build();
        }catch (Exception e){
            return ResponseObject.builder()
                    .code(400)
                    .message(EXCEPTION_MSG + e.getMessage())
                    .build();
        }
    }

    @Override
    public ResponseObject<Object> create(FoodEncylopediaDTOcreate object) {
        try{
            UserSystem userSystem = userServiceCall.getObject(object.getEmployeeId());
            if(userSystem == null){
                throw new ObjectNotFound("user not found");
            }
            FoodEncylopedia object1 = modelMapper.map(object, FoodEncylopedia.class);
            object1.setUpdateDate(new Date(System.currentTimeMillis()));
            object1.setEmployeeId(userSystem.getId());
            object1.setImg(logoName);
            foodEncylopediaRepository.save(object1);
            return ResponseObject.builder()
                    .code(200)
                    .message(SUCCESS_MSG)
                    .data(object1)
                    .build();
        }catch (Exception e){
            return ResponseObject.builder()
                    .code(400)
                    .message(EXCEPTION_MSG + e.getMessage())
                    .build();
        }
    }

    @Override
    public ResponseObject<Object> update(FoodEncylopediaDTOupdate object) {
        try{
            FoodEncylopedia object1 = foodEncylopediaRepository.findById(object.getId()).orElse(null);
            if(object1 == null) {
                throw new ObjectNotFound(OBJECT_NOT_FOUND_MSG);
            }
            object1.setName(object.getName());
            object1.setCalo(object.getCalo());
            object1.setUnit(object.getUnit());
            object1.setUpdateDate(new Date(System.currentTimeMillis()));
            foodEncylopediaRepository.save(object1);
            return ResponseObject.builder()
                    .code(200)
                    .message(SUCCESS_MSG)
                    .build();
        }catch (Exception e){
            return ResponseObject.builder()
                    .code(400)
                    .message(EXCEPTION_MSG + e.getMessage())
                    .build();
        }
    }

    @Override
    public ResponseObject<Object> updateImage(String id, MultipartFile file) {
        try{
            FoodEncylopedia object = foodEncylopediaRepository.findById(id).orElse(null);
            if(object == null) {
                throw new ObjectNotFound(OBJECT_NOT_FOUND_MSG);
            }
            if(!object.getImg().equals(logoName)){
                fileStore.deleteImage(object.getImg());
            }

            //upload new img
            object.setImg(fileStore.uploadImg(file));
            foodEncylopediaRepository.save(object);
            return ResponseObject.builder()
                    .code(200)
                    .message(SUCCESS_MSG)
                    .build();
        }catch (Exception e){
            return ResponseObject.builder()
                    .code(400)
                    .message(EXCEPTION_MSG + e.getMessage())
                    .build();
        }
    }

    @Override
    public ResponseObject<Object> delete(String id) {
        try{
            FoodEncylopedia object = foodEncylopediaRepository.findById(id).orElse(null);
            if(object == null) {
                throw new ObjectNotFound(OBJECT_NOT_FOUND_MSG);
            }
            if(!object.getImg().equals(logoName)){
                fileStore.deleteImage(object.getImg());
            }
            foodEncylopediaRepository.delete(object);
            return ResponseObject.builder()
                    .code(200)
                    .message(SUCCESS_MSG)
                    .build();
        }catch (Exception e){
            return ResponseObject.builder()
                    .code(400)
                    .message(EXCEPTION_MSG + e.getMessage())
                    .build();
        }
    }
}
