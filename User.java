package com.techconative.demo.bo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.persistence.GenerationType;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_details",
uniqueConstraints = {
		@UniqueConstraint(columnNames = "userName", name = "unique_userName_constraint"),
		@UniqueConstraint(columnNames = "email", name = "unique_email_constraint"),
        @UniqueConstraint(columnNames = "mobileNumber", name = "unique_mobileNumber_constraint"),
        @UniqueConstraint(columnNames = "aadharNumber", name = "unique_aadharNumber_constraint")
        
}
)
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Column(length = 32)
	@Size(min = 4, max = 30, message = "UserName must be at least 4 and maximum of 30 characters long")
	private String userName;
	
	@NotBlank
	@Column(length = 64)
	@Size(min = 10, max = 50, message = "Email must be at least 10 and maximum of 50 characters long")
	private String email;
	
	@NotBlank
    @Size(min = 6, max = 20, message = "Password must be at least 6 and maximum of 20 characters long")
	private String password;
	
	@NotBlank
	@Size(min = 12, max = 12, message = "Aadhar number must be 12 digits")
	@Digits(integer = 12, fraction = 0, message = "Aadhar number must consist only of digits")
	private String aadharNumber;
	
	@NotBlank
    @Size(min = 10, max = 10, message = "Mobile number must be 10 digits")
    @Digits(integer = 10, fraction = 0, message = "Mobile number must consist only of digits")
	private String mobileNumber;
	
	@Column(length = 64)
	private String backupEmail;

	@Column(name = "created_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false)
	private LocalDateTime createdAt;

	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", userName='" + userName + '\'' +
				", email='" + email + '\'' +
				", password='" + password + '\'' +
				", aadharNumber='" + aadharNumber + '\'' +
				", mobileNumber='" + mobileNumber + '\'' +
				", backupEmail='" + backupEmail + '\'' +
				", createdAt=" + createdAt +
				'}';
	}
}
