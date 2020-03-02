package domain;

import domain.numberscontainer.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoRankMachineTest {
    @Test
    @DisplayName("당첨 결과 계산")
    void test1() {
        Tickets tickets = Tickets.createTickets(6,6, createFixedNumbers());
        WinningNumbers winningNumbers = new WinningNumbers("3, 4, 5, 6, 7, 8", 9);
        LottoResult lottoResult = LottoResultMachine.calculateResult(tickets, winningNumbers);

        assertThat(lottoResult.count(LottoRank.FIRST)).isEqualTo(1);
        assertThat(lottoResult.count(LottoRank.SECOND)).isEqualTo(1);
        assertThat(lottoResult.count(LottoRank.THIRD)).isEqualTo(1);
        assertThat(lottoResult.count(LottoRank.FOURTH)).isEqualTo(1);
        assertThat(lottoResult.count(LottoRank.FIFTH)).isEqualTo(1);
        assertThat(lottoResult.count(LottoRank.FAILED)).isEqualTo(1);
    }

    @Test
    @DisplayName("수익률 계산")
    void test2() {
        Money money = new Money("6000");
        Tickets tickets = Tickets.createTickets(6, 6, createFixedNumbers());
        WinningNumbers winningNumbers = new WinningNumbers("3, 4, 5, 6, 7, 8", 9);

        LottoResult lottoResult = LottoResultMachine.calculateResult(tickets, winningNumbers);
        LottoProfit profit = LottoProfit.ofProfit(lottoResult, money);
        assertThat(profit.getValue()).isEqualTo(33859200);
    }

    private List<String> createFixedNumbers() {
        return Arrays.asList("3, 4, 5, 6, 7, 8",  //1등
                "3, 4, 5, 6, 7, 9",               //2등
                "3, 4, 5, 6, 7, 10",              //3등
                "3, 4, 5, 6, 10, 11",             //4등
                "3, 4, 5, 10, 11, 12",            //5등
                "10, 11, 12, 13, 14, 15");         //당첨 x
    }
}