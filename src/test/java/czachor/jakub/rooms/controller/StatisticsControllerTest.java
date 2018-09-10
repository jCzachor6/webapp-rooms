package czachor.jakub.rooms.controller;

import czachor.jakub.rooms.models.dto.StatisticsDTO;
import czachor.jakub.rooms.service.StatisticsService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class StatisticsControllerTest {
    @Mock
    private StatisticsService service;

    @InjectMocks
    private StatisticsController controller;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .build();
    }

    @Test
    public void getTotalUsersJoinedInTest() throws Exception {
        StatisticsDTO statisticsDTO = new StatisticsDTO();
        statisticsDTO.setName("samplestat");
        statisticsDTO.setValue("5");
        when(service.getTotalUsersJoined()).thenReturn(statisticsDTO);
        mockMvc.perform(get("/stat/total"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("samplestat"))
                .andExpect(jsonPath("$.value").value("5"));
    }

    @Test
    public void getAllStatisticsTest() throws Exception {
        StatisticsDTO stat1 = new StatisticsDTO();
        stat1.setName("stat1");
        stat1.setValue("1");
        StatisticsDTO stat2 = new StatisticsDTO();
        stat2.setName("stat2");
        stat2.setValue("2");
        List statistics = Arrays.asList(stat1, stat2);

        when(service.getAllStatistics()).thenReturn(statistics);
        mockMvc.perform(get("/stat/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*").isArray())
                .andExpect(jsonPath("$.[0].name").value("stat1"))
                .andExpect(jsonPath("$.[0].value").value("1"))
                .andExpect(jsonPath("$.[1].name").value("stat2"))
                .andExpect(jsonPath("$.[1].value").value("2"));
    }
}