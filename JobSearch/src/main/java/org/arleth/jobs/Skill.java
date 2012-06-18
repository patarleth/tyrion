package org.arleth.jobs;

/**
 *
 * @author arleth
 */
public class Skill {

    String name;
    String description;
    String url;

    public Skill() {
    }

    public Skill(String n, String d, String u) {
        name = n;
        description = d;
        url = u;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (url != null) {
            sb.append("<a href=\"").append(url).append("\">");
        }

        sb.append(name);
        if (description != null) {
            sb.append(" (").append(description).append(")");
        }

        if (url != null) {
            sb.append("</a>");
        }
        return sb.toString();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
