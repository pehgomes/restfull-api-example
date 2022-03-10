package br.com.peopleservice.port.adapter.repository;

import br.com.peopleservice.domain.Birthday;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BirthdayRepositorySpringData extends JpaRepository<Birthday, UUID> {

    Page<Birthday> findByNameStartsWith(String name, Pageable pageRequest);

    Page<Birthday> findByDayOrMonth(Integer day, Integer month, Pageable pageRequest);

    Page<Birthday> findByOrderByMonthAsc(Integer month, Pageable pageRequest);


}
