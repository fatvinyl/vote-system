package ru.fatvinyl.votesystem;

import ru.fatvinyl.votesystem.matcher.ModelMatcher;
import ru.fatvinyl.votesystem.model.Restaurant;
import ru.fatvinyl.votesystem.model.Vote;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static ru.fatvinyl.votesystem.DishTestData.TEST_DATE;
import static ru.fatvinyl.votesystem.RestaurantTestData.RESTAURANT1_ID;

/**
 * @author Anton Yolgin
 */
public class VoteTestData {

    public static final ModelMatcher<Vote> VOTE_MATCHER = ModelMatcher.of(Vote.class);

    public static final Integer VOTE_ID_CREATED = 4;
    public static final Vote VOTE1 = new Vote(1, 3, TEST_DATE, 1);
    public static final Vote VOTE2 = new Vote(2, 4, TEST_DATE, 2);
    public static final Vote VOTE3 = new Vote(3, 5, TEST_DATE, 3);
    public static final Vote VOTE_EXPECTED = new Vote(VOTE_ID_CREATED, 1, LocalDate.now(), RESTAURANT1_ID);

    public static final List<Vote> VOTES = Arrays.asList(VOTE1, VOTE2, VOTE3);


    public static Vote getCreated() {
        return new Vote(null, 1, LocalDate.now(), RESTAURANT1_ID);
    }


    public static Vote getIncremented() {
        return new Vote(VOTE_ID_CREATED, 2, LocalDate.now(), RESTAURANT1_ID);
    }

}
