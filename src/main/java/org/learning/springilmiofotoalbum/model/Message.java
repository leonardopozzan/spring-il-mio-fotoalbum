package org.learning.springilmiofotoalbum.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Email
    @NotBlank(message = "la mail non può essere vuoto o contenere solo spazi")
    @Column(nullable = false)
    private String email;
    @NotBlank(message = "il testo del messaggio non può essere vuoto o contenere solo spazi")
    @Lob
    @Column(nullable = false)
    private String text;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
