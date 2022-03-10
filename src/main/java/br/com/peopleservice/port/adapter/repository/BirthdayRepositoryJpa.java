package br.com.peopleservice.port.adapter.repository;

import br.com.peopleservice.domain.Birthday;
import br.com.peopleservice.domain.repository.BirthdayRepository;
import br.com.peopleservice.domain.repository.PageableResult;
import br.com.peopleservice.domain.repository.PageableResultFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@Transactional
public class BirthdayRepositoryJpa implements BirthdayRepository {

    private final BirthdayRepositorySpringData repository;

    public BirthdayRepositoryJpa(BirthdayRepositorySpringData repository) {
        this.repository = repository;
    }

    @Override
    public Birthday save(Birthday people) {
        return repository.save(people);
    }

    @Override
    public void delete(Birthday people) {
        repository.delete(people);
    }

    @Override
    public Optional<Birthday> findById(UUID id) {
        return repository.findById(id);
    }

    @Override
    public List<Birthday> findAll() {
        return repository.findAll();
    }

    @Override
    public PageableResult<Birthday> findAllOrderBy(PageRequest pageRequest) {
        return PageableResultFactory.from(repository.findAll(pageRequest));
    }

    @Override
    public PageableResult<Birthday> findPeopleByNameLike(String name, PageRequest pageRequest) {
        return PageableResultFactory.from(repository.findByNameStartsWith(name, pageRequest));
    }

    @Override
    public PageableResult<Birthday> findByBirthDate(Integer day, Integer month, Pageable pageable) {
        return PageableResultFactory.from(repository.findByDayOrMonth(day, month, pageable));
    }

    @Override
    public PageableResult<Birthday> findByMonth(Integer month) {
        var pageRequest = PageRequest.of(
                0,
                20);

        return PageableResultFactory.from(repository.findByOrderByMonthAsc(month, pageRequest));
    }
}
