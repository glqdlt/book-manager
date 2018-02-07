package com.glqdlt.bookmanager.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Data
@Entity
public class ServerConfigEntity {

    @Id
    @GeneratedValue
    private Integer no;
    private String data_path;
}
