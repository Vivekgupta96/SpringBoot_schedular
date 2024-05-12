package com.masai.SpringBootApp.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class MyErrorClass {

	private String message;
	private LocalDateTime localDateTimes;
	private String desc;

}
