package lotto.domain.ticketresult;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import lotto.domain.LottoNumber;
import lotto.domain.LottoTicket;
import lotto.domain.LottoTickets;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static lotto.domain.ticketresult.Rank.*;

public class LottoComparatorTest {
    private WinningLottoNumbers winningLottoNumbers;

    @BeforeEach
    void setWinningLottoNumbers() {
        LottoTicket winnerTicket = new LottoTicket(
            Arrays.asList(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(6)
            )
        );
        LottoNumber bonusNumber = new LottoNumber(7);
        winningLottoNumbers = new WinningLottoNumbers(winnerTicket, bonusNumber);
    }

    @DisplayName("1등 당첨 - 6개 일치")
    @Test
    void Should_Return_Result_When_SixNumbersMatched() {
        LottoTicket purchasedLottoTicket = new LottoTicket(Arrays.asList(
            new LottoNumber(1),
            new LottoNumber(2),
            new LottoNumber(3),
            new LottoNumber(4),
            new LottoNumber(5),
            new LottoNumber(6)
        ));

        List<LottoTicket> lottoTickets = new ArrayList<>(Arrays.asList(purchasedLottoTicket));
        LottoTickets purchasedLottoTickets = new LottoTickets(lottoTickets);

        LottoComparator lottoComparator = new LottoComparator(winningLottoNumbers);
        Map<Rank, Integer> lottoResult
            = lottoComparator.getLottoResult(purchasedLottoTickets);

        Assertions.assertThat(lottoResult.get(FIFTH)).isEqualTo(0);
        Assertions.assertThat(lottoResult.get(FOURTH)).isEqualTo(0);
        Assertions.assertThat(lottoResult.get(THIRD)).isEqualTo(0);
        Assertions.assertThat(lottoResult.get(SECOND)).isEqualTo(0);
        Assertions.assertThat(lottoResult.get(FIRST)).isEqualTo(1);
    }


    @DisplayName("2등 당첨 - 5개, 보너스 번호 일치")
    @Test
    void Should_Return_Result_When_FiveNumbersAndBonusNumberMatched() {
        LottoTicket purchasedLottoTicket = new LottoTicket(Arrays.asList(
            new LottoNumber(1),
            new LottoNumber(2),
            new LottoNumber(3),
            new LottoNumber(4),
            new LottoNumber(5),
            new LottoNumber(7)
        ));

        List<LottoTicket> lottoTickets = new ArrayList<>(Arrays.asList(purchasedLottoTicket));
        LottoTickets purchasedLottoTickets = new LottoTickets(lottoTickets);

        LottoComparator lottoComparator = new LottoComparator(winningLottoNumbers);
        Map<Rank, Integer> lottoResult
            = lottoComparator.getLottoResult(purchasedLottoTickets);

        Assertions.assertThat(lottoResult.get(FIFTH)).isEqualTo(0);
        Assertions.assertThat(lottoResult.get(FOURTH)).isEqualTo(0);
        Assertions.assertThat(lottoResult.get(THIRD)).isEqualTo(0);
        Assertions.assertThat(lottoResult.get(SECOND)).isEqualTo(1);
        Assertions.assertThat(lottoResult.get(FIRST)).isEqualTo(0);
    }


    @DisplayName("3등 당첨 - 5개 일치")
    @Test
    void Should_Return_Result_When_FiveNumbersMatched() {
        LottoTicket purchasedLottoTicket = new LottoTicket(Arrays.asList(
            new LottoNumber(1),
            new LottoNumber(2),
            new LottoNumber(3),
            new LottoNumber(4),
            new LottoNumber(5),
            new LottoNumber(10)
        ));

        List<LottoTicket> lottoTickets = new ArrayList<>(Arrays.asList(purchasedLottoTicket));
        LottoTickets purchasedLottoTickets = new LottoTickets(lottoTickets);

        LottoComparator lottoComparator = new LottoComparator(winningLottoNumbers);
        Map<Rank, Integer> lottoResult
            = lottoComparator.getLottoResult(purchasedLottoTickets);

        Assertions.assertThat(lottoResult.get(FIFTH)).isEqualTo(0);
        Assertions.assertThat(lottoResult.get(FOURTH)).isEqualTo(0);
        Assertions.assertThat(lottoResult.get(THIRD)).isEqualTo(1);
        Assertions.assertThat(lottoResult.get(SECOND)).isEqualTo(0);
        Assertions.assertThat(lottoResult.get(FIRST)).isEqualTo(0);
    }


    @DisplayName("4등 당첨 - 4개 일치")
    @Test
    void Should_Return_Result_When_FourNumbersMatched() {
        LottoTicket purchasedLottoTicket = new LottoTicket(Arrays.asList(
            new LottoNumber(1),
            new LottoNumber(2),
            new LottoNumber(3),
            new LottoNumber(4),
            new LottoNumber(9),
            new LottoNumber(10)
        ));

        List<LottoTicket> lottoTickets = new ArrayList<>(Arrays.asList(purchasedLottoTicket));
        LottoTickets purchasedLottoTickets = new LottoTickets(lottoTickets);

        LottoComparator lottoComparator = new LottoComparator(winningLottoNumbers);
        Map<Rank, Integer> lottoResult
            = lottoComparator.getLottoResult(purchasedLottoTickets);

        Assertions.assertThat(lottoResult.get(FIFTH)).isEqualTo(0);
        Assertions.assertThat(lottoResult.get(FOURTH)).isEqualTo(1);
        Assertions.assertThat(lottoResult.get(THIRD)).isEqualTo(0);
        Assertions.assertThat(lottoResult.get(SECOND)).isEqualTo(0);
        Assertions.assertThat(lottoResult.get(FIRST)).isEqualTo(0);
    }


    @DisplayName("5등 당첨 - 3개 일치")
    @Test
    void Should_Return_Result_When_ThreeNumbersMatched() {
        LottoTicket purchasedLottoTicket = new LottoTicket(Arrays.asList(
            new LottoNumber(1),
            new LottoNumber(2),
            new LottoNumber(3),
            new LottoNumber(8),
            new LottoNumber(9),
            new LottoNumber(10)
        ));

        List<LottoTicket> lottoTickets = new ArrayList<>(Arrays.asList(purchasedLottoTicket));
        LottoTickets purchasedLottoTickets = new LottoTickets(lottoTickets);

        LottoComparator lottoComparator = new LottoComparator(winningLottoNumbers);
        Map<Rank, Integer> lottoResult
            = lottoComparator.getLottoResult(purchasedLottoTickets);

        Assertions.assertThat(lottoResult.get(FIFTH)).isEqualTo(1);
        Assertions.assertThat(lottoResult.get(FOURTH)).isEqualTo(0);
        Assertions.assertThat(lottoResult.get(THIRD)).isEqualTo(0);
        Assertions.assertThat(lottoResult.get(SECOND)).isEqualTo(0);
        Assertions.assertThat(lottoResult.get(FIRST)).isEqualTo(0);
    }
}
