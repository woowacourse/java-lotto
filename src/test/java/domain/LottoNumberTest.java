package domain;

import static common.DisplayFormat.PARAMETERIZED_TEST_DISPLAY_FORMAT;
import static constant.ExceptionMessages.INVALID_LOTTO_NUMBER_RANGE_EXCEPTION_MESSAGE;
import static constant.LottoConstants.MAXIMUM_LOTTO_NUMBER;
import static constant.LottoConstants.MINIMUM_LOTTO_NUMBER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class LottoNumberTest {

    @Test
    void of_returnsIntOnValidInteger() {
        LottoNumber lottoNum = LottoNumber.of(1);
        assertThat(lottoNum.getNumber()).isEqualTo(1);
    }

    @ParameterizedTest(name = PARAMETERIZED_TEST_DISPLAY_FORMAT)
    @ValueSource(ints = {MINIMUM_LOTTO_NUMBER - 1, MAXIMUM_LOTTO_NUMBER + 1})
    void of_failsOnInvalidRangeInteger(int value) {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> LottoNumber.of(value))
                .withMessageMatching(INVALID_LOTTO_NUMBER_RANGE_EXCEPTION_MESSAGE);
    }

    @Test
    void of_returnsCacheForAlreadyCreatedNumber() {
        LottoNumber newLottoNum = LottoNumber.of(10);
        LottoNumber cachedLottoNum = LottoNumber.of(10);

        assertThat(newLottoNum).isEqualTo(cachedLottoNum);
    }

    @Test
    void compareTo_sortsByAscending() {
        List<LottoNumber> lottoNumbers = createLottoNumbersOf(15, 35, 25, 5, 45);

        List<Integer> sortedNums = lottoNumbers.stream()
                .map(LottoNumber::getNumber)
                .collect(Collectors.toList());

        assertThat(sortedNums).isSorted();
    }

    private List<LottoNumber> createLottoNumbersOf(int... nums) {
        return Arrays.stream(nums).boxed()
                .map(LottoNumber::of)
                .sorted()
                .collect(Collectors.toList());
    }
}
