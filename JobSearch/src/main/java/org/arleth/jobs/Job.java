package org.arleth.jobs;

/**
 *
 * @author arleth
 */
public class Job {

    String company;
    String title;
    String city, state;
    int start;
    Integer end;
    String description;
    String[] accomplishments;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(title).append(", ").append(company).append("\n");
        sb.append(city).append(", ").append(start).append(" - ");
        if (end != null) {
            sb.append(end);
        }
        sb.append("\n\n");
        sb.append(description).append("\n");
        sb.append("accomplishments\n");
        if (accomplishments != null) {
            for (String a : accomplishments) {
                sb.append("    * ").append(a).append("\n");
            }
        }
        return sb.toString();
    }

    public String[] getAccomplishments() {
        return accomplishments;
    }

    public void setAccomplishments(String[] accomplishments) {
        this.accomplishments = accomplishments;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getEnd() {
        return end;
    }

    public void setEnd(Integer end) {
        this.end = end;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
