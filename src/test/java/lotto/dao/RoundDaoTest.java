package lotto.dao;

import lotto.config.TableCreator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RoundDaoTest {
    @BeforeAll
    static void createTable() throws Exception {
        TableCreator.create();
    }

    @Test
    void addTest() {
        assertThat(1).isEqualTo(new RoundDao().add());
    }

    @Test
    void getLatestTest() {
        assertThat(-1).isNotEqualTo(new RoundDao().getLatest());
    }
}