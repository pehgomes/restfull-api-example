package br.com.peopleservice.port.adapters.web;

import br.com.peopleservice.application.PeopleResponseData;
import br.com.peopleservice.application.SchedulesApplicationService;
import br.com.peopleservice.application.configuration.CustomJacksonObjectMapper;
import br.com.peopleservice.domain.repository.PageableResultFactory;
import br.com.peopleservice.port.adapter.web.ScheduleController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.UUID;

import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ScheduleControllerTest {

    private MockMvc mvc;
    private SchedulesApplicationService schedulesApplicationService = BDDMockito.mock(SchedulesApplicationService.class);
    final ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();

    @BeforeEach
    void setUp() {
        messageSource.setBasename("classpath:messages");
        messageSource.setDefaultEncoding(StandardCharsets.UTF_8.displayName());
        initMocks(this);

        mvc = MockMvcBuilders
                .standaloneSetup(new ScheduleController(schedulesApplicationService))
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setMessageConverters(new MappingJackson2HttpMessageConverter(CustomJacksonObjectMapper.objectMapper()))
                .build();
    }

    @Test
    public void shouldReturnBirthdayByMonth() throws Exception {
        var uuid = UUID.fromString("e26831ce-420e-45bf-ad4b-1de769c21aa8");

        Pageable pageRequest = PageRequest.of(0, 1);
        var response = List.of(new PeopleResponseData(uuid, "Pedro", "23/6"));
        Page<PeopleResponseData> pages = new PageImpl(response, pageRequest, 1);
        var result = PageableResultFactory.from(pages);

        BDDMockito.given(schedulesApplicationService.getPeopleByBirthDate(BDDMockito.any(), BDDMockito.any(), BDDMockito.any()))
                .willReturn(result);

        this.mvc.perform(get("/v1/schedules/birthdays/by-month")
                        .param("month", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.elements.[0].birthdayId").value("e26831ce-420e-45bf-ad4b-1de769c21aa8"));
    }

    @Test
    public void shouldReturnBirthdayByName() throws Exception {
        var uuid = UUID.fromString("e26831ce-420e-45bf-ad4b-1de769c21aa8");

        Pageable pageRequest = PageRequest.of(0, 1);
        var response = List.of(new PeopleResponseData(uuid, "Pedro", "23/6"));
        Page<PeopleResponseData> pages = new PageImpl(response, pageRequest, 1);
        var result = PageableResultFactory.from(pages);

        BDDMockito.given(schedulesApplicationService.getPeopleByNameLike(BDDMockito.any(), BDDMockito.any()))
                .willReturn(result);

        this.mvc.perform(get("/v1/schedules/birthdays/by-name")
                        .param("name", "P")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.elements.[0].birthdayId").value("e26831ce-420e-45bf-ad4b-1de769c21aa8"));
    }

    @Test
    public void shouldReturnBirthdaySortedByName() throws Exception {
        var uuid = UUID.fromString("e26831ce-420e-45bf-ad4b-1de769c21aa8");

        Pageable pageRequest = PageRequest.of(0, 1);
        var response = List.of(new PeopleResponseData(uuid, "Pedro", "23/6"));
        Page<PeopleResponseData> pages = new PageImpl(response, pageRequest, 1);
        var result = PageableResultFactory.from(pages);

        BDDMockito.given(schedulesApplicationService
                .getPeoplesScheduledOrderByNameOrDate(BDDMockito.any())).willReturn(result);

        this.mvc.perform(get("/v1/schedules/birthdays/sorted-by-name")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.elements.[0].birthdayId").value("e26831ce-420e-45bf-ad4b-1de769c21aa8"));
    }

    @Test
    public void shouldReturnBirthdaySortedByMonth() throws Exception {
        var uuid = UUID.fromString("e26831ce-420e-45bf-ad4b-1de769c21aa8");

        Pageable pageRequest = PageRequest.of(0, 1);
        var response = List.of(new PeopleResponseData(uuid, "Pedro", "23/6"));
        Page<PeopleResponseData> pages = new PageImpl(response, pageRequest, 1);
        var result = PageableResultFactory.from(pages);

        BDDMockito.given(schedulesApplicationService
                .getPeoplesScheduledOrderByNameOrDate(BDDMockito.any())).willReturn(result);

        this.mvc.perform(get("/v1/schedules/birthdays/sorted-by-month")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.elements.[0].birthdayId").value("e26831ce-420e-45bf-ad4b-1de769c21aa8"));

    }

    @Test
    public void shouldCreateAPeopleByCommand() throws Exception {
        var payload = "{\n" +
                "  \"day\": 23,\n" +
                "  \"month\": 6,\n" +
                "  \"name\": \"Pedro\"\n" +
                "}";

        var uuid = UUID.fromString("e26831ce-420e-45bf-ad4b-1de769c21aa8");

        BDDMockito.given(schedulesApplicationService.createBirthdays(BDDMockito.any()))
                .willReturn(uuid);

        this.mvc.perform(post("/v1/schedules/birthdays")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "http://localhost/v1/schedules/birthdays/" + uuid.toString()));
    }

    @Test
    public void shouldUpdateAPeopleByCommand() throws Exception {
        var payload = "{\n" +
                "  \"day\": 23,\n" +
                "  \"month\": 6,\n" +
                "  \"name\": \"Pedro\"\n" +
                "}";

        this.mvc.perform(put("/v1/schedules/birthdays/e26831ce-420e-45bf-ad4b-1de769c21aa8")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload))
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldDeleteAPeopleByCommand() throws Exception {
        this.mvc.perform(delete("/v1/schedules/birthdays/e26831ce-420e-45bf-ad4b-1de769c21aa8")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

    }
}
