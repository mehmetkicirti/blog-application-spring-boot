package com.mehmetkicirti.blogapplication.entity.concrete;

import com.mehmetkicirti.blogapplication.entity.abstracts.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "posts",
        uniqueConstraints = {
        @UniqueConstraint(columnNames = {"title"})
})
public class Post extends BaseEntity {
    private String title;
    private String description;
    @Lob
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Comment> comments = new HashSet<>();
}
