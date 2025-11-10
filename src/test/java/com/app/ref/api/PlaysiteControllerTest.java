package com.app.ref.api;

import com.app.ref.TestUtils;
import com.app.ref.domain.dto.PlaySiteRequest;
import com.app.ref.domain.dto.PlaySiteResponse;
import com.app.ref.service.PlaySiteService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PlaysiteControllerTest {

    @MockBean
    private PlaySiteService playSiteService;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    @AfterEach
    void tearDown() {
        verifyNoMoreInteractions(playSiteService);
    }


    @Test
    void createPlaySite() throws Exception {

        String requestJson = "{\n" +
                "  \"ball_pit_count\": 2,\n" +
                "  \"carousel_count\": 3,\n" +
                "  \"slide_count\": 4,\n" +
                "  \"double_swing_count\": 5\n" +
                "}";

        PlaySiteRequest playSiteRequest = TestUtils.OBJECT_MAPPER.readValue(requestJson, PlaySiteRequest.class);

        when(playSiteService.savePlaySite(playSiteRequest)).thenReturn(new PlaySiteResponse());

        mockMvc.perform(post("/api/playsite")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content(requestJson)
                )
                .andExpect(status().isOk());

        verify(playSiteService, times(1)).savePlaySite(playSiteRequest);
    }

    @Test
    void updatePlaySite() throws Exception {

        String requestJson = "{\n" +
                "  \"ball_pit_count\": 2,\n" +
                "  \"carousel_count\": 3,\n" +
                "  \"slide_count\": 4,\n" +
                "  \"double_swing_count\": 5\n" +
                "}";

        PlaySiteRequest playSiteRequest = TestUtils.OBJECT_MAPPER.readValue(requestJson, PlaySiteRequest.class);
        Long playSiteId = 1L;

        when(playSiteService.updatePlaySite(playSiteId, playSiteRequest)).thenReturn(new PlaySiteResponse());

        mockMvc.perform(put("/api/playsite/1")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content(requestJson)
                )
                .andExpect(status().isOk());

        verify(playSiteService, times(1)).updatePlaySite(playSiteId, playSiteRequest);
    }

    @Test
    void getPlaySiteInfo() throws Exception {
        Long playSiteId = 1L;

        when(playSiteService.getPlaySiteInfo(playSiteId)).thenReturn(new PlaySiteResponse());

        mockMvc.perform(get("/api/playsite/1")
                        .contentType(APPLICATION_JSON_VALUE)
                ).andExpect(status().isOk());

        verify(playSiteService, times(1)).getPlaySiteInfo(playSiteId);
    }

    @Test
    void deletePlaySite() throws Exception {
        Long playSiteId = 1L;

        mockMvc.perform(delete("/api/playsite/1")
                .contentType(APPLICATION_JSON_VALUE)
        ).andExpect(status().isOk());

        verify(playSiteService, times(1)).deletePlaySite(playSiteId);
    }
}