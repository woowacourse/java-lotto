package domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinningLottoTest {

    @Test
    @DisplayName("당청번호와 구매의 로또 번호의 일치 여부를 계산하는 경우")
    void compareWithPurchaseLotto() {
        Lotto purchasedLotto = LottoFactory.createLotto(Arrays.asList(1, 2, 3, 4, 44, 6));
        Lotto lotto = LottoFactory.createLotto(Arrays.asList(12, 23, 6, 44, 17, 16));
        WinningLotto winningLotto = new WinningLotto(lotto, LottoNumber.valueOf(2));

        int matchCount = winningLotto.calculateMatchCount(purchasedLotto);

        assertThat(matchCount).isEqualTo(2);
    }

    @Test
    @DisplayName("당첨번호와 보너스 번호가 중복된 경우")
    void checkDuplicateNumber() {
        Lotto winningLotto = LottoFactory.createLotto(Arrays.asList(12, 23, 6, 44, 17, 16));

        LottoNumber bonusNumber = LottoNumber.valueOf(12);

        assertThatThrownBy(() -> new WinningLotto(winningLotto, bonusNumber))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("당첨번호와 보너스 번호가 유효한 경우")
    void checkValidDuplicateNumber() {
        Lotto winningLotto = LottoFactory.createLotto(Arrays.asList(12, 23, 6, 44, 17, 16));

        LottoNumber bonusNumber = LottoNumber.valueOf(13);

        assertThat(new WinningLotto(winningLotto, bonusNumber)).isNotNull();
    }
}
