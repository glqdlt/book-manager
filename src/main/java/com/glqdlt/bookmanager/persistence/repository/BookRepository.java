package com.glqdlt.bookmanager.persistence.repository;

import com.glqdlt.bookmanager.persistence.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository  extends JpaRepository<BookEntity, Integer>{

}
