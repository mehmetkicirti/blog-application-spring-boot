package com.mehmetkicirti.blogapplication.entity.concrete;

import com.mehmetkicirti.blogapplication.entity.abstracts.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "roles")
public class Role extends BaseEntity {
    @Column(length = 60)
    private String name;

    public Role(String name) {
        this.name = name;
    }
}
