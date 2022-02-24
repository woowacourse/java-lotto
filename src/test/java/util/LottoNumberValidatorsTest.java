package util;

import static constant.ExceptionMessages.DUPLICATE_WINNING_NUMBER_EXCEPTION_MESSAGE;
import static constant.ExceptionMessages.INVALID_LOTTO_NUMBER_RANGE_EXCEPTION_MESSAGE;
import static constant.ExceptionMessages.INVALID_NUMBER_INPUT_EXCEPTION_MESSAGE;
import static constant.ExceptionMessages.NOT_UNIQUE_BONUS_NUMBER_EXCEPTION_MESSAGE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.junit.jupiter.params.ParameterizedTest.ARGUMENTS_PLACEHOLDER;
import static org.junit.jupiter.params.ParameterizedTest.DISPLAY_NAME_PLACEHOLDER;
import static util.LottoNumberValidator.validateAndParseNumber;
import static util.LottoNumberValidator.validateLottoNumberRange;
import static util.LottoNumberValidator.validateNoDuplicateInList;
import static util.LottoNumberValidator.validateNoDuplicates;

import domain.LottoNumber;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class LottoNumberValidatorsTest {

    public static final String PARAMETERIZED_TEST_DISPLAY_FORMAT =
            DISPLAY_NAME_PLACEHOLDER + " [" + ARGUMENTS_PLACEHOLDER + "]";

    @Test
    void validateAndParseNumber_returnsIntOnPass() {
        int parsedValue = validateAndParseNumber("10");
        assertThat(parsedValue).isEqualTo(10);
    }

    @Test
    void validateAndParseNumber_throwIllegalArgumentException() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> validateAndParseNumber("!"))
                .withMessageMatching(INVALID_NUMBER_INPUT_EXCEPTION_MESSAGE);
    }

    @ParameterizedTest(name = PARAMETERIZED_TEST_DISPLAY_FORMAT)
    @ValueSource(ints = {1, 20, 45})
    void validateLottoNumberRange_passIfInRange(int value) {
        assertThatNoException()
                .isThrownBy(() -> validateLottoNumberRange(value));
    }

    @ParameterizedTest(name = PARAMETERIZED_TEST_DISPLAY_FORMAT)
    @ValueSource(ints = {0, 46})
    void validateLottoNumberRange_throwIllegalArgumentException(int value) {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> validateLottoNumberRange(value))
                .withMessageMatching(INVALID_LOTTO_NUMBER_RANGE_EXCEPTION_MESSAGE);
    }

    @Test
    void validateNoDuplicates_passOnNoDuplicates() {
        List<LottoNumber> lotto = Stream.of(1, 2, 3, 4, 5, 6)
                .map(LottoNumber::of)
                .collect(Collectors.toList());

        assertThatNoException()
                .isThrownBy(() -> validateNoDuplicates(lotto));
    }

    @Test
    void validateNoDuplicates_throwIllegalArgumentExceptionOnFail() {
        List<LottoNumber> lotto = Stream.of(1, 1, 2, 3, 4, 5)
                .map(LottoNumber::of)
                .collect(Collectors.toList());

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> validateNoDuplicates(lotto))
                .withMessageMatching(DUPLICATE_WINNING_NUMBER_EXCEPTION_MESSAGE);
    }

    @Test
    void validateNoDuplicateInList_passOnNoDuplicates() {
        List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5, 6);

        assertThatNoException()
                .isThrownBy(() -> validateNoDuplicateInList(7, nums));
    }

    @Test
    void validateNoDuplicateInList_throwIllegalArgumentExceptionOnFail() {
        List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5, 6);

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> validateNoDuplicateInList(6, nums))
                .withMessageMatching(NOT_UNIQUE_BONUS_NUMBER_EXCEPTION_MESSAGE);
    }
}

