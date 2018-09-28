package com.autentia.tnt.api.rest.model.DTO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import java.util.Objects;

public class ActivityDTO {

    private Integer id;

    private Date startDate;

    private Integer duration;

    private String description;

    private Integer roleId;

    private Boolean billable;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Boolean getBillable() {
        return billable;
    }

    public void setBillable(Boolean billable) {
        this.billable = billable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActivityDTO that = (ActivityDTO) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(startDate, that.startDate) &&
                Objects.equals(duration, that.duration) &&
                Objects.equals(description, that.description) &&
                Objects.equals(roleId, that.roleId) &&
                Objects.equals(billable, that.billable);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, startDate, duration, description, roleId, billable);
    }
}
