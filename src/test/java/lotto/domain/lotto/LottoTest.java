package lotto.domain.lotto;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class LottoTest {

    @Test
    @DisplayName("잘못된 개수의 로또 번호")
    void generateIllegalNumberCountLotto() {
        assertThatThrownBy(() -> Lotto.from(Arrays.asList(1, 2, 3, 4)))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining(Lotto.LOTTO_NUMBER_COUNT_ERROR_MESSAGE);
        assertThatThrownBy(() -> Lotto.from(Arrays.asList(1, 2, 3, 4, 5, 6, 7)))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining(Lotto.LOTTO_NUMBER_COUNT_ERROR_MESSAGE);
    }

    @Test
    @DisplayName("중복 로또 번호")
    void generateDuplicateLotto() {
        assertThatThrownBy(() -> Lotto.from(Arrays.asList(1, 1, 3, 4, 5, 45)))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining(Lotto.DUPLICATE_NUMBER_ERROR_MESSAGE);
    }

    @ParameterizedTest
    @DisplayName("일치하는 로또 번호 개수 확인")
    @CsvSource(value = {"1,2,3,4,5,6:6", "1,2,3,4,5,7:5", "1,2,3,4,7,8:4", "1,2,3,7,8,9:3",
        "1,2,7,8,9,10:2", "1,7,8,9,10,11:1", "7,8,9,10,11,12:0"}, delimiter = ':')
    void compareLottoNumber(final String input, final int expected) {
        final Lotto init = Lotto.from(Arrays.asList(1, 2, 3, 4, 5, 6));
        final Integer[] arr = Arrays.stream(input.split(","))
            .map(Integer::new).toArray(Integer[]::new);

        final Lotto lotto = Lotto.from(Arrays.asList(arr));
        final int actual = init.countCommonValue(lotto);
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("보너스볼 포함 여부 확인")
    @CsvSource(value = {"1:true", "7:false"}, delimiter = ':')
    void containBonusNumber(final int input, final boolean expected) {
        final Lotto init = Lotto.from(Arrays.asList(1, 2, 3, 4, 5, 6));
        final LottoNumber inputLottoNumber = LottoNumber.from(input);
        final boolean actual = init.containNumber(inputLottoNumber);
        assertThat(actual).isEqualTo(expected);
    }
}
