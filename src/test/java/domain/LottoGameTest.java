package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static domain.LottoTest.createLottoNumbers;
import static org.assertj.core.api.Assertions.assertThat;

class LottoGameTest {

    @DisplayName("수동 로또와 자동 로또를 합해 전체 로또를 생성한다.")
    @Test
    void createLottos() {
        LottoGame lottoGame = new LottoGame(new LottoMachine(),
                new LottoOrder(new Payment(5000), 2, 3),
                Arrays.asList(new Lotto(createLottoNumbers(1, 2, 3, 4, 5, 6)),
                        new Lotto(createLottoNumbers(7, 8, 9, 10, 11, 12))));

        Lottos lottos = lottoGame.createLottos();
        int lottosSize = lottos.getLottos().size();
        assertThat(lottosSize).isEqualTo(5);
    }
}
