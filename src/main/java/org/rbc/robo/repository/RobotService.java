package org.rbc.robo.repository;

import org.rbc.robo.model.Robot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component

public interface RobotService extends JpaRepository<Robot, Long> {
}
