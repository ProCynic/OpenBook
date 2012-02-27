package models;

import java.util.*;
import javax.persistence.*;

import play.db.jpa.*;
import play.data.validation.*;

@Entity
public class Status extends Model {
  
  @Required
  @ManyToOne
  public User author; // The User who authored the status update
  
  @Lob
  @Required
  @MaxSize(1000)
  public String message; // The status update text
  
  public Date date; // The time when submitted
  
  // ############## TO BE IMPLEMENTED #######################################
  
  // public Status linked_status;  // Think Retweet
  
  // @Required
  // public String type; // Text, Link, Location(Check in), Poll
  
  // @OneToMany(mappedBy = "status", cascade = CascadeType.ALL)
  // public List<Comment> comments;
  
  // @OneToMany(mappedBy="status", cascade=CascadeType.ALL)
  // public List<Like> likes;
  
  // @ManyToMany(cascade=CascadeType.PERSIST)
  // public Set<Tag> tags;
  
  // @ManyToMany(cascade=CascadeType.PERSIST)
  //public List<User> mentions;
  
  // ########################################################################
  
  public Status(User author, String message) {
    // this.comments = new ArrayList<Comment>();
    // this.likes = new ArrayList<Like>();
    // this.tags = new ArrayList<Tag>();
    // this.mentions = someFunction(...message parsing...);
    this.author = author;
    this.message = message;
    this.date = new Date();
    // this.linked_status = someFuntion(...message parsing...);
  }
  
  /*
  public Status addComment(String author, String content){
    Comment newComment = new Comment(this, author, content).save();
    this.comments.add(newComment);
    this.save();
    return this;
  }
  */
  
  /*
  public Status addLike(...){
    Like newLike = new Like(...).save();
    this.likes.add(newLike);
    this.save();
    return this;
  }
  */
      
  /**
  *A method that will be useful in later versions when parsing mentions and tags. Use instead of status.message when you want human readable messages.
  *@return A human readble string of a message that contains @User.id and #tags with link and name intact.
  */
  public String messageToString(){
    return message;
  }

  public Status previous() {
    return Post.find("update_time < ? order by update_time asc", date).first();
  }
  
  public Status next() {
    return Post.find("update_time > ? order by update_time asc", date).first();
  }
  
  /*
  public List<Comment> comments() {
    return comments;
  }
  */
  
  /*
  public static List<Status> findTaggedWith(String... tags) {
    return Status.fin(
            "select distiinct p from Status p join p.tags as t where t.name in (:tags) group by p.id, p.author, p.message, p.update_time having count(t.id) = :size"
    ).bind("tags", tags).bind("size", tags.length).fetch();
  }
  */
}
