package org.example.data;

import lombok.Data;
@Data
public class Authorization {

    Long id;
    String login;
    String hash_of_pass;
    String email;

    public Authorization(Long id, String login, String hash_of_pass, String email) {
        this.id = id;
        this.login = login;
        this.hash_of_pass = hash_of_pass;
        this.email = email;
    }
}
