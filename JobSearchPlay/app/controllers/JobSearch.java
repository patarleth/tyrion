package controllers;

import play.*;
import play.mvc.*;
import play.data.*;
import play.data.validation.Constraints.*;

import views.html.*;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.Morphia;
import com.google.code.morphia.query.Query;
import com.mongodb.Mongo;
import com.mongodb.MongoURI;

import org.bson.types.ObjectId;

import com.google.code.morphia.logging.slf4j.*;
import com.google.code.morphia.logging.*;

import org.arleth.jobs.*;
import org.arleth.reg.*;

public class JobSearch extends Controller {
    private static Datastore datastore = getDatastore();

    static {
	MorphiaLoggerFactory.reset();
	MorphiaLoggerFactory.registerLogger(SLF4JLogrImplFactory.class);
    }

    public static Datastore getDatastore() {
	if( datastore == null ) {
	    try {
		String test = Play.application().configuration().getString("mongo.username");

		String mongoUser = Play.application().configuration().getString("mongo.username");
		String mongoPwd = Play.application().configuration().getString("mongo.pwd");

		MongoURI uri = new MongoURI("mongodb://" + mongoUser + ":" + mongoPwd + "@staff.mongohq.com:10070/arlethJobSearch");
		Mongo mongo = new Mongo(uri);
		Morphia morphia = new Morphia().map(org.arleth.jobs.Resume.class).map(org.arleth.reg.Member.class);
		datastore = morphia.createDatastore(mongo, "arlethJobSearch", mongoUser, mongoPwd.toCharArray());
	    } catch( Exception e ) {
		e.printStackTrace();
	    }
	}
	return datastore;
    }
    
    public static Result index() {
	return ok(index.render("Your new application is ready."));
    }
    
    public static Result myResume() {
	Resume resume = getDatastore().find(org.arleth.jobs.Resume.class).field("lastName").equal("Arleth").get();
	if( resume == null ) {
	    resume = MyResume.now();
	}
	return ok(myResume.render(resume));
    }

    public static Result resume(String id) {
	Datastore datastore = getDatastore();
	Resume resume = datastore.find(org.arleth.jobs.Resume.class).field("memberId").equal(new ObjectId(id)).get();
	return ok(myResume.render(resume));
    }

    public static Result login() {
        Form<Login> form = form(Login.class).bindFromRequest();
	String username = null;
	String password = null;
	Member member = null;
	String sessionVal = null;
	
	if( form.hasErrors() ) {
	    System.out.println( "login form has errors");
	    return ok(login.render(form, username, password, member, sessionVal));
	} else {
	    System.out.println( "login form should be good to go!");
	    Login l = form.get();
	    username = l.username;
	    password = l.password;
	    System.out.println( "login username " + username + " pwd " + password );
	    if( username != null && password != null ) {
		Datastore datastore = getDatastore();
		member = MemberSupport.login(datastore, username, password);
		if( member != null ) {
		    System.out.println( "member was found!" );
		    sessionVal = MemberSupport.sessionHash(member);
		    if( sessionVal != null ) {
			System.out.println( "session val !" + sessionVal );
			response().setCookie( "resume", sessionVal, -1, "/");
			String str = member.getId().toString();
			System.out.println("member id str " + str );
			return redirect(routes.JobSearch.resume(str));
		    }
		} else {
		    System.out.println( "member is still null" );
		}
	    }
	    return ok(login.render(form, username, password, member, sessionVal));
	}
    }


    public static class Login {
        @Required public String username;
        @Required public String password;
    } 
}