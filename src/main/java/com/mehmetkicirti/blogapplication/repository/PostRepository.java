package com.mehmetkicirti.blogapplication.repository;

import com.mehmetkicirti.blogapplication.entity.concrete.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, String> {
}
