package models;

import java.util.*;

import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Event extends Postable {

  @ManyToOne
  public User owner;

  /*
   * @OneToMany public EventInvite invitedUsers;
   */

  public String name;
  public String script;
  public String location;
  public Date startDate;
  public Date endDate;
  public boolean givenEndDate = false;

  public String privilege;
  public boolean open = false;
  public boolean friends = false;
  public boolean inviteOnly = false;
  // public Location eventVenue;

  @ManyToMany
  @JoinTable(name="EventMembers")
  public Set<User> members;

  public Event(User author, String name, String script, String location) {
    this.owner = author;
    this.name = name;
    this.script = script;
    this.location = location;
    this.members = new HashSet<User>();
    this.members.add(owner);
  }

  public EventInvite newEventInvite(User curGuest) {
    EventInvite myEventInvite = new EventInvite(this, curGuest).save();
    this.save();
    return myEventInvite;
  }

  public List<Post> getPosts() {
    return posts;
  }

  public void addMember(User u) {
    if (!members.contains(u))
      members.add(u);
  }

  public void removeMember(User u) {
    if (members.contains(u))
      members.remove(u);
  }

  public int getMemberCount() {
    return members.size();
  }

  public Set<User> uninvitedFriends(User user) {
    HashSet ret = new HashSet(user.friends);
    ret.removeAll(this.members);
    return ret;
  }
}