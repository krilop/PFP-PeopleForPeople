package org.example.data;

import lombok.Data;
@Data
public class Authorization {

    Long id;
    String login;
    String hashOfPass;
    String email;

    public Authorization(Long id, String login, String hashOfPass, String email) {
        this.id = id;
        this.login = login;
        this.hashOfPass = hashOfPass;
        this.email = email;
    }
}
