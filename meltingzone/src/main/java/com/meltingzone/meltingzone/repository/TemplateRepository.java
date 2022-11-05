package com.meltingzone.meltingzone.repository;

import com.meltingzone.meltingzone.domain.Template;
import com.meltingzone.meltingzone.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TemplateRepository extends JpaRepository<Template, Long> {

    List<Template> findAllByUser(User user);
}
