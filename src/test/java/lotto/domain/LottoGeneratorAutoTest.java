package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class LottoGeneratorAutoTest {
    @DisplayName("자동 로또 생성 테스트")
    @Test
    void generator() {
        Customer customer = new Customer(5000, 0, "");
        LottoGame lottoGame = new LottoGame(customer, new LottoGeneratorAuto());
        List<Lotto> autoLotto = lottoGame.lottoGenerate();

        assertThat(autoLotto.size()).isEqualTo(5);
    }
}
