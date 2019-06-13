package lotto.dao;

import lotto.domain.Lotto;
import lotto.domain.generator.LottoNosManualGenerator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LottoDaoTest {
    int round = 1;

    @BeforeAll
    static void createTable() throws Exception {
       TableCreator.create();
    }

    @Test
    void addTest() {
        List<Lotto> lottos = new ArrayList<>();
        lottos.add(Lotto.of(new LottoNosManualGenerator("1,2,3,4,5,6").generate()));
        lottos.add(Lotto.of(new LottoNosManualGenerator("1,2,3,4,12,19").generate()));

        assertTrue(new LottoDao().add(lottos, round));
    }

    @Test
    void findAllByRoundTest() {
        addTest();
        assertThat(0).isNotEqualTo(new LottoDao().findAllByRound(round).size());
    }
}