package lotto.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
}
