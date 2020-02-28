package lotto.service;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lotto.domain.lotto.WinningLotto;

class LottoServiceTest {

    private static LottoService lottoService = new LottoService();

    @Test
    @DisplayName("LottoService에서 winningLotto를 생성")
    void createWinningLottoFromDto() {
        WinningLotto winningLotto = lottoService.createWinningLotto("1,2,3,4,5,6", "7");
        assertThat(winningLotto).isNotNull();
    }
}