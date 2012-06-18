package org.arleth.jobs;

/**
 *
 * @author arleth
 */
public class TelephoneNumber {

    int areacode;
    int number;

    public TelephoneNumber() {
    }

    public TelephoneNumber(long n) {
        if (n < 10000000) {
            //no area code
            number = (int) n;
        } else {
            String s = Long.toString(n);
            number = Integer.valueOf(s.substring(s.length() - 7));
            areacode = Integer.valueOf(s.substring(0, s.length() - 7));
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (areacode > 0) {
            sb.append("(").append(areacode).append(") ");
        }
        sb.append(number);
        return sb.toString();
    }

    public TelephoneNumber(String s) {
        this((s == null) ? 0 : Long.valueOf(s.replaceAll("[^0-9]", "")));
    }

    public int getAreacode() {
        return areacode;
    }

    public void setAreacode(int areacode) {
        this.areacode = areacode;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
