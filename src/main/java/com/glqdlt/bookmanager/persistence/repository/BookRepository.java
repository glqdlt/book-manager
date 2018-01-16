package com.glqdlt.bookmanager.persistence.repository;

import com.glqdlt.bookmanager.persistence.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

public interface BookRepository extends JpaRepository<BookEntity, Integer> {

    @Modifying
    @Transactional
    @Query("UPDATE BookEntity b " +
            "set b.subject = :subject, " +
            "b.author = :author, " +
            "b.path = :path, " +
            "b.server_name =:server_name ," +
            "b.read_status = :read_status, " +
            "b.update_date = :update_date, " +
            "b.book_type =:book_type, " +
            "b.future_date = :future_date, " +
            "b.thumbnail_url = :thumbnail_url, " +
            "b.review_url = :review_url, " +
            "b.note = :note " +
            "where b.no = :no")
    void updateBookEntity(
            @Param("no") int no,
            @Param("subject") String subject,
            @Param("author") String author,
            @Param("book_type") int book_type,
            @Param("note") String note,
            @Param("path") String path,
            @Param("server_name") String server_name,
            @Param("future_date") Date future_date,
            @Param("update_date") Date update_date,
            @Param("read_status") int read_status,
            @Param("thumbnail_url") String thumbnail_url,
            @Param("review_url") String review_url);


    @Transactional
    void deleteByNo(@Param("no") int no);

}
