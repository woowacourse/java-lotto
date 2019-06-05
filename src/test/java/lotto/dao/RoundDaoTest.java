package lotto.dao;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class RoundDaoTest {
    @Test
    void addTest() {
        assertThat(1).isEqualTo(new RoundDao().add());
    }

    @Test
    void getLatest() {
        assertThat(-1).isNotEqualTo(new RoundDao().getLatest());
    }
}