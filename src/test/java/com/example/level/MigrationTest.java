package com.example.level;

import com.playtika.test.common.spring.DockerPresenceBootstrapConfiguration;
import com.playtika.test.mariadb.EmbeddedMariaDBBootstrapConfiguration;
import liquibase.Liquibase;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.SQLException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MigrationTest.MigrationTestConfiguration.class)
public class MigrationTest {

    private static final String CHANGE_LOG_FILE = "liquibase/db/changelog/changelog-current.xml";

    @Autowired
    protected DataSource dataSource;

    @Test
    public void shouldUpdateAndRollback() throws LiquibaseException, SQLException {
        String liquibaseContext = "test";
        Liquibase liquibase = getLiquibase();

        liquibase.update(liquibaseContext);
        liquibase.rollback(liquibase.getDatabase().getRanChangeSetList().size(), liquibaseContext);
        liquibase.update(liquibaseContext);
    }

    private Liquibase getLiquibase() throws SQLException, LiquibaseException {
        return new Liquibase(
                CHANGE_LOG_FILE,
                new ClassLoaderResourceAccessor(),
                DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(dataSource.getConnection()))
        );
    }

    @EnableAutoConfiguration
    @Configuration
    @Import({DockerPresenceBootstrapConfiguration.class, EmbeddedMariaDBBootstrapConfiguration.class})
    public static class MigrationTestConfiguration {
    }
}