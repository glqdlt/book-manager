package com.glqdlt.bookmanager.service;

import com.google.common.hash.Hashing;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Slf4j
@Service
public class FileHandlingUtil {

    // 아래는 google의 guava 라이브러리를 쓴다
    public String byteToStringSha256(byte[] bytes) {
        return Hashing.sha256()
                .hashBytes(bytes)
                .toString();
    }

    public byte[] readAllBytes(Path path) {
        byte[] bytes = null;
        try {
            bytes = Files.readAllBytes(path);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return bytes;

    }

    //        https://docs.oracle.com/javase/7/docs/api/java/nio/file/Files.html#write(java.nio.file.Path,%20byte[],%20java.nio.file.OpenOption...)
    // 공식 문서를 보니 io exception 이 나거나, file 을 다 썻다고 판단하면 그냥 무조건 close 한다고 한다.
    //       http://eherrera.net/ocpj8-notes/09-java-file-io-(NIO.2)
    //        java nio 에 대해서는.. 아래를 참고
    //        http://palpit.tistory.com/640
    //        http://www.baeldung.com/guava-write-to-file-read-from-file

    public boolean fileWrite(Path path, byte[] bytes) {
        boolean result = false;
        try {
            Files.write(path, bytes);
            result = true;
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return result;
    }

    public double byteToSizeMegaBytes(byte[] bytes) {
        return ((bytes.length / 1024) / 1024);
    }
}
