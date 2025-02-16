package model.purchase;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import model.draw.BonusNumber;
import model.draw.WinningNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class LottoTest {
    @DisplayName("당첨 번호와 겹치는 로또 번호의 개수를 구한다")
    @ParameterizedTest
    @CsvSource(value = {"1, 7, 8, 9, 10, 11:1", "1, 2, 7, 8, 9, 10:2", "1, 2, 3, 4, 5, 6:6"}, delimiter = ':')
    void findMatchingCountWithLottoNumber(String targetNumberInput, int expectedCount) {
        WinningNumber targetWinningNumber = new WinningNumber(
                Arrays.stream(targetNumberInput.split(", "))
                        .map(Integer::parseInt).toList());
        Lotto lotto = new Lotto(new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6)));
        int matchingCount = lotto.findMatchingCountWith(targetWinningNumber);

        assertThat(matchingCount).isEqualTo(expectedCount);
    }

    @DisplayName("보너스 번호와 로또 번호의 일치 여부를 구한다")
    @ParameterizedTest
    @CsvSource(value = {"1:true", "7:false"}, delimiter = ':')
    void findMatchesWithBonusNumber(int targetNumberInput, boolean expected) {
        //TODO : 세팅이 불편하다.
        WinningNumber defaultWinningNumber = new WinningNumber(new ArrayList<>(Arrays.asList(11, 12, 13, 14, 15, 16)));
        BonusNumber targetBonusNumber = new BonusNumber(targetNumberInput, defaultWinningNumber);

        Lotto lotto = new Lotto(new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6)));

        assertThat(lotto.contains(targetBonusNumber)).isEqualTo(expected);
    }
}
