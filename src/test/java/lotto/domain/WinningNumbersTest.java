package lotto.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lotto.exception.BonusNumberException;
import lotto.util.InputConvertor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class WinningNumbersTest {

    private List<LottoNumber> lotto;

    @BeforeEach
    void setUp() {
        lotto = Stream.of(11, 12, 13, 14, 15, 16)
                .map(LottoNumber::getByNumber)
                .collect(Collectors.toList());
    }

    @ParameterizedTest(name = "당첨 번호와 중복되는 보너스볼이 입력되면 예외발생 - case : {0}")
    @ValueSource(ints = {1, 45})
    void checkDuplication(int bonusNumber) {
        List<Integer> winningLotto = new ArrayList<>(Arrays.asList(1, 4, 10, 20, 30, 45));
        Assertions.assertThatThrownBy(() -> new WinningNumbers(winningLotto, bonusNumber))
                .isInstanceOf(BonusNumberException.class)
                .hasMessage("보너스 번호는 당첨 번호와 중복될 수 없습니다.");
    }

    @ParameterizedTest(name = "당첨 번호와 일치하는 티켓의 번호 개수 반환 - case : count {1}")
    @CsvSource(value = {"11,12,13,14,15,16:6", "11,12,13,7,8,9:3", "1,2,3,4,5,6:0"}, delimiter = ':')
    void getWinningNumberMatchCount(String winningLottoInput, int expectedCount) {
        List<Integer> winningLotto = InputConvertor.toInt(InputConvertor.splitInput(winningLottoInput, ","));
        WinningNumbers winningNumbers = new WinningNumbers(winningLotto, 45);
        Assertions.assertThat(winningNumbers.getWinningLottoMatchCount(lotto))
                .isEqualTo(expectedCount);
    }

    @ParameterizedTest(name = "보너스 번호가 포함 되는지 판별 - case : {1}")
    @CsvSource(value = {"16,true", "45,false"})
    void isBonusNumberContainedAt(int bonusNumber, boolean expected) {
        WinningNumbers winningNumbers = new WinningNumbers(new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6)),
                bonusNumber);
        Assertions.assertThat(winningNumbers.isBonusNumberContainedAt(lotto))
                .isEqualTo(expected);
    }
}
