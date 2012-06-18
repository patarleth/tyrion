package org.arleth.jobs;

/**
 *
 * @author arleth
 */
public class Skillz {

    SkillType programming;
    SkillType development;
    SkillType environments;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (programming != null) {
            sb.append(programming).append("\n");
        }
        if (development != null) {
            sb.append(development).append("\n");
        }
        if (environments != null) {
            sb.append(environments).append("\n");
        }
        return sb.toString();
    }

    public SkillType getDevelopment() {
        return development;
    }

    public void setDevelopment(SkillType development) {
        this.development = development;
    }

    public SkillType getEnvironments() {
        return environments;
    }

    public void setEnvironments(SkillType environments) {
        this.environments = environments;
    }

    public SkillType getProgramming() {
        return programming;
    }

    public void setProgramming(SkillType programming) {
        this.programming = programming;
    }
}
