package br.com.peopleservice.port.adapter.web;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;


@Value
@AllArgsConstructor
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@EqualsAndHashCode
@ToString
public class BirthdayCommand {

    @NotNull
    @ApiModelProperty(value = "birthday name", example = "Pedro")
    String name;

    @NotNull
    @ApiModelProperty(value = "day of birthDate", example = "23")
    Integer day;

    @NotNull
    @ApiModelProperty(value = "month of birthdate", example = "6")
    Integer month;
}
