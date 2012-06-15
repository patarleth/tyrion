package org.arleth;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.Morphia;
import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import com.mongodb.Mongo;
import com.mongodb.MongoURI;
import org.bson.types.ObjectId;

public class Welcome {

    public static class Address {

        String line1, line2;
        String city;
        String state;
        int zipcode;
    }

    public static class TelephoneNumber {

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

        public TelephoneNumber(String s) {
            this((s == null) ? 0 : Long.valueOf(s.replaceAll("[^0-9]", "")));
        }
        int areacode;
        int number;
    }

    public static class Email {

        public Email() {
        }

        public Email(String e) {
            address = e;
            if (e != null) {
                int index = e.indexOf("@");
                if (index > 0) {
                    name = e.substring(0, index);
                    domain = e.substring(index + 1);
                }
            }
        }
        String address, name, domain;
    }

    public static class Skillz {

        SkillType programming;
        SkillType development;
        SkillType environments;
    }

    public static class SkillType {

        public SkillType() {
        }

        public SkillType(String n) {
            name = n;
        }
        String name;
        Skill[] skills;
    }

    public static class Skill {

        public Skill() {
        }

        public Skill(String n, String d, String u) {
            name = n;
            description = d;
            url = u;
        }
        String name;
        String description;
        String url;
    }

    public static class Experience {

        Job[] jobs;
    }

    public static class Job {

        String company;
        String title;
        String city, state;
        int start;
        Integer end;
        String description;
        String[] accomplishments;
    }

    public static class Education {

        School[] schools;
    }

    public static class School {

        String school;
        String city, state;
        int start;
        Integer end;
        String degree;
    }

    @Entity("resumes")
    public static class Resume {

        @Id
        ObjectId id; // auto-generated, if not set (see ObjectId)
        String firstName, lastName;
        Long salary = null;
        Address home;
        TelephoneNumber homePhone;
        Email email;
        String web;
        String summary;
        Skillz technicalSkills;
        Experience experience;
        Education education;
    }

    public static void main(String[] args) throws Exception {
        MongoURI uri = new MongoURI("mongodb://arleth:GoDucks01@staff.mongohq.com:10070/arlethJobSearch");
        Mongo mongo = new Mongo(uri);
        Morphia morphia = new Morphia().map(Resume.class);
        Datastore datastore = morphia.createDatastore(mongo, "arlethJobSearch", "arleth", "GoDucks01".toCharArray());
        Resume r = datastore.find(Resume.class).field("lastName").equal("Arleth").get();
        /*
        if (r == null) {
        r = GenerateInitialResume();
        datastore.save(r);
        }
         */
        if (r != null) {
            System.out.println("hello " + r.firstName + " " + r.lastName);

        } else {
            System.out.println("no resume found");
        }
    }

