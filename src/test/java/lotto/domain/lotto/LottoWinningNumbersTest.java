package lotto.domain.lotto;

import java.util.Arrays;
import lotto.controller.LottoController;
import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.LottoNumber;
import lotto.domain.lotto.LottoWinningNumbers;
import lotto.domain.result.LottoResult;
import lotto.domain.result.Rank;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.assertj.core.api.Assertions.*;

class LottoWinningNumbersTest {

    @ParameterizedTest
    @CsvSource(value = "1,2,3,4,5:6", delimiter = ':')
    public void 잘못된_당첨번호_입력_테스트(String value, String number) {
        LottoController lottoController = new LottoController();

        assertThatThrownBy(() -> lottoController.createLottoByNumbers(value))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @CsvSource(value = "1,2,3,4,5,6:7", delimiter = ':')
    public void 옳은_당첨번호_입력_테스트(String value, String number) {
        LottoController lottoController = new LottoController();

        Lotto lotto = lottoController.createLottoByNumbers(value);
        LottoNumber lottoNumber = new LottoNumber(number);

        new LottoWinningNumbers(lotto, lottoNumber);
    }

    @ParameterizedTest
    @CsvSource(value = "1,2,3,4,5,6:6", delimiter = ':')
    public void 보너스볼_중복_테스트(String value, String number) {
        LottoController lottoController = new LottoController();

        Lotto lotto = lottoController.createLottoByNumbers(value);
        LottoNumber lottoNumber = new LottoNumber(number);

        assertThatThrownBy(() -> new LottoWinningNumbers(lotto, lottoNumber))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void 당첨결과_계산_테스트() {
        LottoWinningNumbers lottoWinningNumbers = new LottoWinningNumbers(new Lotto(Arrays.asList(1,2,3,4,5,6)), new LottoNumber("7"));
        LottoResult lottoResult = new LottoResult();
        lottoResult.calculateWinning(lottoWinningNumbers.getWinningLotto(), lottoWinningNumbers.getBonusNumber(),
                new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6)));
        assertThat(lottoResult.getRankCount(Rank.FIRST)).isEqualTo(1);
        assertThat(lottoResult.getRankCount(Rank.SECOND)).isEqualTo(0);
        assertThat(lottoResult.getRankCount(Rank.THIRD)).isEqualTo(0);
    }

    @Test
    void 당첨번호_빈값_검증() {
        assertThatThrownBy(() -> new LottoWinningNumbers(null,  new LottoNumber("1")))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @NullAndEmptySource
    void 보너스번호_빈값_검증(String value) {
        assertThatThrownBy(() -> new LottoWinningNumbers(new Lotto(Arrays.asList(1,2,3,4,5,6)),  new LottoNumber(value)))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
