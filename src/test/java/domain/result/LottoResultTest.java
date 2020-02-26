package domain.result;

import domain.Money;
import domain.lottonumbers.LottoTicket;
import domain.lottonumbers.LottoTicketDto;
import domain.lottonumbers.WinningNumbers;
import domain.lottonumbers.WinningNumbersDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("최종결과 테스트")
class LottoResultTest {

    private List<LottoTicket> testTickets;
    private WinningNumbers winningNumbers;

    @BeforeEach
    void setting() {
        List<Set<Integer>> testNumbers = Arrays.asList(createSixNumbersSet(3, 4, 5, 6, 7, 8),
                createSixNumbersSet(3, 4, 5, 6, 7, 9),
                createSixNumbersSet(3, 4, 5, 6, 7, 10),
                createSixNumbersSet(3, 4, 5, 6, 10, 11),
                createSixNumbersSet(3, 4, 5, 10, 11, 12),
                createSixNumbersSet(4, 5, 6, 7, 8, 9),
                createSixNumbersSet(4, 5, 6, 7, 8, 10),
                createSixNumbersSet(4, 5, 6, 7, 10, 11),
                createSixNumbersSet(10, 11, 12, 13, 14, 15),
                createSixNumbersSet(16, 17, 18, 19, 20, 21));

        this.testTickets = testNumbers.stream()
                .map(LottoTicketDto::new)
                .map(LottoTicket::new)
                .collect(toList());

        Set<Integer> sixNumbers = createSixNumbersSet(3, 4, 5, 6, 7, 8);
        this.winningNumbers = new WinningNumbers(new WinningNumbersDto(sixNumbers, 9));
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

    private Set<Integer> createSixNumbersSet(int number1, int number2, int number3, int number4, int number5, int number6) {
        return new HashSet<Integer>(Arrays.asList(number1, number2, number3, number4, number5, number6));
    }
}