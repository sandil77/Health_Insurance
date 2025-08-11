package com.cms.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import org.springframework.beans.BeanUtils;

import com.cms.user.entity.User;

import lombok.Data;

@Data
public class UserRegisterDto {

	private int id;

	@NotEmpty(message = "First name cannot be empty")
	@Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
	private String firstName;

	@NotEmpty(message = "Last name cannot be empty")
	@Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
	private String lastName;

	@NotNull(message = "Age cannot be null")
	@Min(value = 18, message = "Age must be at least 18")
	private Integer age;

	@NotEmpty(message = "Gender cannot be empty")
	@Pattern(regexp = "^(Male|Female|Other)$", message = "Gender must be Male, Female, or Other")
	private String gender;

	@NotEmpty(message = "Email cannot be empty")
	@Email(message = "Invalid email format")
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
	@Size(min = 8, max = 100, message = "Password must be between 8 and 100 characters")
	private String password;

	@NotEmpty(message = "Role cannot be empty")
	@Size(min = 3, max = 50, message = "Role name should be between 3 and 50 characters")
	private String role;

	public static User toEntity(UserRegisterDto userRegisterDto) {
		User user = new User();
		BeanUtils.copyProperties(userRegisterDto, user);
		return user;
	}
}
