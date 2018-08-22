package autentia.apiRestTnt.model;

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
public class RequestHoliday {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @JsonIgnore
    @Column
    private Long id;

    @Column
    private Date beginDate;

    @Column
    private Date finalDate;

    @Column
    private String state;

    @Column
    private Integer userId;

    @Column
    @JsonIgnore
    private String observations;

    @Column
    @JsonIgnore
    private String departmentId;

    @Column
    @JsonIgnore
    private String userComment;

    @Column
    @JsonIgnore
    private Date chargeYear;

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

    public RequestHoliday(){

    }

    public RequestHoliday(Date beginDate, Date finalDate, String state, Integer userId, String observations, String departamentId, String userComment, Date chargeYear) {
        this.beginDate = beginDate;
        this.finalDate = finalDate;
        this.state = state;
        this.userId = userId;
        this.observations = observations;
        this.departmentId = departamentId;
        this.userComment = userComment;
        this.chargeYear = chargeYear;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getFinalDate() {
        return finalDate;
    }

    public void setFinalDate(Date finalDate) {
        this.finalDate = finalDate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getUserComment() {
        return userComment;
    }

    public void setUserComment(String userComment) {
        this.userComment = userComment;
    }

    public Date getChargeYear() {
        return chargeYear;
    }

    public void setChargeYear(Date chargeYear) {
        this.chargeYear = chargeYear;
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
        RequestHoliday that = (RequestHoliday) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(beginDate, that.beginDate) &&
                Objects.equals(finalDate, that.finalDate) &&
                Objects.equals(state, that.state) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(observations, that.observations) &&
                Objects.equals(departmentId, that.departmentId) &&
                Objects.equals(userComment, that.userComment) &&
                Objects.equals(chargeYear, that.chargeYear) &&
                Objects.equals(insertDate, that.insertDate) &&
                Objects.equals(updateDate, that.updateDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, beginDate, finalDate, state, userId, observations, departmentId, userComment, chargeYear, insertDate, updateDate);
    }
}
