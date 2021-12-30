
package com.openmusic.api.models.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author Ahmad Irfaan Hibatullah
 * @version $Id: SongRequest.java, v 0.1 2021‐10‐20 08.03 Ahmad Irfaan Hibatullah Exp $$
 */
public class SongRequest {

    @NotBlank
    private String title;

    @NotNull
    private Integer year;

    @NotBlank
    private String performer;

    @NotBlank
    private String genre;

    @NotNull
    private Integer duration;


    /**
     * Getter method for property title.
     *
     * @return property value of title
     */
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

    /**
     * Getter method for property year.
     *
     * @return property value of year
     */
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

    /**
     * Getter method for property performer.
     *
     * @return property value of performer
     */
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

    /**
     * Getter method for property genre.
     *
     * @return property value of genre
     */
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

    /**
     * Getter method for property duration.
     *
     * @return property value of duration
     */
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
}