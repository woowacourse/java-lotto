package domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoFactoryTest {
    LottoFactory lottoFactory;

    @BeforeEach
    void setUp() {
        final Lotto winNumbers = new Lotto("1, 2, 3, 4, 5, 6");
        final LottoNumber bonusNumber = new LottoNumber("7");
        final Money money = new Money("100000000");

        lottoFactory = new LottoFactory(money);
    }

    @DisplayName("자동 로또 발급시 번호 갯수가 6개인지 확인한다.")
    @Test
    void autoLottoNumbers_size_correct() {
        final Lotto lotto = lottoFactory.generateAutoLottoNumbers();

        assertThat(lotto.size()).isEqualTo(6);
    }

    @DisplayName("구입 금액만큼 발급 받은 로또의 갯수를 확인한다.")
    @Test
    void issueLotto_count_correct() {
        lottoFactory.issueLotto();
        final List<Lotto> lottoTickets = lottoFactory.getLottoTickets();

        assertThat(lottoTickets.size()).isEqualTo(100000);
    }

}
