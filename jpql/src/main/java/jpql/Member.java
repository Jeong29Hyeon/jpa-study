package jpql;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.FetchType.LAZY;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Member {
    @Id @GeneratedValue
    private Long id;
    private String userName;
    private int age;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    @Enumerated(value = EnumType.STRING)
    private MemberType type;

    public void changeTeam(Team team){
        this.setTeam(team);
        team.getMembers().add(this);
    }

    public MemberType getType() {
        return type;
    }

    public void setType(MemberType type) {
        this.type = type;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Member{" +
            "id=" + id +
            ", userName='" + userName + '\'' +
            ", age=" + age +
            '}';
    }
}