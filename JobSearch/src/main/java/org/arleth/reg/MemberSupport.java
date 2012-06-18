package org.arleth.reg;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.query.Query;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.regex.Pattern;
import org.arleth.jobs.Email;
import org.arleth.jobs.TelephoneNumber;
import org.bson.types.ObjectId;
import sun.misc.BASE64Encoder;

/**
 *
 * @author arleth
 */
public class MemberSupport {

    public static Member setPassword(Datastore datastore,
            String username, String oldPassword, String newPassword) throws PasswordValidationException, PasswordEncryptException {
        Member result = null;
        if (datastore != null && username != null
                && oldPassword != null && newPassword != null) {
            if (!validPassword(newPassword)) {
                throw new PasswordValidationException();
            } else {
                try {
                    String hashOld = encrypt(oldPassword, "SHA-1", "UTF-8");
                    String hashNew = encrypt(newPassword, "SHA-1", "UTF-8");

                    Query<Member> q = datastore.createQuery(Member.class);
                    q.and(q.criteria("username").equal(username)).
                            and((q.criteria("passwordHash").equal(hashOld)));
                    result = q.get();

                    if (result != null) {
                        result.setPasswordHash(hashNew);
                        result.setDateModified(new Date());
                        datastore.save(result);
                    }
                    //result = datastore.find(Member.class).field("username").equal(username).get();
                } catch (PasswordEncryptException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return result;
    }

    public static Member create(Datastore datastore,
            String username, String password, String email, String homePhone)
            throws EmailValidationException,
            UsernameValidationException,
            PasswordValidationException,
            MemberExistsException, PasswordEncryptException {
        Member result = null;
        if (username != null && password != null && email != null) {
            if (!validUsername(username)) {
                throw new UsernameValidationException();
            } else if (!validPassword(password)) {
                throw new PasswordValidationException();
            } else if (!validEmail(email)) {
                throw new EmailValidationException();
            } else {
                result = datastore.find(Member.class).field("username").equal(username).get();
                if (result != null) {
                    new MemberExistsException();
                } else {
                    result = new Member();
                    result.setUsername(username);
                    result.setPasswordHash(encrypt(password, "SHA-1", "UTF-8"));
                    result.setEmail(new Email(email));
                    result.setHomePhone(new TelephoneNumber(homePhone));
                    result.setDateCreated(new Date());
                    result.setDateModified(result.getDateCreated());
                    datastore.save(result);
                }
            }
        }
        return result;
    }

    public static boolean validUsername(String username) {
        boolean result = false;
        if (username != null && username.length() > 2 && username.matches("[a-zA-Z0-9_\\-.~]+")) {
            result = true;
        }
        return result;
    }

    private static boolean validPassword(String password) {
        boolean result = false;
        if (password != null && password.length() > 6) {
            result = true;
        }
        return result;
    }

    private static boolean validEmail(String email) {
        boolean result = false;
        if (email != null) {
            //put actual email validation here
            result = Pattern.compile("[^@]+@[^@]+").matcher(email).matches();
        }
        return result;
    }

    public static Member login(Datastore datastore, String username, String password) {
        Member result = null;
        if (datastore != null && username != null && password != null) {
            try {
                String hash = encrypt(password, "SHA-1", "UTF-8");

                Query<Member> q = datastore.createQuery(Member.class);
                q.and(q.criteria("username").equal(username)).
                        and((q.criteria("passwordHash").equal(hash)));
                result = q.get();
                //result = datastore.find(Member.class).field("username").equal(username).get();
            } catch (PasswordEncryptException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    public static synchronized String encrypt(String plaintext,
            String algorithm, String encoding) throws PasswordEncryptException {
        String hashValue = null;
        MessageDigest msgDigest = null;
        try {
            msgDigest = MessageDigest.getInstance(algorithm);
            msgDigest.update(plaintext.getBytes(encoding));
            byte rawByte[] = msgDigest.digest();
            hashValue = (new BASE64Encoder()).encode(rawByte);

        } catch (NoSuchAlgorithmException e) {
            throw new PasswordEncryptException(e);
        } catch (UnsupportedEncodingException e) {
            throw new PasswordEncryptException(e);
        }
        return hashValue;
    }

    public static String sessionHash(Member m) {
        String result = null;
        try {
            String id = m.getId().toString();
            result = id + "+" + encrypt(m.getUsername() + m.getPasswordHash(), "SHA-1", "UTF-8");
        } catch (PasswordEncryptException ex) {
        }
        return result;
    }

    public static boolean validateSession(Datastore ds, String sessionValue) {
        boolean result = false;
        if (sessionValue != null && !sessionValue.equals("")) {
            int plus = sessionValue.indexOf("+");
            if (plus > 0) {
                String idStr = sessionValue.substring(0, plus);
                System.out.println( "validateSession idStr " + idStr);
                Member m = ds.get(Member.class, new ObjectId(idStr));
                String hash = sessionHash(m);
                result = (hash.equals(sessionValue));
            }
        }
        return result;
    }
}