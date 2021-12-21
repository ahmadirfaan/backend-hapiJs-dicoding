package com.openmusic.api.models.response;

/**
 * @author Ahmad Irfaan Hibatullah
 * @version $Id: GetAllSongResponse.java, v 0.1 2021‐11‐14 15.40 Ahmad Irfaan Hibatullah Exp $$
 */
public class GetAllSongResponse {

    private String id;

    private String title;

    private String performer;

    public GetAllSongResponse(String id, String title, String performer) {
        this.id = id;
        this.title = title;
        this.performer = performer;
    }

    /**
     * Getter method for property id.
     *
     * @return property value of id
     */
    public String getId() {
        return id;
    }

    /**
     * Setter method for property id.
     *
     * @param id value to be assigned to property id
     */

    public void setId(String id) {
        this.id = id;
    }

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
}