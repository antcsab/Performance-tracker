package com.itschool.performance_tracker.repository;

import com.itschool.performance_tracker.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player,Long>, JpaSpecificationExecutor<Player> {
}
