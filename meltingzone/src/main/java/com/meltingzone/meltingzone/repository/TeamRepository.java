package com.meltingzone.meltingzone.repository;

import com.meltingzone.meltingzone.domain.Presentation;
import com.meltingzone.meltingzone.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
    List<Team> findAllByPresentationId(Long presentationId);
}
