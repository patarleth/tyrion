package org.arleth;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.Morphia;
import com.google.code.morphia.query.Query;
import com.mongodb.Mongo;
import com.mongodb.MongoURI;

import org.arleth.jobs.MyResume;
import org.arleth.jobs.Resume;
import org.arleth.reg.Member;
import org.arleth.reg.MemberSupport;

public class Welcome {

    public static void main(String[] args) throws Exception {
        MongoURI uri = new MongoURI("mongodb://arleth:GoDucks01@staff.mongohq.com:10070/arlethJobSearch");
        Mongo mongo = new Mongo(uri);
        Morphia morphia = new Morphia().map(org.arleth.jobs.Resume.class).map(org.arleth.reg.Member.class);
        Datastore datastore = morphia.createDatastore(mongo, "arlethJobSearch", "arleth", "GoDucks01".toCharArray());
        /*
        Query q = datastore.createQuery(Member.class);
        datastore.delete(q);
         */
        Member m = MemberSupport.login(datastore, "arleth", "patrickj");

        if (m == null) {
            m = MemberSupport.create(datastore, "arleth", "patrickj", "arleth@about.me", "860 833 2846");
            //try login again
            m = MemberSupport.login(datastore, "arleth", "patrickj");
            println("member " + m.getUsername());
        } else {
            println("yay! login worked ", m.getUsername(), " pwdHash ", m.getPasswordHash());
            String sessionVal = MemberSupport.sessionHash(m);
            println("sessionHash ", sessionVal);
            println("valid hash ", MemberSupport.validateSession(datastore, sessionVal));

            /*
            m = MemberSupport.setPassword(datastore, "arleth", "patrickj", "patrick");
            println("updated password ", m.getPasswordHash());
            m = MemberSupport.login(datastore, "arleth", "patrick");
            println("login with new password worked ", m.getPasswordHash());
            m = MemberSupport.setPassword(datastore, "arleth", "patrick", "patrickj");
            println("updated password back ", m.getPasswordHash());
            m = MemberSupport.login(datastore, "arleth", "patrickj");
            println("login with orig password worked ", m.getPasswordHash());
             */
        }

        Resume r = null;

        /*
        Query q = datastore.createQuery(Resume.class);
        datastore.delete(q);
        r = MyResume.now();
        r.setMemberId(m.getId());
        println("zipcode before save ", r.getHome().getZipcode());
        datastore.save(r);
        println("zipcode after save ", r.getHome().getZipcode());
         */

        r = datastore.find(org.arleth.jobs.Resume.class).field("lastName").equal("Arleth").get();
        if (r != null) {
            println(r);
        } else {
            println("no resume found");
        }
    }

    public static void println(Object... args) {
        if (args != null) {
            for (Object arg : args) {
                System.out.print(arg);
            }
            System.out.println();
        }
    }
}