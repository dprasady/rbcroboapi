package org.rbc.robo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.rbc.robo.controller.RobotController;
import org.rbc.robo.model.Robot;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
public class RobotControllerTest {

    private MockMvc mockMvc;
    private static String URL = "/robot/";

    @Mock
    private RobotController robotController;


    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(robotController)
                .build();
    }

    @Test
    public void findByRobotId() throws Exception {
        Robot robot = new Robot();
        robot.setId(1L);
        robot.setName("test-robot");
        given(robotController.findByRobotId(robot.getId())).willReturn(robot);
        mockMvc.perform(get(URL + "/show/" + robot.getId())
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id", is(robot.getId().intValue())))
                .andExpect(jsonPath("name", is(robot.getName())));
    }

    @Test
    public void listAllRobots() throws Exception {

        Robot robot1 = new Robot();
        robot1.setId(1L);
        robot1.setName("test-robot-1");

        Robot robot2 = new Robot();
        robot2.setId(2L);
        robot2.setName("test-robot-2");

        List<Robot> robotList = Arrays.asList(robot1, robot2);
        given(robotController.listAllRobots()).willReturn(robotList);

        mockMvc.perform(get(URL+"list")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(robot1.getId().intValue())))
                .andExpect(jsonPath("$[0].name", is(robot1.getName())))
                .andExpect(jsonPath("$[1].id", is(robot2.getId().intValue())))
                .andExpect(jsonPath("$[1].name", is(robot2.getName())));
    }

    @Test
    public void addRobot() throws Exception {
        Robot robot = new Robot();
        robot.setId(1L);
        robot.setName("test-robot");

        String json = "{\"id\":1,\"name\":\"test-robot\"}";

        when(robotController.addRobot(Mockito.any(Robot.class))).thenReturn(robot);

        RequestBuilder requestBuilder = post(URL+"/add")
                .accept(MediaType.APPLICATION_JSON).content(json)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals(response.getContentAsString(), json);
    }

    @Test
    public void deleteByRobotId() throws Exception {
        Robot robot = new Robot();
        robot.setId(1L);
        robot.setName("test-robot");
        when(robotController.findByRobotId(robot.getId())).thenReturn(robot);
        // execute
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.delete(URL + "/delete/{id}", robot.getId()))
                .andReturn();
        // verify
        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
        // verify that service method was called once
        verify(robotController).deleteByRobotId(any(Long.class));
    }
}
