package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class WinningLottoTest {
    @Test
    void validateBonusNumber_보너스볼이_나오지_않은_숫자일_때() {
        List<LottoNumber> winningNumbers = new ArrayList<LottoNumber>(Arrays.asList(
                new LottoNumber("1"),
                new LottoNumber("2"),
                new LottoNumber("3"),
                new LottoNumber("4"),
                new LottoNumber("5"),
                new LottoNumber("6")));
        LottoNumber validBonusNumber = new LottoNumber("7");
        new WinningLotto(winningNumbers, validBonusNumber);
    }

    @Test
    void validateBonusNumber_보너스볼이_이미_나온_숫자일_때() {
        List<LottoNumber> winningNumbers = new ArrayList<LottoNumber>(Arrays.asList(
                new LottoNumber("1"),
                new LottoNumber("2"),
                new LottoNumber("3"),
                new LottoNumber("4"),
                new LottoNumber("5"),
                new LottoNumber("6")));
        LottoNumber invalidBonusNumber = new LottoNumber("6");
        assertThatThrownBy(() -> new WinningLotto(winningNumbers, invalidBonusNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("보너스 번호가 당첨번호와 중복됩니다.");
    }
}
