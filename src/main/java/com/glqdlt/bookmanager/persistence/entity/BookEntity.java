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
@Data
public class BookEntity {
    public BookEntity(String subject, String author, int book_type, String note, String path, String server_name, String reg_id, Date future_date, Date reg_date, Date update_date, int read_status, String thumbnail_url, String review_url) {
        this.subject = subject;
        this.author = author;
        this.book_type = book_type;
        this.note = note;
        this.path = path;
        this.server_name = server_name;
        this.reg_id = reg_id;
        this.future_date = future_date;
        this.reg_date = reg_date;
        this.update_date = update_date;
        this.read_status = read_status;
        this.thumbnail_url = thumbnail_url;
        this.review_url = review_url;
    }

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
