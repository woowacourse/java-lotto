package lotto.model.winningnumber;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import lotto.model.message.BonusBallExceptionMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import lotto.model.message.WinningNumberExceptionMessage;

class WinningLottoTest {
    private final String bonusBall = "13";
    private final List<Integer> winningInts = List.of(1, 2, 3, 4, 5, 6);
    private final List<String> winningStrings = List.of("1", "2", "3", "4", "5", "7");

    @ParameterizedTest
    @ValueSource(strings = {"", "  ", "\t", "\n"})
    @DisplayName("당첨 번호 입력 공백 검증")
    void validateInputLottoNumberBlank(String numbers) {
        assertThatThrownBy(() -> new WinningLotto(List.of(numbers), bonusBall))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(WinningNumberExceptionMessage.BLANK_ERROR.getMassage());
    }

    @Test
    @DisplayName("당첨 번호 입력 null 검증")
    void validateInputLottoNumberNull() {
        assertThatThrownBy(() -> new WinningLotto(null, bonusBall))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(WinningNumberExceptionMessage.BLANK_ERROR.getMassage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"azpi, ++, greeanlawn", "1dksl,-1", "1, 2, as"})
    @DisplayName("당첨 번호가 숫자가 아닌 경우 검증")
    void validateInputLottoWinningNumberIsInt(String numbers) {
        assertThatThrownBy(() -> new WinningLotto(List.of(numbers), bonusBall))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(WinningNumberExceptionMessage.NUMBER_ERROR.getMassage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"46, 1, 2, 3, 4, 5", "0, 45, 2, 3, 4, 5"})
    @DisplayName("당첨 번호가 범위 밖인 경우")
    void validateWinningNumberOutOfRange(String numbers) {
        assertThatThrownBy(() -> new WinningLotto(Arrays.stream(numbers
                        .split(","))
                .map(String::trim)
                .collect(Collectors.toList()), bonusBall))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(WinningNumberExceptionMessage.RANGE_ERROR.getMassage());
    }

    @Test
    @DisplayName("당첨 번호 숫자로 변경 및 저장")
    void saveLottoNumber() {
        WinningLotto winningNumber = new WinningLotto(Arrays.stream("1, 2, 3, 4, 5, 6"
                        .split(","))
                .map(String::trim)
                .collect(Collectors.toList()), bonusBall);

        Set<Integer> winningNumbers = winningNumber.getWinningNumbers();
        assertThat(winningNumbers).isEqualTo(new HashSet<>(winningInts));
    }

    @ParameterizedTest
    @ValueSource(strings = {"1,2,3,4,5", "1,2,3,4,5,6,7"})
    @DisplayName("당첨 번호 숫자 사이즈가 6개가 아닌 경우")
    void validateInputLottoWinningNumberSize(String numbers) {
        assertThatThrownBy(() -> new WinningLotto(List.of(numbers.split(",")), bonusBall))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(WinningNumberExceptionMessage.SIZE_ERROR.getMassage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"1,2,1,3,4,5", "1,2,3,4,5,5"})
    @DisplayName("당첨 번호에 중복이 있는지 검증")
    void validateWinningNumberReduplication(String numbers) {
        assertThatThrownBy(() -> new WinningLotto(List.of(numbers.split(",")), bonusBall))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(WinningNumberExceptionMessage.REDUPLICATION_ERROR.getMassage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"1","2","3","4"})
    @DisplayName("보너스 볼이 당첨 번호와 중복되는지 검증")
    void validateReduplicationWithBonusBall(String number) {
        WinningLotto winningNumber = new WinningLotto(List.of("1,2,3,4,5,6".split(",")), bonusBall);
        assertThatThrownBy(() -> winningNumber.validateReduplicationWithBonusBall(number))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(WinningNumberExceptionMessage.REDUPLICATION_BONUS_BALL_ERROR.getMassage());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "", "\t", "\n"})
    @DisplayName("보너스 볼 입력 공백 검증")
    void validateBonusBallBlank(String number) {
        assertThatThrownBy(() -> {

            new WinningLotto(winningStrings, number);

        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(BonusBallExceptionMessage.BLANK_ERROR.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"-1", "asb"})
    @DisplayName("보너스 볼 입력 숫자 검증")
    void validateBonusBallNumber(String number) {
        assertThatThrownBy(() -> new WinningLotto(winningStrings, number))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(BonusBallExceptionMessage.NUMBER_ERROR.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"46", "0"})
    @DisplayName("보너스 볼 입력 숫자 범위 검증")
    void validateBonusBallOutOfRange(String number) {
        assertThatThrownBy(() -> new WinningLotto(winningStrings, number))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(BonusBallExceptionMessage.RANGE_ERROR.getMessage());
    }

    @Test
    @DisplayName("보너스 볼 숫자로 변환 및 저장")
    void saveBonusBallNumber() {
        WinningLotto winningLotto = new WinningLotto(winningStrings, bonusBall);
        assertThat(winningLotto.getBonusBall()).isEqualTo(13);
    }
}
