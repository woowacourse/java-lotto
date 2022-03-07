package lotto.model.lottofactory;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lotto.model.LottoNumber;
import lotto.model.Rank;

class LottoTest {

    @Test
    @DisplayName("4등 당첨 판정을 확인한다.")
    void matchNumber() {
        LottoFactory factory = new ManualLottoFactory(List.of(
            List.of(1, 2, 3, 4, 5, 6), List.of(1, 2, 3, 4, 44, 45)));
        Lotto lotto = factory.generate();
        Lotto winningNumbers = factory.generate();

        Rank actual = lotto.match(winningNumbers, new LottoNumber(7));

        assertThat(actual).isEqualTo(Rank.FOURTH);
    }

    @Test
    @DisplayName("2등 당첨으로 보너스 번호 일치 여부를 확인한다")
    void matchBonusNumberTest() {
        LottoFactory factory = new ManualLottoFactory(List.of(
            List.of(1, 2, 3, 4, 5, 6), List.of(1, 2, 3, 4, 5, 45)));
        Lotto lotto = factory.generate();
        Lotto winningNumbers = factory.generate();
        LottoNumber bonusNumber = new LottoNumber(6);

        Rank actual = lotto.match(winningNumbers, bonusNumber);

        assertThat(actual).isEqualTo(Rank.SECOND);
    }

    @Test
    @DisplayName("로또 번호 개수가 6개 미만인 경우 예외 처리")
    void validateNumberOfLottoUnder6Test() {
        LottoFactory factory = new ManualLottoFactory(List.of(
            List.of(1, 2, 3, 4, 5)));
        assertThatThrownBy(factory::generate)
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("로또 번호 개수는 6개로 입력해주세요.");
    }

    @Test
    @DisplayName("로또 번호 개수가 6개 초과인 경우 예외 처리")
    void validateNumberOfLottoOver6Test() {
        LottoFactory factory = new ManualLottoFactory(List.of(
            List.of(1, 2, 3, 4, 5, 6, 7)));
        assertThatThrownBy(factory::generate)
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("로또 번호 개수는 6개로 입력해주세요.");
    }

    @Test
    @DisplayName("로또 번호 중 중복된 값이 입력될 경우 예외 처리")
    void validateDuplicationLottoTest() {
        LottoFactory factory = new ManualLottoFactory(List.of(
            List.of(1, 2, 3, 3, 4, 6)));
        assertThatThrownBy(factory::generate)
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("로또 번호 개수는 6개로 입력해주세요.");
    }
}
