package br.com.peopleservice.port.adapter.web;

import br.com.peopleservice.application.PeopleResponseData;
import br.com.peopleservice.application.SchedulesApplicationService;
import br.com.peopleservice.domain.repository.PageableResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentContextPath;

@RestController
@RequestMapping("/v1/schedules")
@Api(tags = "Schedule Controller", value = "BirthdayCommand", description = "Controller for Birthdays Schedules")
public class ScheduleController {

    private final SchedulesApplicationService peopleApplicationService;

    public ScheduleController(SchedulesApplicationService peopleApplicationService) {
        this.peopleApplicationService = peopleApplicationService;
    }

    @GetMapping("/birthdays/by-month")
    @ApiOperation(value = "Returns a people by birth date using day and month",
            response = PageableResult.class,
            produces = "application/json")
    public PageableResult<PeopleResponseData> getPeopleByDate(
            @RequestParam(defaultValue = "0", required = false) Integer page,
            @RequestParam(defaultValue = "20", required = false) Integer size,
            @RequestParam(defaultValue = "2") @ApiParam(defaultValue = "2") Integer month) {

        var pageRequest = PageRequest.of(
                page != null && page > 0 ? page - 1 : 0,
                size != null && size > 0 && size < 20 ? size : 20);

        return peopleApplicationService.getPeopleByBirthDate(null, month, pageRequest);
    }

    @GetMapping("/birthdays/by-name")
    @ApiOperation(value = "Returns a people by birth date using day and month",
            response = PageableResult.class,
            produces = "application/json")
    public PageableResult<PeopleResponseData> getPeopleByDate(
            @RequestParam @ApiParam(example = "P") String name,
            @RequestParam(defaultValue = "0", required = false) Integer page,
            @RequestParam(defaultValue = "20", required = false) Integer size) {

        var pageRequest = PageRequest.of(
                page != null && page > 0 ? page - 1 : 0,
                size != null && size > 0 && size < 20 ? size : 20);

        return peopleApplicationService.getPeopleByNameLike(name, pageRequest);
    }

    @GetMapping("/birthdays/sorted-by-name")
    @ApiOperation(value = "Returns the complete schedule sorted by name, ASC or DESC",
            response = PeopleResponseData.class,
            produces = "application/json")
    public PageableResult<PeopleResponseData> getScheduleByName(
            @RequestParam(defaultValue = "0", required = false) Integer page,
            @RequestParam(defaultValue = "20", required = false) Integer size) {

        var pageRequest = PageRequest.of(
                page != null && page > 0 ? page - 1 : 0,
                size != null && size > 0 && size < 20 ? size : 20,
                Sort.by(Sort.Direction.ASC,"name"));

        return peopleApplicationService.getPeoplesScheduledOrderByNameOrDate(pageRequest);
    }

    @GetMapping("/birthdays/sorted-by-month")
    @ApiOperation(value = "Returns the complete schedule sorted by name, ASC or DESC",
            response = PeopleResponseData.class,
            produces = "application/json")
    public PageableResult<PeopleResponseData> getScheduleByMonth(
            @RequestParam(defaultValue = "0", required = false) Integer page,
            @RequestParam(defaultValue = "20", required = false) Integer size) {

        var pageRequest = PageRequest.of(
                page != null && page > 0 ? page - 1 : 0,
                size != null && size > 0 && size < 20 ? size : 20,
                Sort.by(Sort.Direction.ASC,"month"));

        return peopleApplicationService.getPeoplesScheduledOrderByNameOrDate(pageRequest);
    }

    @PostMapping("/birthdays")
    @ApiOperation(value = "Create a people by people command",
            produces = "application/json", code = 201)
    public ResponseEntity<Void> newPeople(@RequestBody @ApiParam BirthdayCommand peopleCommand) {

        var peopleId = peopleApplicationService.createBirthdays(peopleCommand);

        return ResponseEntity.created(fromCurrentContextPath()
                .path("/v1/schedules/birthdays/{birthdayId}")
                .buildAndExpand(peopleId.toString())
                .toUri()).build();
    }

    @PutMapping("/birthdays/{birthdayId}")
    @ApiOperation(value = "Update a specific people by id, passing people command." +
            " BirthdayId obtained from location from previous POST is required.", code = 204,
            produces = "application/json")
    public ResponseEntity<Void> updatePeople(@PathVariable UUID birthdayId, @RequestBody @ApiParam BirthdayCommand peopleCommand) {
        peopleApplicationService.updatePeople(birthdayId, peopleCommand);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/birthdays/{birthdayId}")
    @ApiOperation(value = "Excludes people by id. " +
            " BirthdayId obtained from location from previous POST is required.", code = 204,
            produces = "application/json")
    public ResponseEntity<Void> deletePeople(@PathVariable @ApiParam UUID birthdayId) {
        peopleApplicationService.removePeople(birthdayId);
        return ResponseEntity.noContent().build();
    }

}