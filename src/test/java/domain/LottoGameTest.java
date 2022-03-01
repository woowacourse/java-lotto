package domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoGameTest {

    @Test
    @DisplayName("로또 게임을 생성하는 경우")
    void createLottoGame() {
        Lotto lotto1 = LottoFactory.createLotto(List.of(1, 2, 3, 4, 17, 16));
        Lotto lotto2 = LottoFactory.createLotto(List.of(1, 2, 3, 4, 17, 9));
        Lotto lotto3 = LottoFactory.createLotto(List.of(1, 2, 3, 4, 17, 8));
        Lottos lottos = new Lottos(List.of(lotto1, lotto2, lotto3));

        LottoGame lottoGame = new LottoGame(lottos);

        assertThat(lottoGame).isNotNull();
    }
}