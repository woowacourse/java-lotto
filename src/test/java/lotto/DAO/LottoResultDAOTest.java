package lotto.dao;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoResultDAOTest {

    @Test
    void 라운드_출력_테스트() {
        assertThat(LottoResultDAO.getInstance().findPresentRound()).isEqualTo(1);
    }
}
