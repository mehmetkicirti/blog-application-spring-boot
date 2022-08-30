package com.mehmetkicirti.blogapplication.repository;

import com.mehmetkicirti.blogapplication.entity.concrete.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, String> {
}
