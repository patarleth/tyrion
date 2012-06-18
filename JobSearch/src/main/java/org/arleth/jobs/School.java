package org.arleth.jobs;

/**
 *
 * @author arleth
 */
public class School {

    String school;
    String city, state;
    int start;
    Integer end;
    String degree;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(school);
        if (city != null && state != null) {
            sb.append(city).append(", ").append(state);
        }
        sb.append("\n");
        sb.append(degree).append(" - ").append(start).append(" - ");
        if (end != null) {
            sb.append(end);
        }
        return sb.toString();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public Integer getEnd() {
        return end;
    }

    public void setEnd(Integer end) {
        this.end = end;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
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
}
