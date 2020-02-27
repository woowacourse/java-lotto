package lotto.service;

import lotto.domain.lotto.WinningLotto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LottoServiceTest {

    private static LottoService lottoService = new LottoService();

    @Test
    @DisplayName("lottoService는 winningLottoDto를 받아서 winningLotto를 생성")
    void createWinningLottoFromDto() {
        WinningLotto winningLotto = lottoService.createWinningLotto("1,2,3,4,5,6", "7");
        assertThat(winningLotto).isNotNull();
    }
}