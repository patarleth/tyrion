package org.arleth.jobs;

/**
 *
 * @author arleth
 */
public class MyResume {

    public static Resume now() {
        Resume r = new Resume();
        r.setFirstName("Patrick");
        r.setLastName("Arleth");
        
        r.setHome(new Address());
        r.getHome().setLine1("21 Park Place Cir.");
        r.getHome().setCity("West Hartford");
        r.getHome().setState("CT");
        r.getHome().setZipcode(6110);

        r.setHomePhone(new TelephoneNumber(8608332846l));
        r.setEmail(new Email("arleth@about.me"));
        r.setWeb("http://about.me/arleth");

        r.setSummary("Programmer and architect with 15+ years of industry leading experience delivering innovative solutions in an ever-changing technological landscape. Solid core Java and J2EE fundamentals, with an eye on emerging trends and technologies. I’ve maintained and extended compiled templating languages, utilized natural language text clustering strategies, developed user personalization solutions, developed content management solutions, syndication services for data and user facing content all with the goal of scaling out access to millions of users.");

        r.setTechnicalSkills(new Skillz());
        r.getTechnicalSkills().setProgramming(new SkillType("Programming"));
        r.getTechnicalSkills().getProgramming().setSkills(new Skill[]{
                    new Skill("Java", null, null),
                    new Skill("XML", null, null),
                    new Skill("Javascript", null, null),
                    new Skill("MongoDB", null, null),
                    new Skill("SQL", null, null),
                    new Skill("MySQL", null, null),
                    new Skill("OpenNLP", null, null),
                    new Skill("c/c++", null, null)
                });
        r.getTechnicalSkills().setDevelopment(new SkillType("Development"));
        r.getTechnicalSkills().getDevelopment().setSkills(new Skill[]{
                    new Skill("OO Design", null, null),
                    new Skill("Agile development", null, null),
                    new Skill("JIRA", null, null)
                });
        r.getTechnicalSkills().setEnvironments(new SkillType("Environments"));
        r.getTechnicalSkills().getEnvironments().setSkills(new Skill[]{
                    new Skill("Windows", null, null),
                    new Skill("Linux", "Ubuntu,REL", null),
                    new Skill("F5", null, null),
                    new Skill("Netbeans", null, null),
                    new Skill("Eclipse", null, null),
                    new Skill("emacs", null, null)
                });

        r.setExperience(new Experience());
        Job job1 = new Job();
        job1.setTitle("CTO");
        job1.setCompany("VarioLabs");
        job1.setStart(2011);
        job1.setEnd(2012);
        job1.setCity("West Hartford");
        job1.setState("CT");
        job1.setDescription("Co-Founder of VarioLabs. Developed a news aggregation service called The Storyline. Built with Java/MongoDB/Solr/Ruby and natural language parsing techniques. The services enabled automatic clustering of content sourced from raw HTML, RSS and Twitter feeds. Libraries used included PhantomJS, Twitter4j, ROME for rss, Rhinojs, mahout-core, Stanford NLP and OpenNLP.");
        job1.setAccomplishments(new String[]{
                    "Filtered and parsed content removing markup for text analysis and search",
                    "Extracted persons, organizations, and locations for the purpose of clustering documents.",
                    "Provided both a river of news and a timely, clustered solution based on the extracted subject matter."
                });
        Job job2 = new Job();
        job2.setTitle("Staff Engineer");
        job2.setCompany("ESPN");
        job2.setStart(2001);
        job2.setEnd(2011);
        job2.setCity("Bristol");
        job2.setState("CT");
        job2.setDescription("Senior member of the platform architecture team. Developed, maintained and upgraded core platform infrastructure and application suites including - search, personalization, content management, in-process and out of process caching technologies, and legacy platform integration. Provided leadership, guidance and training to fellow engineers.");
        job2.setAccomplishments(new String[]{
                    "Personalization: favorite team/player/columnists saved to a member profile using distributed caching techniques. The distributed \"grid” was stress tested to more than 14,000 request a second with 1 million unique member keys with less than 1% failed requests. 30,000 req/sec with less than 4% failed requests.",
                    "Data services: high throughput/availability sport data services using SOA and open frameworks. The goal of this ongoing project is the elimination of custom, legacy dependencies and technologies, standardizing data access across platforms using open frameworks.",
                    "Fantasy games: team lead involved in the redesign of the fantasy core engine. The project’s overriding goal was to scale user capacity from hundreds of thousands of concurrent users to millions. We accomplished this goal using a combination of custom serialization and a clustered \"pod\" approach which simplified the transaction model and vastly lowered latency.",
                    "Video asset manager: disparate video sources and tools were consolidated into a single workflow tool called the video asset manager or VAM. VAM shortened time to site for the most common video asset up to 20%. The video asset manager further removed the need for access to numerous backchannel networks which required multiple logins.",
                    "Search: Senior engineer responsible for content and user search initiatives. Search evolved over the years from an overly simplistic Microsoft Full Text approach, then transitioned to Solr1.3 and finally migrating to Endeca powered search."
                });
        Job job3 = new Job();
        job3.setTitle("Software Engineer");
        job3.setCompany("Starwave/Disney Internet Group");
        job3.setStart(1997);
        job3.setEnd(2001);
        job3.setCity("Seattle");
        job3.setState("WA");
        job3.setDescription("Team lead responsible for registration and billing services for go.com sites.");
        job3.setAccomplishments(new String[]{
                    "Integrated registration and billing services provided to all go.com sites",
                    "Credit card transaction services for disney affiliates including - DisneyBlast, ESPN Insider",
                    "Web-services exposed to partners within a secured network",
                    "Maintenance of a custom servlet containers and compiled template languages"
                });
        r.getExperience().setJobs(new Job[]{
                    job1, job2, job3
                });

        r.setEducation(new Education());
        School school1 = new School();
        school1.setSchool("University of Oregon");
        school1.setCity("Eugene");
        school1.setState("OR");
        school1.setDegree("Bachelor of Science in Computer Information Science");
        school1.setStart(1990);
        school1.setEnd(1996);
        r.getEducation().setSchools(new School[]{
                    school1
                });
        return r;
    }
}
