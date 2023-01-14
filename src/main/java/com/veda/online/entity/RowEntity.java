package com.veda.online.entity;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "row_data")
@Data
public class RowEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "row_id", nullable = false)
    private String rowId;

    @Column(name = "parent_id", nullable = false)
    private Long parentId;
}
