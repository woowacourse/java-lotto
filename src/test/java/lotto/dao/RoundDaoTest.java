package lotto.dao;

import lotto.TestTableCreator;
import lotto.config.DBConnector;
import lotto.config.DataSource;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RoundDaoTest {
    static DBConnector dbConnector = new DBConnector(DataSource.getTestInstance());
    RoundDao roundDao = new RoundDao(dbConnector);

    @BeforeAll
    static void createTable() {
        TestTableCreator.create();
    }

    @Test
    void getLatestTest() {
        assertThat(-1).isNotEqualTo(roundDao.getLatest());
    }

    @Test
    void findAllTest() {
        roundDao.add();
        List<Integer> expected = roundDao.findAll();

        assertThat(0).isNotEqualTo(expected.size());
    }
}