package br.com.peopleservice.application;

import lombok.*;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@EqualsAndHashCode
@Accessors(fluent = true)
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class ScheduleResponseData {

    String title;
    Integer day;
    Integer month;
    List<PeopleResponseData> birthdays;
}
