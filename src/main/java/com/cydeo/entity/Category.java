package com.cydeo.entity;

import com.cydeo.entity.common.BaseEntity;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;

@Entity
@Where(clause = "is_deleted = false")
public class Category extends BaseEntity {
}
