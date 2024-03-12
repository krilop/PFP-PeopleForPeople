package org.example.crud;

import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.List;

@Data
public class CRUDUser_data {
    private Long id;
    private String name;
    private String last_name;
    private String description;
    private short age;
    private boolean gender;
    private SimpleDateFormat date_of_birth= new SimpleDateFormat("dd-MM-yyyy");
    private Media media;
    @Data
    static class Media
    {
        List<String> url;
    }

    public CRUDUser_data(Long id, String name, String last_name, String description, short age, boolean gender, SimpleDateFormat date_of_birth, Media media) {
        this.id = id;
        this.name = name;
        this.last_name = last_name;
        this.description = description;
        this.age = age;
        this.gender = gender;
        this.date_of_birth = date_of_birth;
        this.media = media;
    }

}
