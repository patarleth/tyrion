package org.arleth.reg;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import java.util.Date;
import org.arleth.jobs.Email;
import org.arleth.jobs.TelephoneNumber;
import org.bson.types.ObjectId;

/**
 *
 * @author arleth
 */
@Entity("members")
public class Member {

    @Id
    ObjectId id; // auto-generated, if not set (see ObjectId)
    String username, passwordHash;
    Email email;
    TelephoneNumber homePhone;
    Date dateCreated, dateModified;

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateModified() {
        return dateModified;
    }

    public void setDateModified(Date dateModified) {
        this.dateModified = dateModified;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public TelephoneNumber getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(TelephoneNumber homePhone) {
        this.homePhone = homePhone;
    }
}
