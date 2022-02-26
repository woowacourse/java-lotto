package validator;

import static common.DisplayFormat.PARAMETERIZED_TEST_DISPLAY_FORMAT;
import static constant.ExceptionMessages.DUPLICATE_WINNING_NUMBER_EXCEPTION_MESSAGE;
import static constant.ExceptionMessages.INVALID_LOTTO_NUMBERS_SIZE_EXCEPTION_MESSAGE;
import static constant.ExceptionMessages.INVALID_LOTTO_NUMBER_RANGE_EXCEPTION_MESSAGE;
import static constant.ExceptionMessages.INVALID_NUMBER_INPUT_EXCEPTION_MESSAGE;
import static constant.ExceptionMessages.INVALID_TOTAL_LOTTO_PRICE_EXCEPTION_MESSAGE;
import static constant.ExceptionMessages.NEGATIVE_NUMBER_INPUT_EXCEPTION_MESSAGE;
import static constant.ExceptionMessages.NOT_UNIQUE_BONUS_NUMBER_EXCEPTION_MESSAGE;
import static constant.LottoConstants.LOTTO_PRICE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static validator.NumberValidators.validateAndParseNumber;
import static validator.NumberValidators.validateLottoNumberRange;
import static validator.NumberValidators.validateNoDuplicateInList;
import static validator.NumberValidators.validateNoDuplicates;
import static validator.NumberValidators.validateTotalLottoPriceUnit;
import static validator.NumberValidators.validateLottoNumbersSize;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class NumberValidatorsTest {

    @Test
    void validateAndParseNumber_returnsIntOnPass() {
        int parsedValue = validateAndParseNumber("10");
        assertThat(parsedValue).isEqualTo(10);
    }

    @ParameterizedTest(name = PARAMETERIZED_TEST_DISPLAY_FORMAT)
    @ValueSource(strings = {"10 ", " 10", "  10  "})
    void validateAndParseNumber_passesOnNumberWithBlank(String value) {
        int parsedValue = validateAndParseNumber(value);
        assertThat(parsedValue).isEqualTo(10);
    }

    @Test
    void validateAndParseNumber_throwIllegalArgumentExceptionOnFail() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> validateAndParseNumber("!"))
                .withMessageMatching(INVALID_NUMBER_INPUT_EXCEPTION_MESSAGE);
    }

    @Test
    void validateTotalLottoPriceUnit_returnsIntOnPass() {
        assertThatNoException()
                .isThrownBy(() -> validateTotalLottoPriceUnit(LOTTO_PRICE * 10));
    }

    @Test
    void validateTotalLottoPriceUnit_failOnNegativeNumberInput() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> validateTotalLottoPriceUnit(LOTTO_PRICE * -10))
                .withMessageMatching(NEGATIVE_NUMBER_INPUT_EXCEPTION_MESSAGE);
    }

    @Test
    void validateTotalLottoPriceUnit_failIfChangesExist() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> validateTotalLottoPriceUnit(LOTTO_PRICE * 10 + 1))
                .withMessageMatching(INVALID_TOTAL_LOTTO_PRICE_EXCEPTION_MESSAGE);
    }

    @ParameterizedTest(name = PARAMETERIZED_TEST_DISPLAY_FORMAT)
    @ValueSource(ints = {1, 20, 45})
    void validateLottoNumberRange_passIfInRange(int value) {
        assertThatNoException()
                .isThrownBy(() -> validateLottoNumberRange(value));
    }

    @ParameterizedTest(name = PARAMETERIZED_TEST_DISPLAY_FORMAT)
    @ValueSource(ints = {0, 46})
    void validateLottoNumberRange_throwIllegalArgumentExceptionOnFail(int value) {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> validateLottoNumberRange(value))
                .withMessageMatching(INVALID_LOTTO_NUMBER_RANGE_EXCEPTION_MESSAGE);
    }

    @Test
    void validateLottoNumbersSize_passOnValidListSize() {
        List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5, 6);

        assertThatNoException()
                .isThrownBy(() -> validateLottoNumbersSize(nums));
    }

    @Test
    void validateLottoNumbersSize_throwsIllegalExceptionOnInvalidListSize() {
        List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5);

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> validateLottoNumbersSize(nums))
                .withMessageMatching(INVALID_LOTTO_NUMBERS_SIZE_EXCEPTION_MESSAGE);
    }

    @Test
    void validateNoDuplicates_passOnNoDuplicates() {
        List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5, 6);

        assertThatNoException()
                .isThrownBy(() -> validateNoDuplicates(nums));
    }

    @Test
    void validateNoDuplicates_throwIllegalArgumentExceptionOnFail() {
        List<Integer> nums = Arrays.asList(1, 1, 2, 3, 4, 5);

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> validateNoDuplicates(nums))
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
