package com.openmusic.api.entities.database;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @author Ahmad Irfaan Hibatullah
 * @version $Id: Collaborations.java, v 0.1 2021‐10‐19 19.10 Ahmad Irfaan Hibatullah Exp $$
 */

@Entity
@Table(name = "collaborations")
public class Collaborations extends AbstractTable<String> {

    @Id
    @GenericGenerator(name = "id_collaboration", strategy = "uuid")
    @GeneratedValue(generator = "id_collaboration", strategy = GenerationType.IDENTITY)
    private String id;

    @OneToOne()
    @JoinColumn(name = "playlist_id")
    private Playlists playlists;

    @OneToOne()
    @JoinColumn(name = "user_id")
    private Users users;

    /**
     * Getter method for property id.
     *
     * @return property value of id
     */
    @Override
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
     * Getter method for property playlists.
     *
     * @return property value of playlists
     */
    public Playlists getPlaylists() {
        return playlists;
    }

    /**
     * Setter method for property playlists.
     *
     * @param playlists value to be assigned to property playlists
     */

    public void setPlaylists(Playlists playlists) {
        this.playlists = playlists;
    }

    /**
     * Getter method for property users.
     *
     * @return property value of users
     */
    public Users getUsers() {
        return users;
    }

    /**
     * Setter method for property users.
     *
     * @param users value to be assigned to property users
     */

    public void setUsers(Users users) {
        this.users = users;
    }
}