package ru.fatvinyl.votesystem.web;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.fatvinyl.votesystem.util.DateTimeUtil;
import ru.fatvinyl.votesystem.util.DbPopulator;

import java.time.LocalTime;


/**
 * This controller is used only in the demo version of the application. Not for production.
 *
 * @author Anton Yolgin
 */

@Controller
public class DemoAppController {

    @GetMapping("/updateDb")
    public String updateDb() {
        DbPopulator initDb = new DbPopulator("classpath:db/initDB.sql");
        initDb.execute();
        DbPopulator populateDB = new DbPopulator("classpath:db/populateDB.sql");
        populateDB.execute();
        return "redirect:login";
    }

    @PostMapping("/changeDeadline")
    public String changeDeadline( @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime deadline) {
        DateTimeUtil.deadline = deadline;
        return "redirect:login";
    }

}
