package com.avyudaya.blogservice.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Table(name = "blogs")
@Getter
@Setter
public class Blog {
    @Id
    @UuidGenerator
    private String id;

    private String title;
    private String description;
}
