package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static lotto.view.InputView.LOTTO_NUMBERS_DELIMITER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class WinningLottoTest {

    @DisplayName("당첨 번호와 중복되는 보너스 볼 입력 시 예외가 발생하는지 확인")
    @ParameterizedTest
    @ValueSource(strings = {"1", "2", "3", "4", "5", "6"})
    void validateDuplicatedNumberTest(int input) {
        String[] numbers = {"1", "2", "3", "4", "5", "6"};
        Lotto winningLottoLine = Lotto.from(numbers);
        LottoNumber bonusNumber = LottoNumber.getInstance(input);

        assertThatThrownBy(() -> new WinningLotto(winningLottoLine, bonusNumber))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("당첨 번호와 중복되는 보너스 볼입니다.");
    }

    @DisplayName("로또 번호와 당첨 번호가 일치하는 숫자의 개수에 맞는 당첨 결과가 있는지 확인하는 기능 테스트")
    @ParameterizedTest
    @CsvSource(value = {
            "7, 8, 9, 10, 11, 12:1, 2, 3, 4, 5, 6:7:false",
            "6, 7, 8, 9, 10, 11:1, 2, 3, 4, 5, 6:7:false",
            "5, 6, 7, 8, 9, 10:1, 2, 3, 4, 5, 6:7:false",
            "4, 5, 6, 7, 8, 9:1, 2, 3, 4, 5, 6:7:true",
            "3, 4, 5, 6, 7, 8:1, 2, 3, 4, 5, 6:7:true",
            "2, 3, 4, 5, 6, 8:1, 2, 3, 4, 5, 6:7:true",
            "2, 3, 4, 5, 6, 7:1, 2, 3, 4, 5, 6:7:true",
            "1, 2, 3, 4, 5, 6:1, 2, 3, 4, 5, 6:7:true"
    }, delimiter = ':')
    void hasMatchResultTest(String input1, String input2, int input3, boolean expected) {
        Lotto lotto = Lotto.from(input1.split(LOTTO_NUMBERS_DELIMITER));
        Lotto winningLottoLine = Lotto.from(input2.split(LOTTO_NUMBERS_DELIMITER));
        LottoNumber bonusNumber = LottoNumber.getInstance(input3);
        WinningLotto winningLotto = new WinningLotto(winningLottoLine, bonusNumber);

        boolean result = winningLotto.hasMatchResult(lotto);
        assertThat(result).isEqualTo(expected);
    }

    @DisplayName("로또 번호와 당첨 번호가 일치하는 숫자의 개수에 따라 올바른 결과값 상금을 만드는지 확인")
    @ParameterizedTest
    @CsvSource(value = {
            "4, 5, 6, 7, 8, 9:1, 2, 3, 4, 5, 6:7:5000",
            "3, 4, 5, 6, 7, 8:1, 2, 3, 4, 5, 6:7:50000",
            "2, 3, 4, 5, 6, 8:1, 2, 3, 4, 5, 6:7:1500000",
            "2, 3, 4, 5, 6, 7:1, 2, 3, 4, 5, 6:7:30000000",
            "1, 2, 3, 4, 5, 6:1, 2, 3, 4, 5, 6:7:2000000000"
    }, delimiter = ':')
    void createResultTest(String input1, String input2, String input3, int expected) {
        Lotto lotto = Lotto.from(input1.split(LOTTO_NUMBERS_DELIMITER));
        Lotto winningLottoLine = Lotto.from(input2.split(LOTTO_NUMBERS_DELIMITER));
        LottoNumber bonusNumber = LottoNumber.getInstance(input3);
        WinningLotto winningLotto = new WinningLotto(winningLottoLine, bonusNumber);

        MatchResult matchResult = winningLotto.createResult(lotto);
        assertThat(matchResult.getPrize()).isEqualTo(expected);
    }
}
