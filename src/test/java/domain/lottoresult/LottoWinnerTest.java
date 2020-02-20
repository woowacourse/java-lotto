package domain.lottoresult;

import domain.lottonumber.TestNumberGenerator;
import domain.lottonumber.LottoNumber;
import domain.lottonumber.LottoNumbers;
import domain.lottonumber.LottoNumbersFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class LottoWinnerTest {
    @Test
    void null_방어_로직() {
        Assertions.assertThatThrownBy(() -> new LottoWinner(null, null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("null값이 입력되었습니다.");
    }

    @Test
    void 보너스볼_중복_예외_확인() {
        LottoNumbers lottoNumbers = LottoNumbersFactory.createLottoNumbers(new TestNumberGenerator());
        LottoNumber bonus = LottoNumber.of(6);
        Assertions.assertThatThrownBy(() -> new LottoWinner(lottoNumbers, bonus))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("보너스 숫자가 중복되었습니다.");
    }

    @Test
    void 등수_1등_반환_확인() {
        LottoNumbers lottoNumbers = LottoNumbersFactory.createLottoNumbers(new TestNumberGenerator());
        LottoNumbers checkNumbers = LottoNumbersFactory.createLottoNumbers(new TestNumberGenerator());
        LottoNumber bonus = LottoNumber.of(7);
        LottoWinner winner = new LottoWinner(lottoNumbers, bonus);
        Assertions.assertThat(winner.createRank(checkNumbers)).isEqualTo(LottoRank.FIRST);
    }

    @Test
    void 등수_2등_반환_확인() {
        List<Integer> value = Arrays.asList(1, 2, 3, 4, 6, 7);
        LottoNumbers lottoNumbers = LottoNumbersFactory.createLottoNumbers(new TestNumberGenerator());
        LottoNumbers checkNumbers = LottoNumbersFactory.createLottoNumbers(new TestNumberGenerator(value));
        LottoNumber bonus = LottoNumber.of(7);
        LottoWinner winner = new LottoWinner(lottoNumbers, bonus);
        Assertions.assertThat(winner.createRank(checkNumbers)).isEqualTo(LottoRank.SECOND);
    }

    @Test
    void 등수_3등_반환_확인() {
        List<Integer> value = Arrays.asList(1, 2, 3, 4, 6, 8);
        LottoNumbers lottoNumbers = LottoNumbersFactory.createLottoNumbers(new TestNumberGenerator());
        LottoNumbers checkNumbers = LottoNumbersFactory.createLottoNumbers(new TestNumberGenerator(value));
        LottoNumber bonus = LottoNumber.of(7);
        LottoWinner winner = new LottoWinner(lottoNumbers, bonus);
        Assertions.assertThat(winner.createRank(checkNumbers)).isEqualTo(LottoRank.THIRD);
    }

    @Test
    void 등수_4등_반환_확인() {
        List<Integer> value = Arrays.asList(1, 2, 3, 4, 7, 8);
        LottoNumbers lottoNumbers = LottoNumbersFactory.createLottoNumbers(new TestNumberGenerator());
        LottoNumbers checkNumbers = LottoNumbersFactory.createLottoNumbers(new TestNumberGenerator(value));
        LottoNumber bonus = LottoNumber.of(7);
        LottoWinner winner = new LottoWinner(lottoNumbers, bonus);
        Assertions.assertThat(winner.createRank(checkNumbers)).isEqualTo(LottoRank.FOURTH);
    }

    @Test
    void 등수_5등_반환_확인() {
        List<Integer> value = Arrays.asList(1, 2, 3, 8, 9, 10);
        LottoNumbers lottoNumbers = LottoNumbersFactory.createLottoNumbers(new TestNumberGenerator());
        LottoNumbers checkNumbers = LottoNumbersFactory.createLottoNumbers(new TestNumberGenerator(value));
        LottoNumber bonus = LottoNumber.of(7);
        LottoWinner winner = new LottoWinner(lottoNumbers, bonus);
        Assertions.assertThat(winner.createRank(checkNumbers)).isEqualTo(LottoRank.FIFTH);
    }

    @Test
    void 등수_없음_반환_확인() {
        List<Integer> value = Arrays.asList(11, 21, 13, 14, 16, 17);
        LottoNumbers lottoNumbers = LottoNumbersFactory.createLottoNumbers(new TestNumberGenerator());
        LottoNumbers checkNumbers = LottoNumbersFactory.createLottoNumbers(new TestNumberGenerator(value));
        LottoNumber bonus = LottoNumber.of(7);
        LottoWinner winner = new LottoWinner(lottoNumbers, bonus);
        Assertions.assertThat(winner.createRank(checkNumbers)).isEqualTo(LottoRank.NOTHING);
    }
}
