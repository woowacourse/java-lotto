package model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import exception.BonusExceptionType;
import exception.CommonExceptionType;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class BonusTest {

    private Lotto lotto;

    @BeforeEach
    void beforeEach() {
        lotto = Lotto.of(List.of(1, 2, 3, 4, 5, 6));
    }

    @Test
    @DisplayName("되는 보너스")
    void validBonus() {
        int bonusNumber = 7;
        int expected = 7;
        Bonus bonus = Bonus.of(bonusNumber, lotto);

        assertThat(bonus.getNumber()).isEqualTo(expected);
    }

    @Test
    @DisplayName("로또와 중복된 숫자가 들어왔을 때 예외 처리된다.")
    void inputWithDuplicate() {
        assertThatThrownBy(() -> Bonus.of(1, lotto))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(BonusExceptionType.BONUS_DUPLICATE.getMessage());
    }

    @ParameterizedTest(name = "{0} 미만이거나 {1}} 초과 숫자가 들어왔을 때 예외 처리된다.")
    @MethodSource("provideLottoRange")
    void inputWithInvalidRange(final int lottoMinRange, final int lottoMaxRange) {
        assertThatThrownBy(() -> Bonus.of(46, lotto))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(BonusExceptionType.INVALID_BONUS_RANGE.getMessage(lottoMinRange, lottoMaxRange));
    }

    @Test
    @Disabled
    @DisplayName("숫자의 갯수가 여러개 일 때 예외 처리된다.")
    void inputWithInvalidCount() {
        assertThatThrownBy(() -> Bonus.of(0, lotto)) // TODO : number 인자 0을 테스트하고 싶은 숫자로 수정
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(CommonExceptionType.INVALID_NUMBER_FORMAT.getMessage());
    }

    static Stream<Arguments> provideLottoRange() {
        return Stream.of(Arguments.of(Lotto.LOTTO_MIN_RANGE, Lotto.LOTTO_MAX_RANGE));
    }
}
