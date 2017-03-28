package ru.fatvinyl.votesystem.to;


import com.fasterxml.jackson.annotation.JsonProperty;
import ru.fatvinyl.votesystem.model.Dish;
import ru.fatvinyl.votesystem.model.Vote;

import java.util.List;

/**
 * @author Anton Yolgin
 */

public class RestaurantWithVote {

    private Integer id;

    private String name;

    private List<Dish> menu;

    private Vote vote;

//    private Integer voteId;
//
//    private Integer amountVotes;

    public RestaurantWithVote(@JsonProperty("id") Integer id,
                              @JsonProperty("name") String name,
                              @JsonProperty("menu")  List<Dish> menu,
                              @JsonProperty("vote") Vote vote) {
        this.id = id;
        this.name = name;
        this.menu = menu;
        this.vote = vote;
    }

//    public RestaurantWithVote(@JsonProperty("id") Integer id,
//                              @JsonProperty("name") String name,
//                              @JsonProperty("menu") List<Dish> menu,
//                              @JsonProperty("voteId") Integer voteId,
//                              @JsonProperty("amountVotes") Integer amountVotes) {
//        this.id = id;
//        this.name = name;
//        this.menu = menu;
//        this.voteId = voteId;
//        this.amountVotes = amountVotes;
//    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Dish> getMenu() {
        return menu;
    }

    public void setMenu(List<Dish> menu) {
        this.menu = menu;
    }

    public Vote getVote() {
        return vote;
    }

    public void setVote(Vote vote) {
        this.vote = vote;
    }

//    public Integer getVoteId() {
//        return voteId;
//    }
//
//    public void setVoteId(Integer voteId) {
//        this.voteId = voteId;
//    }
//
//    public Integer getAmountVotes() {
//        return amountVotes;
//    }
//
//    public void setAmountVotes(Integer amountVotes) {
//        this.amountVotes = amountVotes;
//    }

    @Override
    public String toString() {
        return "RestaurantWithVote{" +
                "id=" + id +
                ", name=" + name +
                ", menu=" + menu +
                ", vote=" + vote +
                '}';
    }

//    @Override
//    public String toString() {
//        return "RestaurantWithVote{" +
//                "id=" + id +
//                ", name='" + name +
//                ", menu=" + menu +
//                ", voteId=" + voteId +
//                ", amountVotes=" + amountVotes +
//                '}';
//    }
}
