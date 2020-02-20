package domain;

import domain.numberscontainer.WinningNumbers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import util.LottoNumbersDtoGenerator;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class UserTest {
    @Test
    @DisplayName("당첨 결과 계산")
    void test1() {
        User user = new User();
        Money money = new Money(10000);
        user.buyTicketsManually(money, createMyNumbers());

        Set<Integer> sixNumbers = createSixNumbersSet(3, 4, 5, 6, 7, 8);
        WinningNumbers winningNumbers = new WinningNumbers(LottoNumbersDtoGenerator.generateFixedNumberDto(sixNumbers, 9));
        Map<LottoResult, Long> lottoResults = user.confirmResult(winningNumbers);

        assertThat(lottoResults.get(LottoResult.FIRST)).isEqualTo(1);
        assertThat(lottoResults.get(LottoResult.SECOND)).isEqualTo(2);
        assertThat(lottoResults.get(LottoResult.THIRD)).isEqualTo(2);
        assertThat(lottoResults.get(LottoResult.FOURTH)).isEqualTo(2);
        assertThat(lottoResults.get(LottoResult.FIFTH)).isEqualTo(1);
        assertThat(lottoResults.get(LottoResult.NO_WIN)).isEqualTo(2);
    }

    @Test
    @DisplayName("수익률 계산")
    void test2() {
        User user = new User();
        Money money = new Money(10000);
        user.buyTicketsManually(money, createMyNumbers());

        Set<Integer> sixNumbers = createSixNumbersSet(3, 4, 5, 6, 7, 8);
        WinningNumbers winningNumbers = new WinningNumbers(LottoNumbersDtoGenerator.generateFixedNumberDto(sixNumbers, 9));
        user.confirmResult(winningNumbers);

        assertThat(user.calculateProfit().getProfit()).isEqualTo(20631050);
    }

    private List<Set<Integer>> createMyNumbers() {
        return Arrays.asList(createSixNumbersSet(3, 4, 5, 6, 7, 8),
                createSixNumbersSet(3, 4, 5, 6, 7, 9),
                createSixNumbersSet(3, 4, 5, 6, 7, 10),
                createSixNumbersSet(3, 4, 5, 6, 10, 11),
                createSixNumbersSet(3, 4, 5, 10, 11, 12),
                createSixNumbersSet(4, 5, 6, 7, 8, 9),
                createSixNumbersSet(4, 5, 6, 7, 8, 10),
                createSixNumbersSet(4, 5, 6, 7, 10, 11),
                createSixNumbersSet(10, 11, 12, 13, 14, 15),
                createSixNumbersSet(16, 17, 18, 19, 20, 21));
    }

    private Set<Integer> createSixNumbersSet(int number1, int number2, int number3, int number4, int number5, int number6) {
        return new HashSet<Integer>(Arrays.asList(number1, number2, number3, number4, number5, number6));
    }
}
