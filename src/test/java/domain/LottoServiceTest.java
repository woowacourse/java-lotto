package domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoServiceTest {

    LottoService lottoService;

    @BeforeEach
    void setUp() {
        final Money money = new Money("100000000");

        lottoService = new LottoService(money);
    }

    @DisplayName("구입 금액만큼 발급 받은 로또의 갯수를 확인한다.")
    @Test
    void issueLotto_count_correct() {
        lottoService.issueLotto();
        final List<Lotto> lottoTickets = lottoService.issueLotto();

        assertThat(lottoTickets.size()).isEqualTo(100000);
    }
}
