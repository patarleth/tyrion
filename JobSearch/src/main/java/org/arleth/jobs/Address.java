package org.arleth.jobs;

/**
 *
 * @author arleth
 */
public class Address {

    String line1, line2;
    String city;
    String state;
    int zipcode;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (line1 != null) {
            sb.append(line1).append(", ");
        }
        if (line2 != null) {
            sb.append(line2).append(", ");
        }
        if (city != null) {
            sb.append(city).append(" ");

        }
        if (state != null) {
            sb.append(state).append(", ");
        }
        if (zipcode > 0) {
            if( zipcode < 10000 ) {
                sb.append("0");
                if( zipcode < 1000) {
                    sb.append("0");
                    if( zipcode < 100) {
                        sb.append("0");
                        if( zipcode < 10) {
                            sb.append("0");
                        }
                    }
                }
            }
            sb.append(zipcode);
        }
        return sb.toString();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLine1() {
        return line1;
    }

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public String getLine2() {
        return line2;
    }

    public void setLine2(String line2) {
        this.line2 = line2;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getZipcode() {
        return zipcode;
    }

    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
    }
}