    public static Resume GenerateInitialResume() {
        Resume r = new Resume();
        r.firstName = "Patrick";
        r.lastName = "Arleth";
        r.home = new Address();
        r.home.line1 = "21 Park Place Cir.";
        r.home.city = "West Hartford";
        r.home.state = "CT";
        r.home.zipcode = 06110;

        r.homePhone = new TelephoneNumber(8608332846l);
        r.email = new Email("arleth@about.me");
        r.web = "http://about.me/arleth";

        r.summary = "Programmer and architect with 15+ years of industry leading experience delivering innovative solutions in an ever-changing technological landscape. Solid core Java and J2EE fundamentals, with an eye on emerging trends and technologies. I’ve maintained and extended compiled templating languages, utilized natural language text clustering strategies, developed user personalization solutions, developed content management solutions, syndication services for data and user facing content all with the goal of scaling out access to millions of users.";

        r.technicalSkills = new Skillz();
        r.technicalSkills.development = new SkillType("Developemnt");
        r.technicalSkills.development.skills = new Skill[]{
            new Skill("Java", null, null),
            new Skill("XML", null, null),
            new Skill("Javascript", null, null),
            new Skill("MongoDB", null, null),
            new Skill("SQL", null, null),
            new Skill("MySQL", null, null),
            new Skill("OpenNLP", null, null),
            new Skill("c/c++", null, null)
        };
        r.technicalSkills.development = new SkillType("Development");
        r.technicalSkills.development.skills = new Skill[]{
            new Skill("OO Design", null, null),
            new Skill("Agile development", null, null),
            new Skill("JIRA", null, null)
        };
        r.technicalSkills.environments = new SkillType("Environments");
        r.technicalSkills.environments.skills = new Skill[]{
            new Skill("Windows", null, null),
            new Skill("Linux", "Ubuntu,REL", null),
            new Skill("F5", null, null),
            new Skill("Netbeans", null, null),
            new Skill("Eclpise", null, null),
            new Skill("emacs", null, null)
        };

        r.experience = new Experience();
        Job job1 = new Job();
        job1.title = "CTO";
        job1.company = "VarioLabs";
        job1.start = 2011;
        job1.end = 2012;
        job1.city = "West Hartford";
        job1.state = "CT";
        job1.description = "Co-Founder of VarioLabs. Developed a news aggregation service called The Storyline. Built with Java/MongoDB/Solr/Ruby and natural language parsing techniques. The services enabled automatic clustering of content sourced from raw HTML, RSS and Twitter feeds. Libraries used included PhantomJS, Twitter4j, ROME for rss, Rhinojs, mahout-core, Stanford NLP and OpenNLP.";
        job1.accomplishments = new String[]{
            "Filtered and parsed content removing markup for text analysis and search",
            "Extracted persons, organizations, and locations for the purpose of clustering documents.",
            "Provided both a river of news and a timely, clustered solution based on the extracted subject matter."
        };
        Job job2 = new Job();
        job2.title = "Staff Engineer";
        job2.company = "ESPN";
        job2.start = 2001;
        job2.end = 2011;
        job2.city = "Bristol";
        job2.state = "CT";
        job2.description = "Senior member of the platform architecture team. Developed, maintained and upgraded core platform infrastructure and application suites including - search, personalization, content management, in-process and out of process caching technologies, and legacy platform integration. Provided leadership, guidance and training to fellow engineers.";
        job2.accomplishments = new String[]{
            "Personalization: favorite team/player/columnists saved to a member profile using distributed caching techniques. The distributed \"grid” was stress tested to more than 14,000 request a second with 1 million unique member keys with less than 1% failed requests. 30,000 req/sec with less than 4% failed requests.",
            "Data services: high throughput/availability sport data services using SOA and open frameworks. The goal of this ongoing project is the elimination of custom, legacy dependencies and technologies, standardizing data access across platforms using open frameworks.",
            "Fantasy games: team lead involved in the redesign of the fantasy core engine. The project’s overriding goal was to scale user capacity from hundreds of thousands of concurrent users to millions. We accomplished this goal using a combination of custom serialization and a clustered \"pod\" approach which simplified the transaction model and vastly lowered latency.",
            "Video asset manager: disparate video sources and tools were consolidated into a single workflow tool called the video asset manager or VAM. VAM shortened time to site for the most common video asset up to 20%. The video asset manager further removed the need for access to numerous backchannel networks which required multiple logins.",
            "Search: Senior engineer responsible for content and user search initiatives. Search evolved over the years from an overly simplistic Microsoft Full Text approach, then transitioned to Solr1.3 and finally migrating to Endeca powered search."
        };
        Job job3 = new Job();
        job3.title = "Software Engineer";
        job3.company = "Starwave/Disney Internet Group";
        job3.start = 1997;
        job3.end = 2001;
        job3.city = "Seattle";
        job3.state = "WA";
        job3.description = "Team lead responsible for registration and billing services for go.com sites.";
        job3.accomplishments = new String[]{
            "Integrated registration and billing services provided to all go.com sites",
            "Credit card transaction services for disney affiliates including - DisneyBlast, ESPN Insider",
            "Web-services exposed to partners within a secured network",
            "Maintenance of a custom servlet containers and compiled template languages"
        };
        r.experience.jobs = new Job[]{
            job1, job2, job3
        };

        r.education = new Education();
        School school1 = new School();
        school1.school = "University of Oregon";
        school1.city = "Eugene";
        school1.state = "OR";
        school1.degree = "Bachelor of Science in Computer Information Science";
        school1.start = 1990;
        school1.end = 1996;
        r.education.schools = new School[]{
            school1
        };

        return r;
    }
}