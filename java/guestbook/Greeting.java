package guestbook;

import java.util.Date;

import com.google.appengine.api.users.User;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Parent;


@Entity
public class Greeting implements Comparable<Greeting> {
    @Parent Key<Guestbook> guestbookName;
    @Id Long id;
    @Index User user;
    @Index String content;
    @Index Date date;
    @Index String title;
    private Greeting() {}
    public Greeting(User user, String content, String guestbookName, String title) {
        this.user = user;
        this.title = title;
        this.content = content;
        this.guestbookName = Key.create(Guestbook.class, guestbookName);
        date = new Date();
    }
    public User getUser() {
        return user;
    }
    public String getContent() {
        return content;
    }
    public String getTitle() {
    	return title;
    }

    @Override
    public int compareTo(Greeting other) {
        if (date.after(other.date)) {
            return 1;
        } else if (date.before(other.date)) {
            return -1;
        }
        return 0;
     }
}