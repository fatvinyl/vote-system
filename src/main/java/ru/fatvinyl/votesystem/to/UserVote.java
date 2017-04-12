package ru.fatvinyl.votesystem.to;

import java.io.Serializable;

/**
 * @author Anton Yolgin
 */

public class UserVote implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer voteId;

    private Boolean isVote;

    public UserVote() {
    }

    public UserVote(Integer id, Integer voteId) {
        this.id = id;
        this.voteId = voteId;
        if (voteId == null) {
            this.isVote = false;
        } else {
            this.isVote = true;
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVoteId() {
        return voteId;
    }

    public void setVoteId(Integer voteId) {
        this.voteId = voteId;
    }

    public Boolean getVote() {
        return isVote;
    }

    public void setVote(Boolean vote) {
        isVote = vote;
    }

    @Override
    public String toString() {
        return "UserVote{" +
                "id=" + id +
                ", voteId=" + voteId +
                ", isVote=" + isVote +
                '}';
    }
}
