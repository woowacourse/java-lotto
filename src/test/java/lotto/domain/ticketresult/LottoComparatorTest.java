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
import lotto.domain.ticketpurchase.LottoTickets;
import lotto.domain.ticketpurchase.UserPurchase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoComparatorTest {
    private WinningTicketAndBonusNumber winningLottoNumbers;
    private static final int PURCHASE_PRICE = 10000;
    private final UserPurchase userPurchase = new UserPurchase(PURCHASE_PRICE);

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
        winningLottoNumbers = new WinningTicketAndBonusNumber(winnerTicket, bonusNumber);
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
        LottoTickets purchasedLottoTickets = new LottoTickets();
        purchasedLottoTickets.add(purchasedLottoTicket);

        LottoComparator lottoComparator = new LottoComparator(winningLottoNumbers, userPurchase);
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

        assertThat(lottoResult.getProfit())
            .isEqualTo((double) SIX_MATCH.getPrizeMoney() / (double) PURCHASE_PRICE);
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
        LottoTickets purchasedLottoTickets = new LottoTickets();
        purchasedLottoTickets.add(purchasedLottoTicket);

        LottoComparator lottoComparator = new LottoComparator(winningLottoNumbers, userPurchase);
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

        assertThat(lottoResult.getProfit())
            .isEqualTo((double) FIVE_AND_BONUS_MATCH.getPrizeMoney() / (double) PURCHASE_PRICE);
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
        LottoTickets purchasedLottoTickets = new LottoTickets();
        purchasedLottoTickets.add(purchasedLottoTicket);

        LottoComparator lottoComparator = new LottoComparator(winningLottoNumbers, userPurchase);
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

        assertThat(lottoResult.getProfit())
            .isEqualTo((double) FIVE_MATCH.getPrizeMoney() / (double) PURCHASE_PRICE);
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
        LottoTickets purchasedLottoTickets = new LottoTickets();
        purchasedLottoTickets.add(purchasedLottoTicket);

        LottoComparator lottoComparator = new LottoComparator(winningLottoNumbers, userPurchase);
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

        assertThat(lottoResult.getProfit())
            .isEqualTo((double) FOUR_MATCH.getPrizeMoney() / (double) PURCHASE_PRICE);
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
        LottoTickets purchasedLottoTickets = new LottoTickets();
        purchasedLottoTickets.add(purchasedLottoTicket);

        LottoComparator lottoComparator = new LottoComparator(winningLottoNumbers, userPurchase);
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

        assertThat(lottoResult.getProfit())
            .isEqualTo((double) THREE_MATCH.getPrizeMoney() / (double) PURCHASE_PRICE);
    }

    @DisplayName("여러 개 당첨 - 2등, 4등")
    @Test
    void Should_Return_Result_When_2ndAnd4thWinning() {
        LottoTicket secondPrizeWinningTicket = new LottoTicket(Arrays.asList(
            new LottoNumber(1),
            new LottoNumber(2),
            new LottoNumber(3),
            new LottoNumber(4),
            new LottoNumber(5),
            new LottoNumber(7)
        ));
        LottoTicket fourthPrizeWinningTicket = new LottoTicket(Arrays.asList(
            new LottoNumber(1),
            new LottoNumber(2),
            new LottoNumber(3),
            new LottoNumber(4),
            new LottoNumber(9),
            new LottoNumber(10)
        ));
        LottoTickets purchasedLottoTickets = new LottoTickets();
        purchasedLottoTickets.add(secondPrizeWinningTicket);
        purchasedLottoTickets.add(fourthPrizeWinningTicket);

        LottoComparator lottoComparator = new LottoComparator(winningLottoNumbers, userPurchase);
        LottoResult lottoResult = lottoComparator.getLottoResult(purchasedLottoTickets);

        assertThat(lottoResult.getCountOfMatchedNumbersOfSpecificType(THREE_MATCH))
            .isEqualTo(0);
        assertThat(lottoResult.getCountOfMatchedNumbersOfSpecificType(FOUR_MATCH))
            .isEqualTo(1);
        assertThat(lottoResult.getCountOfMatchedNumbersOfSpecificType(FIVE_MATCH))
            .isEqualTo(0);
        assertThat(lottoResult.getCountOfMatchedNumbersOfSpecificType(FIVE_AND_BONUS_MATCH))
            .isEqualTo(1);
        assertThat(lottoResult.getCountOfMatchedNumbersOfSpecificType(SIX_MATCH))
            .isEqualTo(0);

        assertThat(lottoResult.getProfit())
            .isEqualTo(
                ((double) FOUR_MATCH.getPrizeMoney() + (double) FIVE_AND_BONUS_MATCH
                    .getPrizeMoney())
                    / (double) PURCHASE_PRICE
            );
    }
}
