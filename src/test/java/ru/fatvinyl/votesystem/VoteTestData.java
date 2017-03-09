package ru.fatvinyl.votesystem;

import ru.fatvinyl.votesystem.matcher.ModelMatcher;
import ru.fatvinyl.votesystem.model.Vote;

import java.time.LocalDate;

import static ru.fatvinyl.votesystem.DishTestData.TEST_DATE;
import static ru.fatvinyl.votesystem.RestaurantTestData.RESTAURANT1_ID;

/**
 * @author Anton Yolgin
 */
public class VoteTestData {

    public static final ModelMatcher<Vote> VOTE_MATCHER = ModelMatcher.of(Vote.class);

    public static final Integer VOTE_ID_CREATED = 3;
    public static final Vote VOTE1 = new Vote(0, 2, TEST_DATE);
    public static final Vote VOTE2 = new Vote(1, 3, TEST_DATE);
    public static final Vote VOTE3 = new Vote(2, 4, TEST_DATE);
    public static final Vote VOTE_EXPECTED = new Vote(VOTE_ID_CREATED, 1, LocalDate.now(), RESTAURANT1_ID);


    public static Vote getCreated() {
        return new Vote(null, 1, LocalDate.now(), RESTAURANT1_ID);
    }


    public static Vote getIncremented() {
        return new Vote(VOTE_ID_CREATED, 2, LocalDate.now(), RESTAURANT1_ID);
    }

}
