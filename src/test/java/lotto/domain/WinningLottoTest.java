package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class WinningLottoTest {
    final int MIN_LOTTO_NUMBER = 1;
    final int LOTTO_NUMBER_SIZE = 6;

    @Test
    void validateBonusNumber_보너스볼이_나오지_않은_숫자일_때() {
        Set<LottoNumber> winningNumbers = new HashSet<>();
        for (int i = MIN_LOTTO_NUMBER; i <= LOTTO_NUMBER_SIZE; i++) {
            winningNumbers.add(new LottoNumber(i));
        }
        LottoNumber validBonusNumber = new LottoNumber("7");
        new WinningLotto(winningNumbers, validBonusNumber);
    }

    @Test
    void validateBonusNumber_보너스볼이_이미_나온_숫자일_때() {
        Set<LottoNumber> winningNumbers = new HashSet<>();
        for (int i = MIN_LOTTO_NUMBER; i <= LOTTO_NUMBER_SIZE; i++) {
            winningNumbers.add(new LottoNumber(i));
        }
        LottoNumber invalidBonusNumber = new LottoNumber(LOTTO_NUMBER_SIZE);
        assertThatThrownBy(() -> new WinningLotto(winningNumbers, invalidBonusNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("보너스 번호가 당첨번호와 중복됩니다.");
    }
}
