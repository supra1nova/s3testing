package com.example.s3testing.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class RoomImage {

	@NotBlank(message= "Null 값이 들어왔습니다.")
	@URL(message = "유효한 url 형식이 아닙니다.")
	private String imageUrl;

	@NotNull(message= "Null 값이 들어왔습니다.")
	@PositiveOrZero(message = "유효한 숫자가 아닙니다.")
	private int roomId;
	 
	private LocalDateTime insDate;
	 
	private LocalDateTime updDate;
}
