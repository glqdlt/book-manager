package com.glqdlt.bookmanager.api;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class UploadFileModel {

    private String name;
    private Integer size;
    private String type;
    private String data;

}
