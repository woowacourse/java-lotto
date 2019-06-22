package lotto.service;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class RoundServiceTest {

    @Test
    void getAllRound() throws SQLException {
        RoundService roundService = new RoundService();
        assertThat(roundService.getAllRound()).isEqualTo(Arrays.asList(1,2,3,4,5,6,7,8));
    }
}