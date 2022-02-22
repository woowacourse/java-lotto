package model;

import static org.assertj.core.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoGameTest {
    @Test
    @DisplayName("생성된 로또 티켓이 저장되는지 확인한다.")
    void checkGenerateTicket() {
        int purchaseMoney = 17000;
        LottoGame lottoGame = new LottoGame(purchaseMoney);
        assertThat(lottoGame.getTickets().size()).isEqualTo(purchaseMoney / 1000);
    }
}