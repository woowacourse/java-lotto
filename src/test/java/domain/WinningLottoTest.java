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

        WinningLotto winningLotto = new WinningLotto(new Lotto(winningNumbers), new LottoNumber(2));

        assertThat(winningLotto.calculateMatchCount(purchasedLotto)).isEqualTo(2);
    }

    @Test
    @DisplayName("당첨번호와 보너스 번호가 중복된 경우")
    void checkDuplicateNumber() {
        List<LottoNumber> winningNumbers = Stream.of(12, 23, 6, 44, 17, 16)
            .map(LottoNumber::new)
            .collect(Collectors.toList());
        Lotto winningLotto = new Lotto(winningNumbers);

        LottoNumber bonusNumber = new LottoNumber(12);

        assertThatThrownBy(() -> new WinningLotto(winningLotto, bonusNumber))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("당첨번호와 보너스 번호가 유효한 경우")
    void checkValidDuplicateNumber() {
        List<LottoNumber> winningNumbers = Stream.of(12, 23, 6, 44, 17, 16)
            .map(LottoNumber::new)
            .collect(Collectors.toList());
        Lotto winningLotto = new Lotto(winningNumbers);

        LottoNumber bonusNumber = new LottoNumber(13);

        assertThat(new WinningLotto(winningLotto, bonusNumber)).isNotNull();
    }
}
