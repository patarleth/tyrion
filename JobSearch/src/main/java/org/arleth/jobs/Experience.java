package org.arleth.jobs;

/**
 *
 * @author arleth
 */
    public class Experience {

    Job[] jobs;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (jobs != null) {
            for (Job j : jobs) {
                sb.append(j).append("\n");
            }
        }
        return sb.toString();
    }

    public Job[] getJobs() {
        return jobs;
    }

    public void setJobs(Job[] jobs) {
        this.jobs = jobs;
    }
}
