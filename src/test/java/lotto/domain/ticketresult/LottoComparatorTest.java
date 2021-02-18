package lotto.domain.ticketresult;


import static lotto.type.LottoMatchType.FIVE_AND_BONUS_MATCH;
import static lotto.type.LottoMatchType.FIVE_MATCH;
import static lotto.type.LottoMatchType.FOUR_MATCH;
import static lotto.type.LottoMatchType.SIX_MATCH;
import static lotto.type.LottoMatchType.THREE_MATCH;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import lotto.domain.LottoNumber;
import lotto.domain.LottoTicket;
import lotto.domain.ticketpurchase.PurchasedLottoTickets;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
        PurchasedLottoTickets purchasedLottoTickets = new PurchasedLottoTickets();
        purchasedLottoTickets.add(purchasedLottoTicket);

        LottoComparator lottoComparator = new LottoComparator(winningLottoNumbers);
        LottoResult lottoResult = lottoComparator.getLottoResult(purchasedLottoTickets);

        assertThat(lottoResult.getCountOfMatchedNumbersOfSpecificType(THREE_MATCH))
            .isEqualTo(0);
        assertThat(lottoResult.getCountOfMatchedNumbersOfSpecificType(FOUR_MATCH))
            .isEqualTo(0);
        assertThat(lottoResult.getCountOfMatchedNumbersOfSpecificType(FIVE_MATCH))
            .isEqualTo(0);
        assertThat(lottoResult.getCountOfMatchedNumbersOfSpecificType(FIVE_AND_BONUS_MATCH))
            .isEqualTo(0);
        assertThat(lottoResult.getCountOfMatchedNumbersOfSpecificType(SIX_MATCH))
            .isEqualTo(1);
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
        PurchasedLottoTickets purchasedLottoTickets = new PurchasedLottoTickets();
        purchasedLottoTickets.add(purchasedLottoTicket);

        LottoComparator lottoComparator = new LottoComparator(winningLottoNumbers);
        LottoResult lottoResult = lottoComparator.getLottoResult(purchasedLottoTickets);

        assertThat(lottoResult.getCountOfMatchedNumbersOfSpecificType(THREE_MATCH))
            .isEqualTo(0);
        assertThat(lottoResult.getCountOfMatchedNumbersOfSpecificType(FOUR_MATCH))
            .isEqualTo(0);
        assertThat(lottoResult.getCountOfMatchedNumbersOfSpecificType(FIVE_MATCH))
            .isEqualTo(0);
        assertThat(lottoResult.getCountOfMatchedNumbersOfSpecificType(FIVE_AND_BONUS_MATCH))
            .isEqualTo(1);
        assertThat(lottoResult.getCountOfMatchedNumbersOfSpecificType(SIX_MATCH))
            .isEqualTo(0);
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
        PurchasedLottoTickets purchasedLottoTickets = new PurchasedLottoTickets();
        purchasedLottoTickets.add(purchasedLottoTicket);

        LottoComparator lottoComparator = new LottoComparator(winningLottoNumbers);
        LottoResult lottoResult = lottoComparator.getLottoResult(purchasedLottoTickets);

        assertThat(lottoResult.getCountOfMatchedNumbersOfSpecificType(THREE_MATCH))
            .isEqualTo(0);
        assertThat(lottoResult.getCountOfMatchedNumbersOfSpecificType(FOUR_MATCH))
            .isEqualTo(0);
        assertThat(lottoResult.getCountOfMatchedNumbersOfSpecificType(FIVE_MATCH))
            .isEqualTo(1);
        assertThat(lottoResult.getCountOfMatchedNumbersOfSpecificType(FIVE_AND_BONUS_MATCH))
            .isEqualTo(0);
        assertThat(lottoResult.getCountOfMatchedNumbersOfSpecificType(SIX_MATCH))
            .isEqualTo(0);
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
        PurchasedLottoTickets purchasedLottoTickets = new PurchasedLottoTickets();
        purchasedLottoTickets.add(purchasedLottoTicket);

        LottoComparator lottoComparator = new LottoComparator(winningLottoNumbers);
        LottoResult lottoResult = lottoComparator.getLottoResult(purchasedLottoTickets);

        assertThat(lottoResult.getCountOfMatchedNumbersOfSpecificType(THREE_MATCH))
            .isEqualTo(0);
        assertThat(lottoResult.getCountOfMatchedNumbersOfSpecificType(FOUR_MATCH))
            .isEqualTo(1);
        assertThat(lottoResult.getCountOfMatchedNumbersOfSpecificType(FIVE_MATCH))
            .isEqualTo(0);
        assertThat(lottoResult.getCountOfMatchedNumbersOfSpecificType(FIVE_AND_BONUS_MATCH))
            .isEqualTo(0);
        assertThat(lottoResult.getCountOfMatchedNumbersOfSpecificType(SIX_MATCH))
            .isEqualTo(0);
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
        PurchasedLottoTickets purchasedLottoTickets = new PurchasedLottoTickets();
        purchasedLottoTickets.add(purchasedLottoTicket);

        LottoComparator lottoComparator = new LottoComparator(winningLottoNumbers);
        LottoResult lottoResult = lottoComparator.getLottoResult(purchasedLottoTickets);

        assertThat(lottoResult.getCountOfMatchedNumbersOfSpecificType(THREE_MATCH))
            .isEqualTo(1);
        assertThat(lottoResult.getCountOfMatchedNumbersOfSpecificType(FOUR_MATCH))
            .isEqualTo(0);
        assertThat(lottoResult.getCountOfMatchedNumbersOfSpecificType(FIVE_MATCH))
            .isEqualTo(0);
        assertThat(lottoResult.getCountOfMatchedNumbersOfSpecificType(FIVE_AND_BONUS_MATCH))
            .isEqualTo(0);
        assertThat(lottoResult.getCountOfMatchedNumbersOfSpecificType(SIX_MATCH))
            .isEqualTo(0);
    }
}
