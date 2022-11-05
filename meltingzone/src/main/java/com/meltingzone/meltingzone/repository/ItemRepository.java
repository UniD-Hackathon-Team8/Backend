package com.meltingzone.meltingzone.repository;

import com.meltingzone.meltingzone.domain.Template;
import com.meltingzone.meltingzone.domain.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByTemplate(Template template);
}
