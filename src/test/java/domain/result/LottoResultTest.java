package domain.result;

import domain.buyingstrategy.buyinginformation.Money;
import domain.lottonumbers.LottoTicket;
import domain.lottonumbers.WinningNumbers;
import domain.lottonumbers.lottonumber.LottoNumber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.*;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("최종결과 테스트")
class LottoResultTest {

    private List<LottoTicket> testTickets;
    private WinningNumbers winningNumbers;

    @BeforeEach
    void setting() {
        this.testTickets = Arrays.asList(generateLottoTicket(3, 4, 5, 6, 7, 8),
                generateLottoTicket(3, 4, 5, 6, 7, 9),
                generateLottoTicket(3, 4, 5, 6, 7, 10),
                generateLottoTicket(3, 4, 5, 6, 10, 11),
                generateLottoTicket(3, 4, 5, 10, 11, 12),
                generateLottoTicket(4, 5, 6, 7, 8, 9),
                generateLottoTicket(4, 5, 6, 7, 8, 10),
                generateLottoTicket(4, 5, 6, 7, 10, 11),
                generateLottoTicket(10, 11, 12, 13, 14, 15),
                generateLottoTicket(16, 17, 18, 19, 20, 21));

        this.winningNumbers = generateWinningNumbers(3,4,5,6,7,8,9);
    }

    @Test
    @DisplayName("당첨 결과 전체 확인")
    void test1() {
        LottoResult lottoResult = LottoResult.confirmResult(this.testTickets, this.winningNumbers);

        assertThat(lottoResult.findNumberOf(LottoRank.FIRST)).isEqualTo(1);
        assertThat(lottoResult.findNumberOf(LottoRank.SECOND)).isEqualTo(2);
        assertThat(lottoResult.findNumberOf(LottoRank.THIRD)).isEqualTo(2);
        assertThat(lottoResult.findNumberOf(LottoRank.FOURTH)).isEqualTo(2);
        assertThat(lottoResult.findNumberOf(LottoRank.FIFTH)).isEqualTo(1);
        assertThat(lottoResult.findNumberOf(LottoRank.NO_WIN)).isEqualTo(2);
    }

    @Test
    @DisplayName("수익률 계산 확인")
    void test2() {
        Money money = new Money(10000L);
        LottoResult lottoResult = LottoResult.confirmResult(this.testTickets, this.winningNumbers);

        assertThat(lottoResult.calculateProfit(money)).isEqualTo(20631050D);
    }

    WinningNumbers generateWinningNumbers(int number1, int number2, int number3, int number4, int number5, int number6, int bonus) {
        return new WinningNumbers(generateLottoTicket(number1, number2, number3, number4, number5, number6), LottoNumber.of(bonus));
    }

    LottoTicket generateLottoTicket(int number1, int number2, int number3, int number4, int number5, int number6) {
        List<Integer> integers = Arrays.asList(number1, number2, number3, number4, number5, number6);

        return integers.stream()
                .map(LottoNumber::of)
                .collect(collectingAndThen(toSet(), LottoTicket::new));
    }
}