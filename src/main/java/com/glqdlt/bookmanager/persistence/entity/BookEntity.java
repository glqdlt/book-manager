package com.glqdlt.bookmanager.persistence.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;




@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BookEntity {

    @Id
    @GeneratedValue
    private int no;

    private String subject;
    private String author;
    private int book_type;
    private String note;
    private String path;
    private String server_name;

    private String reg_id;
    private Date future_date;
    private Date reg_date;
    private Date update_date;

    private int read_status;
    private String thumbnail_url;
    private String review_url;

}
