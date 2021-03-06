package com.glqdlt.bookmanager.api;

import com.glqdlt.bookmanager.persistence.entity.BookEntity;
import com.glqdlt.bookmanager.persistence.repository.BookRepository;
import com.glqdlt.bookmanager.service.FileHandlingUtil;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

import javax.servlet.Servlet;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Slf4j
@RequestMapping("/api/book")
@CrossOrigin
@RestController
public class BookRestController {

    private static final Integer PAGE_COUNT = 10;
    /**
     * 토이 프로젝트라서 서비스 없이 바로 접근해서 조회,
     */
    @Autowired
    BookRepository bookRepository;

    @Autowired
    FileHandlingUtil handler;

    @Value("${upload.path}")
    String upload_path;

    @RequestMapping(value = "/search/all", method = RequestMethod.GET)
    public ResponseEntity<List<BookEntity>> getBookSelectAll() {
        List<BookEntity> list = bookRepository.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @RequestMapping(value = "/search/{page}", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getBookSelectAllByPage(@PathVariable int page) {
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

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public ResponseEntity<BookEntity> getBookSelectDetailById(@PathVariable int id) {
        BookEntity bookEntity = bookRepository.findOne(id);


        return new ResponseEntity<>(bookEntity, HttpStatus.OK);
    }

    @RequestMapping(value = "/write", method = RequestMethod.PUT)
    public ResponseEntity<Integer> putBookCreate(@RequestBody BookEntity bookEntity) {
        log.info(bookEntity.toString());
        bookRepository.save(bookEntity);
        return new ResponseEntity<>(1, HttpStatus.OK);
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Integer> putBookUpdateAllById(@PathVariable int id, @RequestBody BookEntity bookEntity) {
        log.info((bookEntity.toString()));
        bookRepository.updateBookEntity(id, bookEntity.getSubject(), bookEntity.getAuthor(), bookEntity.getBook_type(), bookEntity.getNote(), bookEntity
                        .getPath(), bookEntity.getServer_name(), bookEntity.getFuture_date(), bookEntity.getUpdate_date(), bookEntity.getRead_status(),
                bookEntity.getThumbnail_url(), bookEntity.getReview_url());
        return new ResponseEntity<>(1, HttpStatus.OK);
    }

    @RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Integer> deleteBookRemoveById(@PathVariable int id) {
        bookRepository.deleteByNo(id);
        return new ResponseEntity<>(1, HttpStatus.OK);
    }

    @RequestMapping(value = "/download/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getBookDownloadById(@PathVariable int id) throws IOException {

        // FIXME 실제 데이터베이스에 id 조회를 해서 다운로드 경로를 얻어 오는 로직이 필요하다.
        String path = "C:\\Users\\iw.jhun\\Downloads\\curl-7.58.0.zip";
        File file = new File(path);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        headers.setContentLength(file.length());
        headers.setContentDispositionFormData("attachment", URLEncoder.encode(file.getName(), "UTF-8"));
        ByteArrayResource byteArrayResource = new ByteArrayResource(Files.readAllBytes(Paths.get(file.getAbsolutePath())));

        return new ResponseEntity<>(byteArrayResource, headers, HttpStatus.OK);
    }


    // json parse error 가 나서 @valid 어노테이션 추가
    // https://reflectoring.io/accessing-spring-data-rest-with-feign/
    @RequestMapping(value="/upload/ajax",method = RequestMethod.POST)
    public ResponseEntity postBookUploadAttachFile(@Valid @RequestBody String data) throws ParseException {
        JSONParser parser = new JSONParser();
        JSONObject object = (JSONObject) parser.parse(data.toString());

        String fileName = object.get("name").toString();
        String[] body = object.get("data").toString().split(",");
        byte[] bytes = Base64.getDecoder().decode(body[1]);
        String base64Sha = handler.byteToStringSha256(bytes);

        byte[] savedDataBytes = handler.readAllBytes(Paths.get("C:\\Users\\iw.jhun\\Downloads\\","Responsive-Dynamic-Timeline-Plugin-For-jQuery-Timeliner.zip"));
        String savedSha256 = handler.byteToStringSha256(savedDataBytes);

        handler.fileWrite(Paths.get(upload_path,fileName),bytes);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ResponseEntity postBookUploadAttachFileById(
            @RequestParam("attach-file") MultipartFile attachFile,
            @RequestParam("thumbnail-image") MultipartFile thumbnailImage
    ) throws IOException {

        String uploadTargetName = attachFile.getOriginalFilename();
        if(uploadTargetName.equals("")){
            log.debug("attach null");
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        byte[] uploadTargetBytes = attachFile.getBytes();

        //todo save to a file via multipartFile.getInputStream()
        log.debug("File name: " + uploadTargetName);
        String uploadTargetSha256 = handler.byteToStringSha256(uploadTargetBytes);

        byte[] savedDataBytes = handler.readAllBytes(Paths.get("C:\\Users\\iw.jhun\\Downloads\\","curl-7.58.0 (1).zip"));
        String savedSha256 = handler.byteToStringSha256(savedDataBytes);

        log.debug("sha256 hex :" + uploadTargetSha256 + ", saved hex : " + savedSha256);
        log.debug("sha256 size:" + handler.byteToSizeMegaBytes(uploadTargetBytes) + ", saved size:" + handler.byteToSizeMegaBytes
                (savedDataBytes));

        handler.fileWrite(Paths.get("c:/users/iw.jhun/Desktop", uploadTargetSha256), uploadTargetBytes);

        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/search/tags/all", method = RequestMethod.GET)
    public ResponseEntity<Object> getBookSelectAllTags() {
        return new ResponseEntity<>(this.bookRepository.findSubjects(), HttpStatus.OK);
    }

    @RequestMapping(value = "/search/subject/{subject}", method = RequestMethod.GET)
    public ResponseEntity<List<BookEntity>> getBookSearchAllBySubject(@PathVariable String subject) {
        return new ResponseEntity<>(this.bookRepository.findBySubject(subject), HttpStatus.OK);
    }
}
