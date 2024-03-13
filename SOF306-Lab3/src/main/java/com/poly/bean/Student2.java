package com.poly.bean;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student2 {

	@NotBlank(message = "Không để trống email")
	@Email(message = "Không đúng định dạng email")
	private String email;

	@NotBlank(message = "Không để trống họ và tên")
	private String fullname;

	@NotNull(message = "Không để trống điểm")
	@PositiveOrZero(message = "Điểm phải lớn hoặc bằng 0")
	@Max(value = 10, message = "Điểm phải nhỏ hơn hoặc bằng 10")
	private Double marks;

	@NotNull(message = "Vui lòng chọn giới tính!")
	private Boolean gender;

	@NotBlank(message = "Vui lòng chọn quốc tịch!")
	private String country;
}