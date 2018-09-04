package czachor.jakub.rooms.controller;

import czachor.jakub.rooms.exceptions.RoomDoesNotExistException;
import czachor.jakub.rooms.models.Room;
import czachor.jakub.rooms.models.RoomTest;
import czachor.jakub.rooms.models.asm.RoomAsm;
import czachor.jakub.rooms.models.dto.RoomDTO;
import czachor.jakub.rooms.service.RoomService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


public class RoomControllerTest {
    @Mock
    private RoomService roomService;

    @InjectMocks
    private RoomController roomController;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(roomController)
                .setControllerAdvice(new AppControllerAdvice())
                .build();
    }

    @Test
    public void getRoomByKeyTest() throws Exception {
        Room room = RoomTest.getTestInstance();
        room.setId(1L);
        RoomDTO roomDTO = new RoomAsm().toResource(room);
        when(roomService.getRoomByKey("key")).thenReturn(roomDTO);

        mockMvc.perform(get("/room/{key}", "key"))
                .andExpect(status().isOk())
                .andExpect(view().name("room"))
                .andExpect(model().attribute("room", hasProperty("name", is("name"))))
                .andExpect(model().attribute("room", hasProperty("info", is("info"))))
                .andExpect(model().attribute("room", hasProperty("key", is("key"))));
    }

    @Test
    public void getRoomByKeyExceptionTest() throws Exception {
        when(roomService.getRoomByKey("key")).thenThrow(new RoomDoesNotExistException("exception text"));

        mockMvc.perform(get("/room/{key}", "key"))
                .andExpect(status().isNotFound())
                .andExpect(view().name("notfound"))
                .andExpect(model().attribute("errormessage", "exception text"));
    }
}