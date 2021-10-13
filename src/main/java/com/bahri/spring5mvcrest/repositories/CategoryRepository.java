package com.bahri.spring5mvcrest.repositories;

import com.bahri.spring5mvcrest.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
