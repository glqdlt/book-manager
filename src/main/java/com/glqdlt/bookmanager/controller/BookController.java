package com.glqdlt.bookmanager.controller;

import com.glqdlt.bookmanager.persistence.entity.BookEntity;
import com.glqdlt.bookmanager.persistence.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
public class BookController {

    private static final Logger log = LoggerFactory.getLogger(BookController.class);
    private static final Integer PAGE_COUNT = 10;
    /**
     * 토이 프로젝트라서 서비스 없이 바로 접근해서 조회,
     */
    @Autowired
    BookRepository bookRepository;

    @RequestMapping(value = "/book/search/all", method = RequestMethod.GET)
    public ResponseEntity<List<BookEntity>> bookSearch() {
        List<BookEntity> list = bookRepository.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @RequestMapping(value = "/book/search/{page}", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> bookSearchPage(@PathVariable int page) {
        log.info("page:" + page);
        Pageable pageable = new PageRequest(page, PAGE_COUNT, new Sort(Sort.Direction.DESC, "no"));
        Page<BookEntity> entityPage = bookRepository.findAll(pageable);
        List<BookEntity> list = new ArrayList<>();
        // 이것은 좋지 않다. 테스트용이니 여유 될 때
        // fixme map -> Object
        Map<String, Object> map = new HashMap<>();
        entityPage.forEach(x -> list.add(x));
        map.put("totalPage", entityPage.getTotalPages());
        map.put("data", list);

        map.forEach((x, y) -> System.out.println(y));
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @RequestMapping(value = "/book/detail/{id}", method = RequestMethod.GET)
    public ResponseEntity<BookEntity> bookDetail(@PathVariable int id) {
        BookEntity bookEntity = bookRepository.findOne(id);


        return new ResponseEntity<>(bookEntity, HttpStatus.OK);
    }

    @RequestMapping(value = "/book/write", method = RequestMethod.PUT)
    public ResponseEntity<Integer> bookWrite(@RequestBody BookEntity bookEntity) {
        log.info(bookEntity.toString());
        bookRepository.save(bookEntity);
        return new ResponseEntity<>(1, HttpStatus.OK);
    }

    @RequestMapping(value = "/book/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Integer> bookUpdate(@PathVariable int id, @RequestBody BookEntity bookEntity) {
        log.info((bookEntity.toString()));
        bookRepository.updateBookEntity(id, bookEntity.getSubject(), bookEntity.getAuthor(), bookEntity.getBook_type(), bookEntity.getNote(), bookEntity
                        .getPath(), bookEntity.getServer_name(), bookEntity.getFuture_date(), bookEntity.getUpdate_date(), bookEntity.getRead_status(),
                bookEntity.getThumbnail_url(), bookEntity.getReview_url());
        return new ResponseEntity<>(1, HttpStatus.OK);
    }

    @RequestMapping(value = "/book/remove/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Integer> bookRemove(@PathVariable int id) {
        bookRepository.deleteByNo(id);
        return new ResponseEntity<>(1, HttpStatus.OK);
    }

    @RequestMapping(value = "/book/download/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> bookDownload(@PathVariable int id) throws IOException {

        // FIXME 실제 데이터베이스에 id 조회를 해서 다운로드 경로를 얻어 오는 로직이 필요하다.
        String path = "C:\\Users\\iw.jhun\\Downloads\\d3.zip";
        File file = new File(path);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        headers.setContentLength(file.length());
        headers.setContentDispositionFormData("attachment", URLEncoder.encode(file.getName(), "UTF-8"));
        ByteArrayResource byteArrayResource = new ByteArrayResource(Files.readAllBytes(Paths.get(file.getAbsolutePath())));

        return new ResponseEntity<>(byteArrayResource, headers, HttpStatus.OK);
    }

    @RequestMapping("/upload")
    public class FileUploadController {

        @RequestMapping(method = RequestMethod.POST)
        public ResponseEntity handleFileUpload(
                @RequestParam("user-file") MultipartFile multipartFile) throws IOException {
            String name = multipartFile.getOriginalFilename();
            System.out.println("File name: "+name);
            //todo save to a file via multipartFile.getInputStream()
            byte[] bytes = multipartFile.getBytes();
            System.out.println("File uploaded content:\n" + new String(bytes));
            return new ResponseEntity(HttpStatus.OK);
        }
    }
}
