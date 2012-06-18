package org.arleth.jobs;

/**
 *
 * @author arleth
 */
public class Email {

    String address, name, domain;

    public Email() {
    }

    public Email(String e) {
        address = e;
        if (e != null) {
            int index = e.indexOf("@");
            if (index > 0) {
                name = e.substring(0, index);
                domain = e.substring(index + 1);
            }
        }
    }

    @Override
    public String toString() {
        return address + " name " + name + " domain " + domain;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}