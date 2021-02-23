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
import lotto.domain.ticketgenerator.AllPurchasedLottoTickets;
import lotto.domain.ticketpurchase.LottoTickets;
import lotto.domain.ticketpurchase.NumberOfTicketsToPurchaseManually;
import lotto.domain.ticketpurchase.PurchasePrice;
import lotto.domain.ticketpurchase.UserPurchase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoResultTest {
    private static final int PURCHASE_PRICE = 10_000;
    private PurchasePrice purchasePrice;

    private WinningTicketAndBonusNumber winningLottoNumbers;

    @BeforeEach
    void setWinningLottoNumbers() {
        purchasePrice = new PurchasePrice(PURCHASE_PRICE);

        LottoTicket winnerTicket = new LottoTicket(
            Arrays.asList(
                LottoNumbers.of(1),
                LottoNumbers.of(2),
                LottoNumbers.of(3),
                LottoNumbers.of(4),
                LottoNumbers.of(5),
                LottoNumbers.of(6)
            )
        );
        LottoNumber bonusNumber = LottoNumbers.of(7);
        winningLottoNumbers = new WinningTicketAndBonusNumber(winnerTicket, bonusNumber);
    }

    @DisplayName("1등 당첨 - 6개 일치")
    @Test
    void Should_Return_Result_When_SixNumbersMatched() {
        LottoTicket lottoTicket = new LottoTicket(Arrays.asList(
            LottoNumbers.of(1),
            LottoNumbers.of(2),
            LottoNumbers.of(3),
            LottoNumbers.of(4),
            LottoNumbers.of(5),
            LottoNumbers.of(6)
        ));
        LottoTickets lottoTickets = new LottoTickets();
        lottoTickets.add(lottoTicket);

        NumberOfTicketsToPurchaseManually numberOfTicketsToPurchaseManually
            = new NumberOfTicketsToPurchaseManually(1, purchasePrice);
        UserPurchase userPurchase = new UserPurchase(purchasePrice, numberOfTicketsToPurchaseManually);
        LottoComparator lottoComparator = new LottoComparator(winningLottoNumbers, userPurchase);
        LottoResult lottoResult = lottoComparator.getLottoResult(lottoTickets);

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
            .isEqualTo(
                new BigDecimal(String.valueOf(SIX_MATCH.getPrizeMoney()))
                    .divide(new BigDecimal(String.valueOf(PURCHASE_PRICE)), MathContext.DECIMAL32)
                    .setScale(2, BigDecimal.ROUND_HALF_UP)
            );
    }


    @DisplayName("2등 당첨 - 5개, 보너스 번호 일치")
    @Test
    void Should_Return_Result_When_FiveNumbersAndBonusNumberMatched() {
        LottoTicket lottoTicket = new LottoTicket(Arrays.asList(
            LottoNumbers.of(1),
            LottoNumbers.of(2),
            LottoNumbers.of(3),
            LottoNumbers.of(4),
            LottoNumbers.of(5),
            LottoNumbers.of(7)
        ));
        LottoTickets lottoTickets = new LottoTickets();
        lottoTickets.add(lottoTicket);

        NumberOfTicketsToPurchaseManually numberOfTicketsToPurchaseManually
            = new NumberOfTicketsToPurchaseManually(1, purchasePrice);
        UserPurchase userPurchase = new UserPurchase(purchasePrice, numberOfTicketsToPurchaseManually);
        LottoComparator lottoComparator = new LottoComparator(winningLottoNumbers, userPurchase);
        LottoResult lottoResult = lottoComparator.getLottoResult(lottoTickets);

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
            .isEqualTo(
                new BigDecimal(String.valueOf(FIVE_AND_BONUS_MATCH.getPrizeMoney()))
                    .divide(new BigDecimal(String.valueOf(PURCHASE_PRICE)), MathContext.DECIMAL32)
                    .setScale(2, BigDecimal.ROUND_HALF_UP)
            );
    }


    @DisplayName("3등 당첨 - 5개 일치")
    @Test
    void Should_Return_Result_When_FiveNumbersMatched() {
        LottoTicket lottoTicket = new LottoTicket(Arrays.asList(
            LottoNumbers.of(1),
            LottoNumbers.of(2),
            LottoNumbers.of(3),
            LottoNumbers.of(4),
            LottoNumbers.of(5),
            LottoNumbers.of(9)
        ));
        LottoTickets lottoTickets = new LottoTickets();
        lottoTickets.add(lottoTicket);

        NumberOfTicketsToPurchaseManually numberOfTicketsToPurchaseManually
            = new NumberOfTicketsToPurchaseManually(1, purchasePrice);
        UserPurchase userPurchase = new UserPurchase(purchasePrice, numberOfTicketsToPurchaseManually);
        LottoComparator lottoComparator = new LottoComparator(winningLottoNumbers, userPurchase);
        LottoResult lottoResult = lottoComparator.getLottoResult(lottoTickets);

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
            .isEqualTo(
                new BigDecimal(String.valueOf(FIVE_MATCH.getPrizeMoney()))
                    .divide(new BigDecimal(String.valueOf(PURCHASE_PRICE)), MathContext.DECIMAL32)
                    .setScale(2, BigDecimal.ROUND_HALF_UP)
            );
    }


    @DisplayName("4등 당첨 - 4개 일치")
    @Test
    void Should_Return_Result_When_FourNumbersMatched() {
        LottoTicket lottoTicket = new LottoTicket(Arrays.asList(
            LottoNumbers.of(1),
            LottoNumbers.of(2),
            LottoNumbers.of(3),
            LottoNumbers.of(4),
            LottoNumbers.of(9),
            LottoNumbers.of(10)
        ));
        LottoTickets lottoTickets = new LottoTickets();
        lottoTickets.add(lottoTicket);

        NumberOfTicketsToPurchaseManually numberOfTicketsToPurchaseManually
            = new NumberOfTicketsToPurchaseManually(1, purchasePrice);
        UserPurchase userPurchase = new UserPurchase(purchasePrice, numberOfTicketsToPurchaseManually);
        LottoComparator lottoComparator = new LottoComparator(winningLottoNumbers, userPurchase);
        LottoResult lottoResult = lottoComparator.getLottoResult(lottoTickets);

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
            .isEqualTo(
                new BigDecimal(String.valueOf(FOUR_MATCH.getPrizeMoney()))
                    .divide(new BigDecimal(String.valueOf(PURCHASE_PRICE)), MathContext.DECIMAL32)
                    .setScale(2, BigDecimal.ROUND_HALF_UP)
            );
    }


    @DisplayName("5등 당첨 - 3개 일치")
    @Test
    void Should_Return_Result_When_ThreeNumbersMatched() {
        LottoTicket lottoTicket = new LottoTicket(Arrays.asList(
            LottoNumbers.of(1),
            LottoNumbers.of(2),
            LottoNumbers.of(3),
            LottoNumbers.of(10),
            LottoNumbers.of(11),
            LottoNumbers.of(12)
        ));
        LottoTickets lottoTickets = new LottoTickets();
        lottoTickets.add(lottoTicket);

        NumberOfTicketsToPurchaseManually numberOfTicketsToPurchaseManually
            = new NumberOfTicketsToPurchaseManually(1, purchasePrice);
        UserPurchase userPurchase = new UserPurchase(purchasePrice, numberOfTicketsToPurchaseManually);
        LottoComparator lottoComparator = new LottoComparator(winningLottoNumbers, userPurchase);
        LottoResult lottoResult = lottoComparator.getLottoResult(lottoTickets);

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
            .isEqualTo(
                new BigDecimal(String.valueOf(THREE_MATCH.getPrizeMoney()))
                    .divide(new BigDecimal(String.valueOf(PURCHASE_PRICE)), MathContext.DECIMAL32)
                    .setScale(2, BigDecimal.ROUND_HALF_UP)
            );
    }

    @DisplayName("여러 개 당첨 - 2등, 4등")
    @Test
    void Should_Return_Result_When_2ndAnd4thWinning() {
        LottoTicket lottoTicket1 = new LottoTicket(Arrays.asList(
            LottoNumbers.of(1),
            LottoNumbers.of(2),
            LottoNumbers.of(3),
            LottoNumbers.of(4),
            LottoNumbers.of(5),
            LottoNumbers.of(7)
        ));
        LottoTicket lottoTicket2 = new LottoTicket(Arrays.asList(
            LottoNumbers.of(1),
            LottoNumbers.of(2),
            LottoNumbers.of(3),
            LottoNumbers.of(4),
            LottoNumbers.of(9),
            LottoNumbers.of(10)
        ));
        LottoTickets lottoTickets = new LottoTickets();
        lottoTickets.add(lottoTicket1);
        lottoTickets.add(lottoTicket2);


        PurchasePrice purchasePrices2Tickets = new PurchasePrice(2000);
        NumberOfTicketsToPurchaseManually numberOfTicketsToPurchaseManually
            = new NumberOfTicketsToPurchaseManually(2,purchasePrices2Tickets);
        UserPurchase userPurchase = new UserPurchase(purchasePrice, numberOfTicketsToPurchaseManually);
        LottoComparator lottoComparator = new LottoComparator(winningLottoNumbers, userPurchase);
        LottoResult lottoResult = lottoComparator.getLottoResult(lottoTickets);

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
                new BigDecimal(String.valueOf(
                    FOUR_MATCH.getPrizeMoney()
                        + FIVE_AND_BONUS_MATCH.getPrizeMoney())
                )
                    .divide(new BigDecimal(String.valueOf(PURCHASE_PRICE)), MathContext.DECIMAL32)
                    .setScale(2, BigDecimal.ROUND_HALF_UP)
            );
    }
}
