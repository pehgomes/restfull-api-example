package br.com.peopleservice.domain.repository;

import br.com.peopleservice.domain.Birthday;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BirthdayRepository {

    default UUID nextPeopleId() {
        return UUID.randomUUID();
    }

    Birthday save(Birthday people);

    void delete(Birthday people);

    Optional<Birthday> findById(UUID id);

    List<Birthday> findAll();

    PageableResult<Birthday> findAllOrderBy(PageRequest sort);

    PageableResult<Birthday> findPeopleByNameLike(String name, PageRequest pageRequest);

    PageableResult<Birthday> findByBirthDate(Integer day, Integer month, Pageable pageable);

    PageableResult<Birthday> findByMonth(Integer month);

}
