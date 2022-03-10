package br.com.peopleservice.domain.repository;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Data
@EqualsAndHashCode
@Accessors(fluent = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class PageableResult<T> {

    @JsonProperty("elements")
    List<T> elements;

    @JsonProperty("page")
    Page page;

    @JsonProperty("navigation")
    Navigation navigation;

    @Value
    @AllArgsConstructor
    @Builder
    public static class Page {
        @JsonProperty
        private Integer number;
        @JsonProperty
        private Integer size;
        @JsonProperty
        private Integer totalPages;
        @JsonProperty
        private Long totalElements;
    }

    @Value
    @AllArgsConstructor
    @Builder
    public static class Navigation {
        @JsonProperty
        private Integer first;
        @JsonProperty
        private Integer previous;
        @JsonProperty
        private Integer next;
        @JsonProperty
        private Integer last;
    }

    public <U> PageableResult<U> map(Function<T, U> function) {
        return new PageableResult<>(elements().stream()
                .map(function)
                .collect(Collectors.toList()),
                page(),
                navigation());
    }
}
