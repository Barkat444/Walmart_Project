package com.stackroute.wipro.backend.User.model;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name="user_table")
public class UserData {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long userId;

	@NotNull(message = "userName should not be null")
	//@Min(3)
	//@Max(30)
//	@Column(unique = true)
	private String userName;

	//@Column(unique = true)
//	@Email(message = "use valid email id")
	private  String email;

//	@Min(8)
//	@Max(20)
	private String password;

//	@NotBlank(message = "role cannot be left blank")
	private String role;

	public UserData() {
	}

	public UserData(Long userId, String userName, String email, String password, String role) {
		this.userId = userId;
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.role = role;
	}

	public void setRole(String role) {
		this.role = role;
	}


	public String getRole() {
		return role;
	}



	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return userName;
	}

	public void setUsername(String username) {
		this.userName = username;
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

	@Override
	public String toString() {
		return "UserData{" +
				"userId=" + userId +
				", userName='" + userName + '\'' +
				", email='" + email + '\'' +
				", password='" + password + '\'' +
				", role='" + role + '\'' +
				'}';
	}
//    public Integer getWishlistId() {
//        return wishlistId;
//    }

	//   public void setWishlistId(Integer wishlistId) {
	//     this.wishlistId = wishlistId;
	//}
}