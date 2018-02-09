package com.glqdlt.bookmanager.persistence.entity;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;


@RequiredArgsConstructor
@Entity
@ToString
@NoArgsConstructor
@Data
public class BookEntity {

    @Id
    @GeneratedValue
    private int no;

    @NonNull
    private String subject;
    @NonNull
    private String author;
    @NonNull
    private int book_type;
    @NonNull
    private String note;
    @NonNull
    private String path;

    private String hash;

    @NonNull
    private String server_name;
    @NonNull
    private String reg_id;
    @NonNull
    private Date future_date;
    @NonNull
    private Date reg_date;
    @NonNull
    private Date update_date;
    @NonNull
    private int read_status;
    @NonNull
    private String thumbnail_url;
    @NonNull
    private String review_url;

    private String file_orign_name;
}
