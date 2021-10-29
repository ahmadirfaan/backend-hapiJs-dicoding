package com.openmusic.api.entities.database;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @author Ahmad Irfaan Hibatullah
 * @version $Id: Playlists.java, v 0.1 2021‐10‐19 19.08 Ahmad Irfaan Hibatullah Exp $$
 */
@Entity
@Table(name = "playlist")
public class Playlists extends AbstractTable<String> {

    @Id
    @GenericGenerator(name = "id_playlist", strategy = "uuid")
    @GeneratedValue(generator = "id_playlist", strategy = GenerationType.IDENTITY)
    private String id;

    private String name;

    @OneToOne()
    @JoinColumn(name = "owner")
    private Users owner;

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

    @Override
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Getter method for property name.
     *
     * @return property value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter method for property name.
     *
     * @param name value to be assigned to property name
     */

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter method for property owner.
     *
     * @return property value of owner
     */
    public Users getOwner() {
        return owner;
    }

    /**
     * Setter method for property owner.
     *
     * @param owner value to be assigned to property owner
     */

    public void setOwner(Users owner) {
        this.owner = owner;
    }
}