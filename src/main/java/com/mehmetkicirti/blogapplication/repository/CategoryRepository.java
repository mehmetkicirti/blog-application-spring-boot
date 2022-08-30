package com.mehmetkicirti.blogapplication.repository;

import com.mehmetkicirti.blogapplication.entity.concrete.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, String> {
}
