package lotto.domain;

import java.util.List;
import lotto.exception.BonusNumberException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class WinningNumbersTest {

    @ParameterizedTest(name = "당첨 번호와 중복되는 보너스볼이 입력되면 예외발생 - case : {0}")
    @ValueSource(ints = {1, 4})
    void generateByString_checkDuplicate(int bonusNumber) {
        Assertions.assertThatThrownBy(() -> WinningNumbers.generate(List.of(1,2,3,4,5,6), bonusNumber))
                .isInstanceOf(BonusNumberException.class)
                .hasMessage("보너스 번호는 당첨 번호와 중복될 수 없습니다.");
    }

    @Test
    @DisplayName("당첨 번호와 보너스 번호를 이용해서 당첨 등급 검색")
    void getRank() {
        WinningNumbers winningNumbers = WinningNumbers.generate(List.of(1,2,3,4,5,6), 7);
        Lotto lotto = Lotto.generateByManual(List.of(1,2,3,4,5,7));
        Assertions.assertThat(winningNumbers.findRankOf(lotto)).isEqualTo(Rank.RANK_2);
    }
}
