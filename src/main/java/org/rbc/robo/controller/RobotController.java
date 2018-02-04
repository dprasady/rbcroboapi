package org.rbc.robo.controller;

import org.rbc.robo.model.Robot;
import org.rbc.robo.repository.RobotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/robot")
public class RobotController {

    @Autowired
    private RobotService robotService;

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Robot addRobot(@RequestBody final Robot robot) {
        return robotService.save(robot);
    }

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Robot> listAllRobots() {
        return robotService.findAll();
    }

    @GetMapping(value = "/show/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Robot findByRobotId(@PathVariable final Long id) {
        return robotService.findOne(id);
    }

    @DeleteMapping(value = "/delete/{id}")
    public void deleteByRobotId(@PathVariable final Long id) {
        robotService.delete(id);
    }
}
