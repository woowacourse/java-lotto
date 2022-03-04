package domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class LottoTest {

    public static Stream<Arguments> provideOtherLottoAndExpected() {
        return Stream.of(
            Arguments.of(Arrays.asList("1", "2", "3", "4", "5", "6"), 6),
            Arguments.of(Arrays.asList("1", "2", "3", "4", "5", "7"), 5),
            Arguments.of(Arrays.asList("1", "2", "3", "4", "7", "8"), 4),
            Arguments.of(Arrays.asList("1", "2", "3", "7", "8", "9"), 3)
        );
    }

    @DisplayName("유효한 로또 번호를 발급을 확인한다.")
    @Test
    void input_lottoNumbers_valid() {
        assertDoesNotThrow(() -> Lotto.fromInput(Arrays.asList("1", "2", "3", "4", "5", "6")));
    }

    @DisplayName("중복된 로또 번호를 발급할시 예외를 발생시킨다.")
    @Test
    void input_lottoNumbers_duplicated() {
        assertThatThrownBy(() -> Lotto.fromInput(Arrays.asList("1", "1", "3", "4", "5", "6")))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("중복");
    }

    @DisplayName("로또 번호가 6개가 아니면 예외를 발생시킨다.")
    @Test
    void input_lottoNumbers_invalid_count() {
        assertThatThrownBy(() -> Lotto.fromInput(Arrays.asList("1", "2", "3", "4", "5")))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("6개");
    }

    @DisplayName("발급 로또와 당첨 로또를 비교한 뒤 당첨 갯수 반환을 확인한다.")
    @Test
    void compare_LottoNumbers() {
        final Lotto lotto = Lotto.fromInput(Arrays.asList("1", "2", "3", "4", "5", "6"));
        final Lotto winNumbers = Lotto.fromInput(Arrays.asList("1", "2", "3", "4", "5", "7"));

        final int matchedCount = lotto.compare(winNumbers);

        assertThat(matchedCount).isEqualTo(5);
    }

    @ParameterizedTest
    @MethodSource("provideOtherLottoAndExpected")
    void compare_lotto_by_lotto(final List<String> otherLottoInput, final int matchedCount) {
        //given
        final Lotto lotto = Lotto.fromInput(Arrays.asList("1", "2", "3", "4", "5", "6"));
        final Lotto otherLotto = Lotto.fromInput(otherLottoInput);
        //when
        final int actual = lotto.compare(otherLotto);
        //then
        assertThat(actual).isEqualTo(matchedCount);
    }

    @DisplayName("발급 로또와 보너스 번호를 비교하여 포함여부를 확인한다.")
    @Test
    void compare_bonusNumbers() {
        final Lotto lotto = Lotto.fromInput(Arrays.asList("1", "2", "3", "4", "5", "6"));
        final LottoNumber bonusNumber = LottoNumber.from("6");

        final boolean hasBonus = lotto.isContainNumber(bonusNumber);

        assertThat(hasBonus).isTrue();
    }
}
