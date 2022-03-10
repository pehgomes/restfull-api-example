package br.com.peopleservice.application;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.UUID;

@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class PeopleResponseData {

    @JsonProperty
    UUID birthdayId;
    @JsonProperty
    String name;
    @JsonProperty
    String birthDay;
}
