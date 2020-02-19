package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("당첨 번호 객체 테스트")
public class WinningNumbersTest {
    @Test
    @DisplayName("당첨 번호 객체 생성")
    void winningNumberConstructor() {
        LottoNumbersDto lottoNumbersDto = createLottoNumberDto(1, 2, 3, 4, 5, 5, 7);
        assertThatThrownBy(() -> new WinningNumbers(lottoNumbersDto))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("6개의 숫자를 입력해주세요.");
    }

    private LottoNumbersDto createLottoNumberDto(int number1, int number2, int number3, int number4, int number5, int number6, int number7) {
        Set<LottoNumber> sixNumbers = new HashSet<>(Arrays.asList(LottoNumber.getLottoNumber(number1),
                LottoNumber.getLottoNumber(number2),
                LottoNumber.getLottoNumber(number3),
                LottoNumber.getLottoNumber(number4),
                LottoNumber.getLottoNumber(number5),
                LottoNumber.getLottoNumber(number6)));
        LottoNumber bonusNumber = LottoNumber.getLottoNumber(number7);

        return new LottoNumbersDto(sixNumbers, bonusNumber);
    }
}
