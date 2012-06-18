package org.arleth.jobs;

/**
 *
 * @author arleth
 */
public class SkillType {

    String name;
    Skill[] skills;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name).append(" ");
        if (skills != null) {
            for (int i = 0; i < skills.length; i++) {
                if (i > 0) {
                    sb.append(", ");
                }
                sb.append(skills[i]);
            }
        }
        return sb.toString();
    }

    public SkillType() {
    }

    public SkillType(String n) {
        name = n;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Skill[] getSkills() {
        return skills;
    }

    public void setSkills(Skill[] skills) {
        this.skills = skills;
    }
}
