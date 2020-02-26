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
        LottoNumbersDto lottoNumbersDto = createSixNumbersDto(1, 2, 3, 4, 5, 5);
        BonusNumberDTO bonusNumberDTO = new BonusNumberDTO("7");
        assertThatThrownBy(() -> new WinningNumbers(lottoNumbersDto, bonusNumberDTO))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("6개의 숫자를 입력해주세요.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"1", "5"})
    @DisplayName("중복 없는 보너스 번호인지 검증")
    void validateBonusNumber(String input) {
        LottoNumbersDto lottoNumbersDto = createSixNumbersDto(1, 2, 3, 4, 5, 6);
        BonusNumberDTO bonusNumberDTO = new BonusNumberDTO(input);
        assertThatThrownBy(() -> new WinningNumbers(lottoNumbersDto, bonusNumberDTO))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageEndingWith("보너스 번호를 입력해주세요.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"-1", "46"})
    @DisplayName("1부터 45까지의 보너스 번호인지 검증")
    void validateBonusNumber2(String input) {
        assertThatThrownBy(() -> new BonusNumberDTO(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageEndingWith("숫자를 입력해주세요.");
    }

    @Test
    @DisplayName("Ticket과 당첨 번호 비교")
    void findDuplicatedNumbers() {
        LottoNumbersDto lottoNumbersDto1 = createSixNumbersDto(1, 2, 3, 4, 5, 6);
        BonusNumberDTO bonusNumberDTO = new BonusNumberDTO("7");
        LottoNumbersDto lottoNumbersDto2 = createSixNumbersDto(4, 5, 6, 7, 8, 9);
        WinningNumbers winningNumbers = new WinningNumbers(lottoNumbersDto1, bonusNumberDTO);
        Ticket ticket = new Ticket(lottoNumbersDto2);
        assertThat((winningNumbers.findDuplicatedNumbers(ticket))).isEqualTo(3);
    }

    @Test
    @DisplayName("당첨 결과 확인")
    void getLottoResultTest() {
        WinningNumbers winningNumbers = new WinningNumbers(createSixNumbersDto(3, 4, 5, 6, 7, 8), new BonusNumberDTO("9"));

        assertThat(winningNumbers.getLottoResult(new Ticket(createSixNumbersDto(3, 4, 5, 6, 7, 8)))).isEqualTo(LottoResult.FIRST);
        assertThat(winningNumbers.getLottoResult(new Ticket(createSixNumbersDto(3, 4, 5, 6, 7, 9)))).isEqualTo(LottoResult.SECOND);
        assertThat(winningNumbers.getLottoResult(new Ticket(createSixNumbersDto(3, 4, 5, 6, 7, 10)))).isEqualTo(LottoResult.THIRD);
        assertThat(winningNumbers.getLottoResult(new Ticket(createSixNumbersDto(3, 4, 5, 6, 10, 11)))).isEqualTo(LottoResult.FOURTH);
        assertThat(winningNumbers.getLottoResult(new Ticket(createSixNumbersDto(3, 4, 5, 10, 11, 12)))).isEqualTo(LottoResult.FIFTH);
        assertThat(winningNumbers.getLottoResult(new Ticket(createSixNumbersDto(10, 11, 12, 13, 14, 15)))).isEqualTo(LottoResult.FAILED);
    }

    private LottoNumbersDto createSixNumbersDto(int number1, int number2, int number3, int number4, int number5, int number6) {
        return new LottoNumbersDto(String.format("%d, %d, %d, %d, %d, %d", number1, number2, number3, number4, number5, number6));
    }
}