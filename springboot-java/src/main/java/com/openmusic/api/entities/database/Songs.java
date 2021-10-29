package com.openmusic.api.entities.database;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @author Ahmad Irfaan Hibatullah
 * @version $Id: Songs.java, v 0.1 2021‐10‐19 18.44 Ahmad Irfaan Hibatullah Exp $$
 */

@Entity
@Table(name = "song")
public class Songs extends AbstractTable<String> {

    @Id
    @GenericGenerator(name = "id_song", strategy = "uuid")
    @GeneratedValue(generator = "id_song", strategy = GenerationType.IDENTITY)
    private String id;

    private String title;

    private Integer year;

    private String performer;

    private String genre;

    private Integer duration;


    public String getTitle() {
        return title;
    }

    /**
     * Setter method for property title.
     *
     * @param title value to be assigned to property title
     */

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getYear() {
        return year;
    }

    /**
     * Setter method for property year.
     *
     * @param year value to be assigned to property year
     */

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getPerformer() {
        return performer;
    }

    /**
     * Setter method for property performer.
     *
     * @param performer value to be assigned to property performer
     */

    public void setPerformer(String performer) {
        this.performer = performer;
    }

    public String getGenre() {
        return genre;
    }

    /**
     * Setter method for property genre.
     *
     * @param genre value to be assigned to property genre
     */

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Integer getDuration() {
        return duration;
    }

    /**
     * Setter method for property duration.
     *
     * @param duration value to be assigned to property duration
     */

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }
}