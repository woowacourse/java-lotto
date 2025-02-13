package lotto.service;

import lotto.domain.Lotto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoServiceTest {
    private LottoService lottoService;

    @BeforeEach
    void beforeEach() {
        lottoService = new LottoService();
    }

    @DisplayName("구매 금액에 해당하는 로또 개수를 계산할 수 있다.")
    @Test
    void 구매_금액에_해당하는_로또_개수를_계산할_수_있다() {
        assertThat(lottoService.purchaseLotto(10000)).isEqualTo(10);
    }

    @DisplayName("구매한 개수만큼의 로또를 생성할 수 있다.")
    @Test
    void 구매한_개수만큼의_로또를_생성할_수_있다() {
        int expectedCount = 5;
        List<Lotto> lottos = lottoService.issueLottos(expectedCount);
        assertThat(lottos.size()).isEqualTo(expectedCount);
    }
}
