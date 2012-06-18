package org.arleth.jobs;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import org.bson.types.ObjectId;

/**
 *
 * @author arleth
 */
@Entity("resumes")
public class Resume {

    @Id
    ObjectId id; // auto-generated, if not set (see ObjectId)
    ObjectId memberId;
    String firstName, lastName;
    Long salary = null;
    Address home;
    TelephoneNumber homePhone;
    Email email;
    String web;
    String summary;
    Skillz technicalSkills;
    Experience experience;
    Education education;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        sb.append(getFirstName()).append(" ").append(getLastName()).append("\n");
        sb.append("address ").append(getHome()).append("\n");
        sb.append("telephone ").append(getHomePhone()).append("\n");
        sb.append("email ").append(getEmail()).append("\n");
        sb.append("web ").append(getWeb()).append("\n");
        sb.append("summary ").append(getSummary()).append("\n\n");
        sb.append("tech skills\n").append(getTechnicalSkills()).append("\n");
        sb.append("Experience\n").append(getExperience()).append("\n");
        sb.append("Education\n").append(getEducation());

        return sb.toString();
    }

    public Education getEducation() {
        return education;
    }

    public void setEducation(Education education) {
        this.education = education;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public Experience getExperience() {
        return experience;
    }

    public void setExperience(Experience experience) {
        this.experience = experience;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Address getHome() {
        return home;
    }

    public void setHome(Address home) {
        this.home = home;
    }

    public TelephoneNumber getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(TelephoneNumber homePhone) {
        this.homePhone = homePhone;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Skillz getTechnicalSkills() {
        return technicalSkills;
    }

    public void setTechnicalSkills(Skillz technicalSkills) {
        this.technicalSkills = technicalSkills;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public ObjectId getMemberId() {
        return memberId;
    }

    public void setMemberId(ObjectId memberId) {
        this.memberId = memberId;
    }
}
