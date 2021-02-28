package lotto.domain.ticketresult;


import lotto.domain.ticket.AutoTickets;
import lotto.domain.ticket.LottoNumber;
import lotto.domain.ticket.LottoTicket;
import lotto.domain.ticket.ManualTickets;
import lotto.domain.ticketpurchase.PurchasedTickets;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Map;

import static lotto.domain.ticketresult.Rank.*;

public class LottoComparatorTest {
    private WinningLottoNumbers winningLottoNumbers;
    private ManualTickets dummy;

    @BeforeEach
    void setWinningLottoNumbersAndDummyTicket() {
        LottoTicket winnerTicket = new LottoTicket(
                Arrays.asList(
                        LottoNumber.of(1),
                        LottoNumber.of(2),
                        LottoNumber.of(3),
                        LottoNumber.of(4),
                        LottoNumber.of(5),
                        LottoNumber.of(6)
                )
        );

        LottoTicket ticket = new LottoTicket(Arrays.asList(
                LottoNumber.of(31),
                LottoNumber.of(32),
                LottoNumber.of(33),
                LottoNumber.of(34),
                LottoNumber.of(35),
                LottoNumber.of(36)
        ));

        dummy = new ManualTickets(Arrays.asList(ticket));
        LottoNumber bonusNumber = LottoNumber.of(7);
        winningLottoNumbers = new WinningLottoNumbers(winnerTicket, bonusNumber);
    }

    @DisplayName("1등 당첨 - 6개 일치")
    @Test
    void Should_Return_Result_When_SixNumbersMatched() {
        LottoTicket ticket = new LottoTicket(Arrays.asList(
                LottoNumber.of(1),
                LottoNumber.of(2),
                LottoNumber.of(3),
                LottoNumber.of(4),
                LottoNumber.of(5),
                LottoNumber.of(6)
        ));

        AutoTickets tickets = new AutoTickets(Arrays.asList(ticket));
        PurchasedTickets purchasedTickets = new PurchasedTickets(dummy, tickets);

        LottoComparator lottoComparator = new LottoComparator(winningLottoNumbers);
        Map<Rank, Integer> lottoResult = lottoComparator.getLottoResult(purchasedTickets);

        Assertions.assertThat(lottoResult.get(FIFTH)).isZero();
        Assertions.assertThat(lottoResult.get(FOURTH)).isZero();
        Assertions.assertThat(lottoResult.get(THIRD)).isZero();
        Assertions.assertThat(lottoResult.get(SECOND)).isZero();
        Assertions.assertThat(lottoResult.get(FIRST)).isEqualTo(1);
    }

    @DisplayName("2등 당첨 - 5개, 보너스 번호 일치")
    @Test
    void Should_Return_Result_When_FiveNumbersAndBonusNumberMatched() {
        LottoTicket ticket = new LottoTicket(Arrays.asList(
                LottoNumber.of(1),
                LottoNumber.of(2),
                LottoNumber.of(3),
                LottoNumber.of(4),
                LottoNumber.of(5),
                LottoNumber.of(7)
        ));

        AutoTickets tickets = new AutoTickets(Arrays.asList(ticket));
        PurchasedTickets purchasedTickets = new PurchasedTickets(dummy, tickets);

        LottoComparator lottoComparator = new LottoComparator(winningLottoNumbers);
        Map<Rank, Integer> lottoResult = lottoComparator.getLottoResult(purchasedTickets);

        Assertions.assertThat(lottoResult.get(FIFTH)).isZero();
        Assertions.assertThat(lottoResult.get(FOURTH)).isZero();
        Assertions.assertThat(lottoResult.get(THIRD)).isZero();
        Assertions.assertThat(lottoResult.get(SECOND)).isEqualTo(1);
        Assertions.assertThat(lottoResult.get(FIRST)).isZero();
    }

    @DisplayName("3등 당첨 - 5개 일치")
    @Test
    void Should_Return_Result_When_FiveNumbersMatched() {
        LottoTicket ticket = new LottoTicket(Arrays.asList(
                LottoNumber.of(1),
                LottoNumber.of(2),
                LottoNumber.of(3),
                LottoNumber.of(4),
                LottoNumber.of(5),
                LottoNumber.of(10)
        ));

        AutoTickets tickets = new AutoTickets(Arrays.asList(ticket));
        PurchasedTickets purchasedTickets = new PurchasedTickets(dummy, tickets);
        LottoComparator lottoComparator = new LottoComparator(winningLottoNumbers);
        Map<Rank, Integer> lottoResult = lottoComparator.getLottoResult(purchasedTickets);

        Assertions.assertThat(lottoResult.get(FIFTH)).isZero();
        Assertions.assertThat(lottoResult.get(FOURTH)).isZero();
        Assertions.assertThat(lottoResult.get(THIRD)).isEqualTo(1);
        Assertions.assertThat(lottoResult.get(SECOND)).isZero();
        Assertions.assertThat(lottoResult.get(FIRST)).isZero();
    }

    @DisplayName("4등 당첨 - 4개 일치")
    @Test
    void Should_Return_Result_When_FourNumbersMatched() {
        LottoTicket ticket = new LottoTicket(Arrays.asList(
                LottoNumber.of(1),
                LottoNumber.of(2),
                LottoNumber.of(3),
                LottoNumber.of(4),
                LottoNumber.of(9),
                LottoNumber.of(10)
        ));

        AutoTickets tickets = new AutoTickets(Arrays.asList(ticket));
        PurchasedTickets purchasedTickets = new PurchasedTickets(dummy, tickets);
        LottoComparator lottoComparator = new LottoComparator(winningLottoNumbers);
        Map<Rank, Integer> lottoResult = lottoComparator.getLottoResult(purchasedTickets);

        Assertions.assertThat(lottoResult.get(FIFTH)).isZero();
        Assertions.assertThat(lottoResult.get(FOURTH)).isEqualTo(1);
        Assertions.assertThat(lottoResult.get(THIRD)).isZero();
        Assertions.assertThat(lottoResult.get(SECOND)).isZero();
        Assertions.assertThat(lottoResult.get(FIRST)).isZero();
    }

    @DisplayName("5등 당첨 - 3개 일치")
    @Test
    void Should_Return_Result_When_ThreeNumbersMatched() {
        LottoTicket ticket = new LottoTicket(Arrays.asList(
                LottoNumber.of(1),
                LottoNumber.of(2),
                LottoNumber.of(3),
                LottoNumber.of(8),
                LottoNumber.of(9),
                LottoNumber.of(10)
        ));

        AutoTickets tickets = new AutoTickets(Arrays.asList(ticket));
        PurchasedTickets purchasedTickets = new PurchasedTickets(dummy, tickets);
        LottoComparator lottoComparator = new LottoComparator(winningLottoNumbers);
        Map<Rank, Integer> lottoResult = lottoComparator.getLottoResult(purchasedTickets);

        Assertions.assertThat(lottoResult.get(FIFTH)).isEqualTo(1);
        Assertions.assertThat(lottoResult.get(FOURTH)).isZero();
        Assertions.assertThat(lottoResult.get(THIRD)).isZero();
        Assertions.assertThat(lottoResult.get(SECOND)).isZero();
        Assertions.assertThat(lottoResult.get(FIRST)).isZero();
    }
}
