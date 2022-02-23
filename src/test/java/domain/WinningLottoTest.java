package domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinningLottoTest {

    @Test
    @DisplayName("당청번호와 구매의 로또 번호의 일치 여부를 계산하는 경우")
    void compareWithPurchaseLotto() {
        List<LottoNumber> purchasedLottoNumbers = Stream.of(1, 2, 3, 4, 44, 6)
            .map(LottoNumber::new)
            .collect(Collectors.toList());
        List<LottoNumber> winningNumbers = Stream.of(12, 23, 6, 44, 17, 16)
            .map(LottoNumber::new)
            .collect(Collectors.toList());

        Lotto purchasedLotto = new Lotto(purchasedLottoNumbers);
        WinningLotto winningLotto = new WinningLotto(winningNumbers);

        assertThat(winningLotto.calculateSameLotto(purchasedLotto)).isEqualTo(2);
    }
}
