package acpbeforemid;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Voter implements Serializable {
    private int voterId;
    private String voterName;
    private int voterAge;
    private String password;
    private int cmVote = 0, pmVote = 0, presiVote = 0;

    public Voter() {
        super();
    }

    public Voter(String voterName, int voterAge, String password) {
        super();
        this.voterAge = voterAge;
        this.voterName = voterName;
        this.password = password;
    }

    public void setVoterId(int voterId) {
        this.voterId = voterId;
    }

    public int getVoterId() {
        return voterId;
    }

    public String getVoterName() {
        return voterName;
    }

    public void setVoterName(String voterName) {
        this.voterName = voterName;
    }

    public int getVoterAge() {
        return voterAge;
    }

    public void setVoterAge(int voterAge) {
        this.voterAge = voterAge;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCmVote(int cmVote) {
        this.cmVote = cmVote;
    }

    public void setPmVote(int pmVote) {
        this.pmVote = pmVote;
    }

    public void setPresiVote(int presiVote) {
        this.presiVote = presiVote;
    }

    public int getCmVote() {
        return cmVote;
    }

    public int getPmVote() {
        return pmVote;
    }

    public int getPresiVote() {
        return presiVote;
    }

    @Override
    public String toString() {
        return "Voter [Id=" + voterId + ", Name=" + voterName + ", Age=" + voterAge + "]";
    }
}