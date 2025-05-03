package com.bqminh.SmartPhoneShop.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;


    @Documented
    @Constraint(validatedBy = RegisterValidator.class)
    @Target({ ElementType.TYPE })
    @Retention(RetentionPolicy.RUNTIME)
    public @interface ValidRegister {
        String message() default "Đăng ký không hợp lệ";  // Thông báo lỗi mặc định
        Class<?>[] groups() default {};
        Class<? extends Payload>[] payload() default {};
    }

