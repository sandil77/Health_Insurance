package com.cms.user.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "First Name cannot be empty")
    @Size(min = 2, max = 50, message = "First Name should be between 2 and 50 characters")
    private String firstName;

    @NotEmpty(message = "Last Name cannot be empty")
    @Size(min = 2, max = 50, message = "Last Name should be between 2 and 50 characters")
    private String lastName;

    @NotNull(message = "Age cannot be null")
    @Min(value = 18, message = "Age must be at least 18")
    private int age;

    @NotEmpty(message = "Gender cannot be empty")
    @Pattern(regexp = "^(Male|Female|Other)$", message = "Gender must be Male, Female, or Other")
    private String gender;

    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Email should be valid")
    @Column(unique = true)
    private String emailId;

    @NotEmpty(message = "Contact number cannot be empty")
    @Pattern(regexp = "^[0-9]{10}$", message = "Contact number must be 10 digits")
    private String contact;

    @NotEmpty(message = "Street cannot be empty")
    @Size(max = 100, message = "Street name can have a maximum of 100 characters")
    private String street;

    @NotEmpty(message = "City cannot be empty")
    @Size(max = 50, message = "City name can have a maximum of 50 characters")
    private String city;

    @NotEmpty(message = "Pincode cannot be empty")
    @Pattern(regexp = "^[0-9]{6}$", message = "Pincode must be a 6-digit number")
    private String pincode;

    @NotEmpty(message = "Password cannot be empty")
    @Size(min = 4, max = 100, message = "Password must be between 4 and 100 characters")
    private String password;

    @NotEmpty(message = "Role cannot be empty")
    @Size(min = 3, max = 50, message = "Role name should be between 3 and 50 characters")
    private String role;

    private int status;

}
