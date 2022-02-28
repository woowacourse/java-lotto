package lotto.model;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class LottoResultTest {
    @ParameterizedTest
    @CsvSource(value = {"6:30_000_000", "44:1_500_000"}, delimiter = ':')
    @DisplayName("2, 3등 당첨 여부를 확인한다")
    void matchNumber(int bonusNumberInt , Long totalWinningMoney) {
        Lotto lotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));
        Lottos lottos = new Lottos(Collections.singletonList(lotto));

        Lotto winningNumbers = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 45));
        LottoNumber bonusNumber = new LottoNumber(bonusNumberInt);
        LottoResult lottoResult = LottoResult.create(lottos, winningNumbers, bonusNumber);

        assertThat(lottoResult.getTotalWinningMoney()).isEqualTo(totalWinningMoney);
    }
}
