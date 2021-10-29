
package com.openmusic.api.entities.database;


import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

/**
 * @author Ahmad Irfaan Hibatullah
 * @version $Id: AbstractTable.java, v 0.1 2021‐10‐19 18.45 Ahmad Irfaan Hibatullah Exp $$
 */

@MappedSuperclass
public abstract class AbstractTable<ID> {


    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

    @Column(name = "deleted_date")
    private LocalDateTime deletedDate;

    @PrePersist
    public void prePersist() {
        this.createdDate = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedDate = LocalDateTime.now();
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    /**
     * Setter method for property createdDate.
     *
     * @param createdDate value to be assigned to property createdDate
     */

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    /**
     * Getter method for property deletedDate.
     *
     * @return property value of deletedDate
     */
    public LocalDateTime getDeletedDate() {
        return deletedDate;
    }

    /**
     * Setter method for property deletedDate.
     *
     * @param deletedDate value to be assigned to property deletedDate
     */

    public void setDeletedDate(LocalDateTime deletedDate) {
        this.deletedDate = deletedDate;
    }

    /**
     * Setter method for property updatedDate.
     *
     * @param updatedDate value to be assigned to property updatedDate
     */

    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }

    public abstract ID getId();

    public abstract void setId(ID id);

}