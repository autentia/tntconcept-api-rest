package com.autentia.tnt.api.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.Objects;

@Entity
public class Holiday {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;

    @Column
    private String description;

    @Column
    private Date date;

    @Column
    @JsonIgnore
    private Integer ownerId;

    @Column
    @JsonIgnore
    private Integer departmentId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "insertDate")
    @CreationTimestamp
    @JsonIgnore
    private Date insertDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updateDate")
    @UpdateTimestamp
    @JsonIgnore
    private Date updateDate;

    public Holiday() {

    }

    public Holiday(String description, Date date, Integer ownerId, Integer departmentId) {
        this.description = description;
        this.date = date;
        this.ownerId = ownerId;
        this.departmentId = departmentId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public Date getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(Date insertDate) {
        this.insertDate = insertDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Holiday holiday = (Holiday) o;
        return Objects.equals(id, holiday.id) &&
                Objects.equals(description, holiday.description) &&
                Objects.equals(date, holiday.date) &&
                Objects.equals(ownerId, holiday.ownerId) &&
                Objects.equals(departmentId, holiday.departmentId) &&
                Objects.equals(insertDate, holiday.insertDate) &&
                Objects.equals(updateDate, holiday.updateDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, description, date, ownerId, departmentId, insertDate, updateDate);
    }
}
