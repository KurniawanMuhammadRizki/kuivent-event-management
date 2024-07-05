package com.mini_project_event_management.event_management.users.dto;

import com.mini_project_event_management.event_management.users.entity.Users;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterUserRequestDto {

    @NotBlank(message = "First name cannot be empty")
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotBlank(message = "Last name cannot be empty")
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @NotBlank(message = "Password cannot be empty")
    @Column(name = "password", nullable = false)
    private String password;

    @NotBlank(message = "Address cannot be empty")
    @Column(name = "address", nullable = false)
    private String address;

    @NotBlank(message = "City cannot be empty")
    @Column(name = "city", nullable = false)
    private String city;

    @NotBlank(message = "Email cannot be empty")
    @Column(name = "email", nullable = false)
    private String email;

    @NotBlank(message = "Phone number cannot be empty")
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    public Users toEntity(){
        Users user = new Users();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPassword(password);
        user.setAddress(address);
        user.setCity(city);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        return user;
    }

}
