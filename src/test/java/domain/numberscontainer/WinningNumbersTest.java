package domain.numberscontainer;

import domain.LottoResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("당첨 번호 객체 테스트")
public class WinningNumbersTest {
    @Test
    @DisplayName("당첨 번호 객체 생성")
    void winningNumberConstructor() {
        SixLottoNumbersDTO sixLottoNumbersDTO = createSixNumbersDto(1, 2, 3, 4, 5, 5);
        BonusNumberDTO bonusNumberDTO = new BonusNumberDTO(LottoNumber.SEVEN);
        assertThatThrownBy(() -> new WinningNumbers(sixLottoNumbersDTO, bonusNumberDTO))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("6개의 숫자를 입력해주세요.");
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 5})
    @DisplayName("중복 없는 보너스 번호인지 검증")
    void validateBonusNumber(int input) {
        SixLottoNumbersDTO sixLottoNumbersDTO = createSixNumbersDto(1, 2, 3, 4, 5, 6);
        BonusNumberDTO bonusNumberDTO = new BonusNumberDTO(LottoNumber.getLottoNumber(input));
        assertThatThrownBy(() -> new WinningNumbers(sixLottoNumbersDTO, bonusNumberDTO))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageEndingWith("보너스 번호를 입력해주세요.");
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 46})
    @DisplayName("1부터 45까지의 보너스 번호인지 검증")
    void validateBonusNumber2(int input) {
        assertThatThrownBy(() -> new BonusNumberDTO(LottoNumber.getLottoNumber(input)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageEndingWith("1부터 45까지의 숫자를 입력해주세요.");
    }

    @Test
    @DisplayName("Ticket과 당첨 번호 비교")
    void findDuplicatedNumbers() {
        SixLottoNumbersDTO sixLottoNumbersDTO1 = createSixNumbersDto(1, 2, 3, 4, 5, 6);
        BonusNumberDTO bonusNumberDTO = new BonusNumberDTO(LottoNumber.SEVEN);
        SixLottoNumbersDTO sixLottoNumbersDTO2 = createSixNumbersDto(4, 5, 6, 7, 8, 9);
        WinningNumbers winningNumbers = new WinningNumbers(sixLottoNumbersDTO1, bonusNumberDTO);
        Ticket ticket = new Ticket(sixLottoNumbersDTO2);
        assertThat((winningNumbers.findDuplicatedNumbers(ticket))).isEqualTo(3);
    }

    private SixLottoNumbersDTO createSixNumbersDto(int number1, int number2, int number3, int number4, int number5, int number6) {
        Set<LottoNumber> sixNumbers = new HashSet<>(Arrays.asList(LottoNumber.getLottoNumber(number1),
                LottoNumber.getLottoNumber(number2),
                LottoNumber.getLottoNumber(number3),
                LottoNumber.getLottoNumber(number4),
                LottoNumber.getLottoNumber(number5),
                LottoNumber.getLottoNumber(number6)));
        return new SixLottoNumbersDTO(sixNumbers);
    }

    @Test
    @DisplayName("당첨 결과 확인")
    void getLottoResultTest() {
        WinningNumbers winningNumbers = new WinningNumbers(createSixNumbersDto(3, 4, 5, 6, 7, 8), new BonusNumberDTO(LottoNumber.NINE));

        assertThat(winningNumbers.getLottoResult(new Ticket(createSixNumbersDto(3, 4, 5, 6, 7, 8)))).isEqualTo(LottoResult.FIRST);
        assertThat(winningNumbers.getLottoResult(new Ticket(createSixNumbersDto(3, 4, 5, 6, 7, 9)))).isEqualTo(LottoResult.SECOND);
        assertThat(winningNumbers.getLottoResult(new Ticket(createSixNumbersDto(3, 4, 5, 6, 7, 10)))).isEqualTo(LottoResult.THIRD);
        assertThat(winningNumbers.getLottoResult(new Ticket(createSixNumbersDto(3, 4, 5, 6, 10, 11)))).isEqualTo(LottoResult.FOURTH);
        assertThat(winningNumbers.getLottoResult(new Ticket(createSixNumbersDto(3, 4, 5, 10, 11, 12)))).isEqualTo(LottoResult.FIFTH);
        assertThat(winningNumbers.getLottoResult(new Ticket(createSixNumbersDto(10, 11, 12, 13, 14, 15)))).isEqualTo(LottoResult.FAILED);
    }
}