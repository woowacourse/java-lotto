package domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class LottoFactoryTest {

    LottoFactory lottoFactory;

    @BeforeEach
    void setUp() {
        final Money money = new Money("100000000");

        lottoFactory = new LottoFactory(money);
    }

    @DisplayName("구입 금액만큼 발급 받은 로또의 갯수를 확인한다.")
    @Test
    void issueLotto_count_correct() {
        lottoFactory.issueLotto();
        final List<Lotto> lottoTickets = lottoFactory.getLotto();

        assertThat(lottoTickets.size()).isEqualTo(100000);
    }
}
