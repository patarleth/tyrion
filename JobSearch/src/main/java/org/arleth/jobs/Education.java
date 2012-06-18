package org.arleth.jobs;

/**
 *
 * @author arleth
 */
public class Education {

    School[] schools;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (schools != null) {
            for (School s : schools) {
                sb.append(s).append("\n");
            }
        }
        return sb.toString();
    }

    public School[] getSchools() {
        return schools;
    }

    public void setSchools(School[] schools) {
        this.schools = schools;
    }
}
