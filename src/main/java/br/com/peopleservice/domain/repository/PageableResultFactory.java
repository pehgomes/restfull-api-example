package br.com.peopleservice.domain.repository;

import org.springframework.data.domain.Page;

public class PageableResultFactory {
    private PageableResultFactory() {
    }

    public static <T> PageableResult<T> from(Page<T> page) {
        int nonZeroPageNumber = page.getNumber() + 1;
        return new PageableResult<>(page.getContent(),
                new PageableResult.Page(nonZeroPageNumber,
                        page.getSize(),
                        page.getTotalPages(),
                        page.getTotalElements()),
                new PageableResult.Navigation(1,
                        page.isFirst() ? null : nonZeroPageNumber - 1,
                        page.isLast() ? null : nonZeroPageNumber + 1,
                        page.getTotalPages()));
    }
}
