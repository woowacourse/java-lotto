package domain;

import domain.numberscontainer.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoResultMachineTest {
    @Test
    @DisplayName("당첨 결과 계산")
    void test1() {
        Money money = new Money("10000");
        List<Ticket> tickets = LottoStore.createTickets(money, 10, createFixedNumbersList());

        LottoNumbersDto lottoNumbers = new LottoNumbersDto(createFixedNumbers(3, 4, 5, 6, 7, 8));
        WinningNumbers winningNumbers = new WinningNumbers(lottoNumbers, new BonusNumberDto("9"));
        Map<LottoResult, Integer> lottoResults = LottoResultMachine.calculateResult(tickets, winningNumbers);

        assertThat(lottoResults.get(LottoResult.FIRST)).isEqualTo(1);
        assertThat(lottoResults.get(LottoResult.SECOND)).isEqualTo(2);
        assertThat(lottoResults.get(LottoResult.THIRD)).isEqualTo(2);
        assertThat(lottoResults.get(LottoResult.FOURTH)).isEqualTo(2);
        assertThat(lottoResults.get(LottoResult.FIFTH)).isEqualTo(1);
        assertThat(lottoResults.get(LottoResult.FAILED)).isEqualTo(2);
    }

    @Test
    @DisplayName("수익률 계산")
    void test2() {
        Money money = new Money("10000");
        List<Ticket> tickets = LottoStore.createTickets(money, 10, createFixedNumbersList());

        LottoNumbersDto lottoNumbers = new LottoNumbersDto(createFixedNumbers(3, 4, 5, 6, 7, 8));
        WinningNumbers winningNumbers = new WinningNumbers(lottoNumbers, new BonusNumberDto("9"));
        Map<LottoResult, Integer> lottoResults = LottoResultMachine.calculateResult(tickets, winningNumbers);
        LottoProfit profit = LottoProfit.ofProfit(lottoResults, money);
        assertThat(profit.getValue()).isEqualTo(20631000);
    }

    private List<String> createFixedNumbersList() {
        return Arrays.asList(createFixedNumbers(3, 4, 5, 6, 7, 8),  //1등
                createFixedNumbers(3, 4, 5, 6, 7, 9),               //2등
                createFixedNumbers(3, 4, 5, 6, 7, 10),              //3등
                createFixedNumbers(3, 4, 5, 6, 10, 11),             //4등
                createFixedNumbers(3, 4, 5, 10, 11, 12),            //5등
                createFixedNumbers(4, 5, 6, 7, 8, 9),               //2등
                createFixedNumbers(4, 5, 6, 7, 8, 10),              //3등
                createFixedNumbers(4, 5, 6, 7, 10, 11),             //4등
                createFixedNumbers(10, 11, 12, 13, 14, 15),         //당첨 x
                createFixedNumbers(16, 17, 18, 19, 20, 21));        //당첨 x
    }

    private String createFixedNumbers(int number1, int number2, int number3, int number4, int number5, int number6) {
        return String.format("%d, %d, %d, %d, %d, %d", number1, number2, number3, number4, number5, number6);
    }
}
