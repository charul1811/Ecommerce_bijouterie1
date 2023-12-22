package com.example.ecommerce_bijouterie1.entities;

import jakarta.persistence.*;
import lombok.*;



@Entity
@Table(name = "bijoux")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class Bijoux {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bijoux_id_seq")
    @SequenceGenerator(name = "bijou_id_seq", sequenceName = "bijoux_id_seq", initialValue = 109, allocationSize = 1)
    private Long id;

    @Column(name = "bijou_title", nullable = false)
    private String bijouTitle;

    @Column(name = "bijou_metal", nullable = false)
    private String bijouMetal;

    @Column(name = "year", nullable = false)
    private Integer year;

    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "bijou_type", nullable = false)
    private String bijouType;

  /*  @Column(name = "fragrance_top_notes", nullable = false)
    private String fragranceTopNotes;

    @Column(name = "fragrance_middle_notes", nullable = false)
    private String fragranceMiddleNotes;

    @Column(name = "fragrance_base_notes", nullable = false)
    private String fragranceBaseNotes;*/

    @Column(name = "description")
    private String description;

    @Column(name = "filename")
    private String filename;

    @Column(name = "price", nullable = false)
    private Integer price;

    @Column(name = "volume", nullable = false)
    private String volume;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBijouTitle() {
        return bijouTitle;
    }

    public void setBijouTitle(String bijouTitle) {
        this.bijouTitle = bijouTitle;
    }

    public String getBijouMetal() {
        return bijouMetal;
    }

    public void setBijouMetal(String bijouMetal) {
        this.bijouMetal = bijouMetal;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getBijouType() {
        return bijouType;
    }

    public void setBijouType(String bijouType) {
        this.bijouType = bijouType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Column(name = "type", nullable = false)
    private String type;
}
