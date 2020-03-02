package domain.numberscontainer;

import domain.LottoResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("당첨 번호 객체 테스트")
public class WinningNumbersTest {
    @Test
    @DisplayName("당첨 번호 객체 생성")
    void winningNumberConstructor() {
        assertThatThrownBy(() -> new WinningNumbers("1, 2, 3, 4, 5, 5", 7))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("중복되지 않는 6개의 숫자를 입력해주세요.");
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 5})
    @DisplayName("중복 없는 보너스 번호인지 검증")
    void validateBonusNumber(int input) {
        assertThatThrownBy(() -> new WinningNumbers("1, 2, 3, 4, 5, 6", input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("당첨 번호와 중복되지 않는 보너스 번호를 입력해주세요.");
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 46})
    @DisplayName("1부터 45까지의 보너스 번호인지 검증")
    void validateBonusNumber2(int input) {
        assertThatThrownBy(() -> LottoNumber.get(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(String.format("%d이(가) 입력되었습니다. 1부터 45까지의 숫자를 입력해주세요.", input));
    }

    @Test
    @DisplayName("Ticket과 당첨 번호 비교")
    void findDuplicatedNumbers() {
        WinningNumbers winningNumbers = new WinningNumbers("1, 2, 3, 4, 5, 6", 7);
        Ticket ticket = new Ticket("4, 5, 6, 7, 8, 9");
        assertThat((winningNumbers.findDuplicatedNumbers(ticket))).isEqualTo(3);
    }
}