package com.example.ecommerce_bijouterie1.dto.request;

import com.example.ecommerce_bijouterie1.constants.ErrorMessage;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderRequest {
    private Long id;
    private Double totalPrice;
    private LocalDateTime date = LocalDateTime.now();

    @NotBlank(message = ErrorMessage.FILL_IN_THE_INPUT_FIELD)
    private String firstName;

    @NotBlank(message = ErrorMessage.FILL_IN_THE_INPUT_FIELD)
    private String lastName;

    @NotBlank(message = ErrorMessage.FILL_IN_THE_INPUT_FIELD)
    private String city;

    @NotBlank(message = ErrorMessage.FILL_IN_THE_INPUT_FIELD)
    private String address;

    @Email(message = ErrorMessage.INCORRECT_EMAIL)
    @NotBlank(message = ErrorMessage.EMAIL_CANNOT_BE_EMPTY)
    private String email;

    @NotBlank(message = ErrorMessage.EMPTY_PHONE_NUMBER)
    private String phoneNumber;

    @Min(value = 5, message = ErrorMessage.EMPTY_POST_INDEX)
    private Integer postIndex;
}
