package ru.fatvinyl.votesystem.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.fatvinyl.votesystem.model.Restaurant;
import ru.fatvinyl.votesystem.util.DateTimeUtil;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Anton Yolgin
 */

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
//@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class RestaurantRepositoryImplTest {

    @Autowired
    protected RestaurantRepository repository;

    @Test
    public void getAllTest() throws Exception {

        LocalDate localDate = LocalDate.parse("2017-01-11", DateTimeUtil.DATE_FORMATTER);
        Collection<Restaurant> actual = repository.getAll(localDate);
        System.out.println("123");
    }

}