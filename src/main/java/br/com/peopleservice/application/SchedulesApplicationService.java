package br.com.peopleservice.application;

import br.com.peopleservice.domain.Birthday;
import br.com.peopleservice.domain.repository.BirthdayRepository;
import br.com.peopleservice.domain.repository.PageableResult;
import br.com.peopleservice.port.adapter.web.BirthdayCommand;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SchedulesApplicationService {

    private final BirthdayRepository birthdayRepository;

    public SchedulesApplicationService(BirthdayRepository peopleRepository) {
        this.birthdayRepository = peopleRepository;
    }

    public UUID createBirthdays(BirthdayCommand peopleCommand) {
        var people = birthdayRepository.save(new Birthday(
                birthdayRepository.nextPeopleId(),
                peopleCommand.getName(),
                peopleCommand.getDay(),
                peopleCommand.getMonth()
        ));

        return people.getBirthdayId();
    }

    public void removePeople(UUID peopleId) {
        var people = birthdayRepository.findById(peopleId)
                .orElseThrow(() -> new RuntimeException("People not found"));

        birthdayRepository.delete(people);
    }

    public void updatePeople(UUID peopleId, BirthdayCommand peopleCommand) {
        var people = birthdayRepository.findById(peopleId)
                .orElseThrow(() -> new RuntimeException("People not found"));

        people.setName(peopleCommand.getName());
        people.setDay(peopleCommand.getDay());
        people.setMonth(peopleCommand.getMonth());

        birthdayRepository.save(people);
    }

    public PageableResult<PeopleResponseData> getPeopleByBirthDate(Integer day, Integer month, Pageable pageable) {
        return birthdayRepository.findByBirthDate(day, month, pageable)
                .map(people -> new PeopleResponseData(people.getBirthdayId(),
                        people.getName(),
                        people.getDay() + "/" + people.getMonth()));
    }

    public PageableResult<PeopleResponseData> getPeopleByNameLike(String name, PageRequest pageable) {
        return birthdayRepository.findPeopleByNameLike(name, pageable)
                .map(people -> new PeopleResponseData(people.getBirthdayId(),
                        people.getName(),
                        people.getDay() + "/" + people.getMonth()));
    }

    public PageableResult<PeopleResponseData> getPeoplesScheduledOrderByNameOrDate(PageRequest pageable) {
        return birthdayRepository.findAllOrderBy(pageable)
                .map(people -> new PeopleResponseData(people.getBirthdayId(),
                        people.getName(),
                        people.getDay() + "/" + people.getMonth()));
    }
}