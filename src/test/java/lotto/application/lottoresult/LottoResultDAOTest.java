package lotto.application.lottoresult;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LottoResultDAOTest {
    private LottoResultDAO lottoResultDAO;

    @BeforeEach
    void setUp() {
        lottoResultDAO = LottoResultDAO.getInstance();
    }

    @Test
    void static_생성자() {
        LottoResultDAO lottoResultDAO2 = LottoResultDAO.getInstance();
        assertThat(lottoResultDAO).isEqualTo(lottoResultDAO2);
        assertThat(lottoResultDAO == lottoResultDAO2).isTrue();
    }

    @Test
    void 한_round를_생성() {
        int lastRound = lottoResultDAO.getLatestRoundNum();
        lottoResultDAO.createNextRound();
        int newRound = lottoResultDAO.getLatestRoundNum();

        assertThat(lastRound + 1 == newRound).isTrue();

        lottoResultDAO.deleteRound(newRound);
    }
}