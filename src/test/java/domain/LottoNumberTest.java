package domain;

import static common.DisplayFormat.PARAMETERIZED_TEST_DISPLAY_FORMAT;
import static common.TestUtils.createLottoNumbersOf;
import static constant.ExceptionMessages.INVALID_LOTTO_NUMBER_RANGE_EXCEPTION_MESSAGE;
import static domain.LottoNumber.MAXIMUM_NUMBER;
import static domain.LottoNumber.MINIMUM_NUMBER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.util.HashSet;
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
    @ValueSource(ints = {MINIMUM_NUMBER - 1, MAXIMUM_NUMBER + 1})
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
    void equalsAndHashCodeNotNeeded_noDuplicatesOnUsingCache() {
        List<LottoNumber> sameLottoNumbers = createLottoNumbersOf(11, 11, 11, 11);

        HashSet<LottoNumber> noDuplicateLottoNumbers = new HashSet<>(sameLottoNumbers);

        assertThat(noDuplicateLottoNumbers.size()).isEqualTo(1);
    }

    @Test
    void compareTo_sortsByAscending() {
        List<LottoNumber> lottoNumbers = createLottoNumbersOf(15, 35, 25, 5, 40, 45);

        List<Integer> sortedNums = lottoNumbers.stream()
                .map(LottoNumber::getNumber)
                .collect(Collectors.toList());

        assertThat(sortedNums).isSorted();
    }
}
