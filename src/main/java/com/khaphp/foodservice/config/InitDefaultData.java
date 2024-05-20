//package com.khaphp.foodservice.config;
//
//import com.khaphp.common.constant.EmailDefault;
//import com.khaphp.common.entity.UserSystem;
//import com.khaphp.foodservice.call.UserServiceCall;
//import com.khaphp.foodservice.dto.FoodEncylopedia.FoodEncylopediaDTOcreate;
//import com.khaphp.foodservice.entity.FoodEncylopedia;
//import com.khaphp.foodservice.repo.FoodEncylopediaRepository;
//import com.khaphp.foodservice.service.FoodEncylopediaService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.stereotype.Component;
//
//@Component
//@RequiredArgsConstructor
//public class InitDefaultData {
//    private final UserServiceCall userServiceCall;
//    private final FoodEncylopediaRepository foodEncylopediaRepository;
//    private final FoodEncylopediaService foodEncylopediaService;
//    @Bean
//    public CommandLineRunner commandLineRunner() {
//        return args -> {
//            //default food encylopedia
//            UserSystem employee = userServiceCall.getDetailByEmail(EmailDefault.EMPLOYEE_EMAIL_DEFAULT);
//            if(employee == null){ throw new RuntimeException("Employee default not found");}
//            FoodEncylopedia foodEncylopedia = foodEncylopediaRepository.findByName("...");
//            if (foodEncylopedia == null) {
//                foodEncylopediaService.create(FoodEncylopediaDTOcreate.builder()
//                        .name("...")
//                        .calo(0)
//                        .unit("...")
//                        .employeeId(employee.getId())
//                        .build());
//            }
//        };
//    }
//}
