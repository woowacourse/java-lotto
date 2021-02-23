package lotto.domain.ticketresult;


import static lotto.type.LottoMatchType.FIVE_AND_BONUS_MATCH;
import static lotto.type.LottoMatchType.FIVE_MATCH;
import static lotto.type.LottoMatchType.FOUR_MATCH;
import static lotto.type.LottoMatchType.SIX_MATCH;
import static lotto.type.LottoMatchType.THREE_MATCH;
import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Arrays;
import lotto.domain.LottoNumber;
import lotto.domain.LottoNumbers;
import lotto.domain.LottoTicket;
import lotto.domain.ticketpurchase.LottoTickets;
import lotto.domain.ticketpurchase.ManualTicketsSize;
import lotto.domain.ticketpurchase.PurchasePrice;
import lotto.domain.ticketpurchase.UserPurchase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoResultTest {
    private static final int PURCHASE_PRICE = 10_000;
    private static final int NEW_SCALE = 2;
    private PurchasePrice purchasePrice;
    private WinningTicketAndBonusNumber winningTicketAndBonusNumber;

    @BeforeEach
    void setWinningLottoNumbers() {
        purchasePrice = new PurchasePrice(PURCHASE_PRICE);
        LottoTicket winnerTicket = new LottoTicket(
            Arrays.asList(
                LottoNumbers.get(1),
                LottoNumbers.get(2),
                LottoNumbers.get(3),
                LottoNumbers.get(4),
                LottoNumbers.get(5),
                LottoNumbers.get(6)
            )
        );
        LottoNumber bonusNumber = LottoNumbers.get(7);
        winningTicketAndBonusNumber = new WinningTicketAndBonusNumber(winnerTicket, bonusNumber);
    }

    @DisplayName("1등 당첨 - 6개 일치")
    @Test
    void Should_Return_ExpectedResult_When_SixNumbersMatched() {
        LottoTicket lottoTicket = new LottoTicket(Arrays.asList(
            LottoNumbers.get(1),
            LottoNumbers.get(2),
            LottoNumbers.get(3),
            LottoNumbers.get(4),
            LottoNumbers.get(5),
            LottoNumbers.get(6)
        ));
        LottoTickets lottoTickets = new LottoTickets();
        lottoTickets.add(lottoTicket);

        ManualTicketsSize manualTicketsSize = new ManualTicketsSize(1, purchasePrice);
        UserPurchase userPurchase = new UserPurchase(purchasePrice, manualTicketsSize);
        LottoComparator lottoComparator
            = new LottoComparator(winningTicketAndBonusNumber, userPurchase);
        LottoResult lottoResult = lottoComparator.getLottoResult(lottoTickets);

        assertThat(lottoResult.getMatchTypeCount(THREE_MATCH))
            .isEqualTo(0);
        assertThat(lottoResult.getMatchTypeCount(FOUR_MATCH))
            .isEqualTo(0);
        assertThat(lottoResult.getMatchTypeCount(FIVE_MATCH))
            .isEqualTo(0);
        assertThat(lottoResult.getMatchTypeCount(FIVE_AND_BONUS_MATCH))
            .isEqualTo(0);
        assertThat(lottoResult.getMatchTypeCount(SIX_MATCH))
            .isEqualTo(1);

        assertThat(lottoResult.getProfit()).isEqualTo(
            new BigDecimal(String.valueOf(SIX_MATCH.getPrizeMoney()))
                .divide(new BigDecimal(String.valueOf(PURCHASE_PRICE)), MathContext.DECIMAL32)
                .setScale(NEW_SCALE, BigDecimal.ROUND_HALF_UP)
        );
    }


    @DisplayName("2등 당첨 - 5개, 보너스 번호 일치")
    @Test
    void Should_Return_ExpectedResult_When_FiveNumbersAndBonusNumberMatched() {
        LottoTicket lottoTicket = new LottoTicket(Arrays.asList(
            LottoNumbers.get(1),
            LottoNumbers.get(2),
            LottoNumbers.get(3),
            LottoNumbers.get(4),
            LottoNumbers.get(5),
            LottoNumbers.get(7)
        ));
        LottoTickets lottoTickets = new LottoTickets();
        lottoTickets.add(lottoTicket);

        ManualTicketsSize manualTicketsSize
            = new ManualTicketsSize(1, purchasePrice);
        UserPurchase userPurchase = new UserPurchase(purchasePrice, manualTicketsSize);
        LottoComparator lottoComparator
            = new LottoComparator(winningTicketAndBonusNumber, userPurchase);
        LottoResult lottoResult = lottoComparator.getLottoResult(lottoTickets);

        assertThat(lottoResult.getMatchTypeCount(THREE_MATCH))
            .isEqualTo(0);
        assertThat(lottoResult.getMatchTypeCount(FOUR_MATCH))
            .isEqualTo(0);
        assertThat(lottoResult.getMatchTypeCount(FIVE_MATCH))
            .isEqualTo(0);
        assertThat(lottoResult.getMatchTypeCount(FIVE_AND_BONUS_MATCH))
            .isEqualTo(1);
        assertThat(lottoResult.getMatchTypeCount(SIX_MATCH))
            .isEqualTo(0);

        assertThat(lottoResult.getProfit()).isEqualTo(
            new BigDecimal(String.valueOf(FIVE_AND_BONUS_MATCH.getPrizeMoney()))
                .divide(new BigDecimal(String.valueOf(PURCHASE_PRICE)), MathContext.DECIMAL32)
                .setScale(NEW_SCALE, BigDecimal.ROUND_HALF_UP)
        );
    }


    @DisplayName("3등 당첨 - 5개 일치")
    @Test
    void Should_Return_ExpectedResult_When_FiveNumbersMatched() {
        LottoTicket lottoTicket = new LottoTicket(Arrays.asList(
            LottoNumbers.get(1),
            LottoNumbers.get(2),
            LottoNumbers.get(3),
            LottoNumbers.get(4),
            LottoNumbers.get(5),
            LottoNumbers.get(9)
        ));
        LottoTickets lottoTickets = new LottoTickets();
        lottoTickets.add(lottoTicket);

        ManualTicketsSize manualTicketsSize
            = new ManualTicketsSize(1, purchasePrice);
        UserPurchase userPurchase = new UserPurchase(purchasePrice, manualTicketsSize);
        LottoComparator lottoComparator
            = new LottoComparator(winningTicketAndBonusNumber, userPurchase);
        LottoResult lottoResult = lottoComparator.getLottoResult(lottoTickets);

        assertThat(lottoResult.getMatchTypeCount(THREE_MATCH))
            .isEqualTo(0);
        assertThat(lottoResult.getMatchTypeCount(FOUR_MATCH))
            .isEqualTo(0);
        assertThat(lottoResult.getMatchTypeCount(FIVE_MATCH))
            .isEqualTo(1);
        assertThat(lottoResult.getMatchTypeCount(FIVE_AND_BONUS_MATCH))
            .isEqualTo(0);
        assertThat(lottoResult.getMatchTypeCount(SIX_MATCH))
            .isEqualTo(0);

        assertThat(lottoResult.getProfit()).isEqualTo(
            new BigDecimal(String.valueOf(FIVE_MATCH.getPrizeMoney()))
                .divide(new BigDecimal(String.valueOf(PURCHASE_PRICE)), MathContext.DECIMAL32)
                .setScale(NEW_SCALE, BigDecimal.ROUND_HALF_UP)
        );
    }


    @DisplayName("4등 당첨 - 4개 일치")
    @Test
    void Should_Return_ExpectedResult_When_FourNumbersMatched() {
        LottoTicket lottoTicket = new LottoTicket(Arrays.asList(
            LottoNumbers.get(1),
            LottoNumbers.get(2),
            LottoNumbers.get(3),
            LottoNumbers.get(4),
            LottoNumbers.get(9),
            LottoNumbers.get(10)
        ));
        LottoTickets lottoTickets = new LottoTickets();
        lottoTickets.add(lottoTicket);

        ManualTicketsSize manualTicketsSize
            = new ManualTicketsSize(1, purchasePrice);
        UserPurchase userPurchase = new UserPurchase(purchasePrice, manualTicketsSize);
        LottoComparator lottoComparator
            = new LottoComparator(winningTicketAndBonusNumber, userPurchase);
        LottoResult lottoResult = lottoComparator.getLottoResult(lottoTickets);

        assertThat(lottoResult.getMatchTypeCount(THREE_MATCH))
            .isEqualTo(0);
        assertThat(lottoResult.getMatchTypeCount(FOUR_MATCH))
            .isEqualTo(1);
        assertThat(lottoResult.getMatchTypeCount(FIVE_MATCH))
            .isEqualTo(0);
        assertThat(lottoResult.getMatchTypeCount(FIVE_AND_BONUS_MATCH))
            .isEqualTo(0);
        assertThat(lottoResult.getMatchTypeCount(SIX_MATCH))
            .isEqualTo(0);

        assertThat(lottoResult.getProfit()).isEqualTo(
            new BigDecimal(String.valueOf(FOUR_MATCH.getPrizeMoney()))
                .divide(new BigDecimal(String.valueOf(PURCHASE_PRICE)), MathContext.DECIMAL32)
                .setScale(NEW_SCALE, BigDecimal.ROUND_HALF_UP)
        );
    }


    @DisplayName("5등 당첨 - 3개 일치")
    @Test
    void Should_Return_ExpectedResult_When_ThreeNumbersMatched() {
        LottoTicket lottoTicket = new LottoTicket(Arrays.asList(
            LottoNumbers.get(1),
            LottoNumbers.get(2),
            LottoNumbers.get(3),
            LottoNumbers.get(10),
            LottoNumbers.get(11),
            LottoNumbers.get(12)
        ));
        LottoTickets lottoTickets = new LottoTickets();
        lottoTickets.add(lottoTicket);

        ManualTicketsSize manualTicketsSize
            = new ManualTicketsSize(1, purchasePrice);
        UserPurchase userPurchase = new UserPurchase(purchasePrice, manualTicketsSize);
        LottoComparator lottoComparator
            = new LottoComparator(winningTicketAndBonusNumber, userPurchase);
        LottoResult lottoResult = lottoComparator.getLottoResult(lottoTickets);

        assertThat(lottoResult.getMatchTypeCount(THREE_MATCH))
            .isEqualTo(1);
        assertThat(lottoResult.getMatchTypeCount(FOUR_MATCH))
            .isEqualTo(0);
        assertThat(lottoResult.getMatchTypeCount(FIVE_MATCH))
            .isEqualTo(0);
        assertThat(lottoResult.getMatchTypeCount(FIVE_AND_BONUS_MATCH))
            .isEqualTo(0);
        assertThat(lottoResult.getMatchTypeCount(SIX_MATCH))
            .isEqualTo(0);

        assertThat(lottoResult.getProfit()).isEqualTo(
            new BigDecimal(String.valueOf(THREE_MATCH.getPrizeMoney()))
                .divide(new BigDecimal(String.valueOf(PURCHASE_PRICE)), MathContext.DECIMAL32)
                .setScale(NEW_SCALE, BigDecimal.ROUND_HALF_UP)
        );
    }

    @DisplayName("여러 개 당첨 - 2등, 4등")
    @Test
    void Should_Return_ExpectedResult_When_2ndAnd4thWinning() {
        LottoTicket lottoTicket1 = new LottoTicket(Arrays.asList(
            LottoNumbers.get(1),
            LottoNumbers.get(2),
            LottoNumbers.get(3),
            LottoNumbers.get(4),
            LottoNumbers.get(5),
            LottoNumbers.get(7)
        ));
        LottoTicket lottoTicket2 = new LottoTicket(Arrays.asList(
            LottoNumbers.get(1),
            LottoNumbers.get(2),
            LottoNumbers.get(3),
            LottoNumbers.get(4),
            LottoNumbers.get(9),
            LottoNumbers.get(10)
        ));
        LottoTickets lottoTickets = new LottoTickets();
        lottoTickets.add(lottoTicket1);
        lottoTickets.add(lottoTicket2);

        PurchasePrice purchasePrices2Tickets = new PurchasePrice(2000);
        ManualTicketsSize manualTicketsSize
            = new ManualTicketsSize(2, purchasePrices2Tickets);
        UserPurchase userPurchase = new UserPurchase(purchasePrice, manualTicketsSize);
        LottoComparator lottoComparator
            = new LottoComparator(winningTicketAndBonusNumber, userPurchase);
        LottoResult lottoResult = lottoComparator.getLottoResult(lottoTickets);

        assertThat(lottoResult.getMatchTypeCount(THREE_MATCH))
            .isEqualTo(0);
        assertThat(lottoResult.getMatchTypeCount(FOUR_MATCH))
            .isEqualTo(1);
        assertThat(lottoResult.getMatchTypeCount(FIVE_MATCH))
            .isEqualTo(0);
        assertThat(lottoResult.getMatchTypeCount(FIVE_AND_BONUS_MATCH))
            .isEqualTo(1);
        assertThat(lottoResult.getMatchTypeCount(SIX_MATCH))
            .isEqualTo(0);

        assertThat(lottoResult.getProfit()).isEqualTo(
            new BigDecimal(String.valueOf(
                FOUR_MATCH.getPrizeMoney() + FIVE_AND_BONUS_MATCH.getPrizeMoney()))
                .divide(new BigDecimal(String.valueOf(PURCHASE_PRICE)), MathContext.DECIMAL32)
                .setScale(NEW_SCALE, BigDecimal.ROUND_HALF_UP)
        );
    }
}
