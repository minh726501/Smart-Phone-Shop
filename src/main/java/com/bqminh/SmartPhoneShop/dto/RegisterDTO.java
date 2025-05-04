package com.bqminh.SmartPhoneShop.dto;

import com.bqminh.SmartPhoneShop.validator.ValidRegister;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@ValidRegister
public class RegisterDTO {
    @NotBlank(message = "First Name must not be blank")
    private String firstName;
    private String lastName;
    @NotBlank(message ="Email must not be blank")
    @Email
    private String email;
    private String password;
    private String confirmPassword;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
