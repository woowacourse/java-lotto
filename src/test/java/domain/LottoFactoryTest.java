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
        final LottoNumbers winNumbers = new LottoNumbers("1, 2, 3, 4, 5, 6");
        final LottoNumber bonusNumber = new LottoNumber("7");
        final Money money = new Money("100000000");

        lottoFactory = new LottoFactory(money, winNumbers, bonusNumber);
    }

    @DisplayName("구입 금액을 로또 발급 갯수로 반환을 확인한다.")
    @Test
    void money_to_counts() {
        assertThat(lottoFactory.calculateCount()).isEqualTo(new Count(100000));
    }

    @DisplayName("자동 로또 발급시 번호 갯수가 6개인지 확인한다.")
    @Test
    void autoLottoNumbers_size_correct() {
        final LottoNumbers lottoNumbers = lottoFactory.generateAutoLottoNumbers();

        assertThat(lottoNumbers.size()).isEqualTo(6);
    }

    @DisplayName("구입 금액만큼 발급 받은 로또의 갯수를 확인한다.")
    @Test
    void issueLotto_count_correct() {
        final Count count = lottoFactory.calculateCount();
        lottoFactory.issueLotto(count);
        final List<LottoNumbers> lottoTickets = lottoFactory.getLottoTickets();

        assertThat(lottoTickets.size()).isEqualTo(100000);
    }

}
