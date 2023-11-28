package com.ll.sb231127.global.jpa.baseEntity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@MappedSuperclass
@SuperBuilder
@NoArgsConstructor(access = PROTECTED)
@Getter
@EqualsAndHashCode
@ToString
public class BaseEntity {
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
}