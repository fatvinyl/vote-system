package ru.fatvinyl.votesystem.util;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;


/**
 *  @author Anton Yolgin
 */

public class DbPopulator extends ResourceDatabasePopulator {
    private static final ResourceLoader RESOURCE_LOADER = new DefaultResourceLoader();

    DataSource dataSource;

    public DbPopulator(String scriptLocation, DataSource dataSource) {
        super(RESOURCE_LOADER.getResource(scriptLocation));
        this.dataSource = dataSource;
    }

    public void execute() {
        this.setSqlScriptEncoding("utf-8");
        DatabasePopulatorUtils.execute(this, dataSource);
    }
}
