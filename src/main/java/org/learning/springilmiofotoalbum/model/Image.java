package org.learning.springilmiofotoalbum.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name= "images")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "il titolo non può essere vuoto o contenere solo spazi")
    @Size(min = 3,  message = "il titolo deve avere almeno 3 caratteri")
    @Size(max = 50, message = "il titolo non può superare i 50 caratteri")
    @Column(nullable = false, unique = true)
    private String title;
    @Lob
    private String description;
    @NotBlank(message = "url non può essere vuoto o contenere solo spazi")
    @Column(nullable = false)
    private String url;
    @NotNull(message = "devi selezionare la visibilità")
    @Column(nullable = false)
    private Boolean visible;

    @NotEmpty(message = "devi selezionare almenon una categoria")
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "category_image",
            joinColumns = @JoinColumn(name = "image_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categories;

    public Image(Image image) {
        this.title = image.title;
        this.description = image.description;
        this.url = image.url;
        this.visible = image.visible;
    }

    public Image(){
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
