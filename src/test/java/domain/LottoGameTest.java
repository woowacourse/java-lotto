package domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.strategy.RandomLottoGeneratorStrategy;

public class LottoGameTest {

    @Test
    @DisplayName("로또 게임을 생성하는 경우")
    void createLottoGame() {
        int amount = 5000;
        LottoMoney lottoMoney = new LottoMoney(amount);

        LottoGame lottoGame = new LottoGame(lottoMoney.calculateLottoCount(), new RandomLottoGeneratorStrategy());

        assertThat(lottoGame).isNotNull();
    }
}
