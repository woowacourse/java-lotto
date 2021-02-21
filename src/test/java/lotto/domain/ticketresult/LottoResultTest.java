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
import lotto.domain.LottoTicket;
import lotto.domain.ticketgenerator.AllPurchasedLottoTickets;
import lotto.domain.ticketpurchase.LottoTickets;
import lotto.domain.ticketpurchase.PurchasePrice;
import lotto.domain.ticketpurchase.UserPurchase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoResultTest {
    private static final int PURCHASE_PRICE = 10_000;

    private WinningTicketAndBonusNumber winningLottoNumbers;
    private UserPurchase userPurchase;

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
        userPurchase = new UserPurchase(new PurchasePrice(PURCHASE_PRICE), new LottoTickets());
    }

    @DisplayName("1등 당첨 - 6개 일치")
    @Test
    void Should_Return_Result_When_SixNumbersMatched() {
        LottoTicket lottoTicket = new LottoTicket(Arrays.asList(
            new LottoNumber(1),
            new LottoNumber(2),
            new LottoNumber(3),
            new LottoNumber(4),
            new LottoNumber(5),
            new LottoNumber(6)
        ));
        LottoTickets manuallyPurchasedLottoTickets = new LottoTickets();
        manuallyPurchasedLottoTickets.add(lottoTicket);
        LottoTickets automaticallyPurchasedLottoTickets = new LottoTickets();
        AllPurchasedLottoTickets allPurchasedLottoTickets
            = new AllPurchasedLottoTickets(manuallyPurchasedLottoTickets,
            automaticallyPurchasedLottoTickets);

        LottoComparator lottoComparator = new LottoComparator(winningLottoNumbers, userPurchase);
        LottoResult lottoResult = lottoComparator.getLottoResult(allPurchasedLottoTickets);

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
            new LottoNumber(1),
            new LottoNumber(2),
            new LottoNumber(3),
            new LottoNumber(4),
            new LottoNumber(5),
            new LottoNumber(7)
        ));
        LottoTickets manuallyPurchasedLottoTickets = new LottoTickets();
        LottoTickets automaticallyPurchasedLottoTickets = new LottoTickets();
        automaticallyPurchasedLottoTickets.add(lottoTicket);
        AllPurchasedLottoTickets allPurchasedLottoTickets
            = new AllPurchasedLottoTickets(manuallyPurchasedLottoTickets,
            automaticallyPurchasedLottoTickets);

        LottoComparator lottoComparator = new LottoComparator(winningLottoNumbers, userPurchase);
        LottoResult lottoResult = lottoComparator.getLottoResult(allPurchasedLottoTickets);

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
            new LottoNumber(1),
            new LottoNumber(2),
            new LottoNumber(3),
            new LottoNumber(4),
            new LottoNumber(5),
            new LottoNumber(9)
        ));
        LottoTickets manuallyPurchasedLottoTickets = new LottoTickets();
        manuallyPurchasedLottoTickets.add(lottoTicket);
        LottoTickets automaticallyPurchasedLottoTickets = new LottoTickets();
        AllPurchasedLottoTickets allPurchasedLottoTickets
            = new AllPurchasedLottoTickets(manuallyPurchasedLottoTickets,
            automaticallyPurchasedLottoTickets);

        LottoComparator lottoComparator = new LottoComparator(winningLottoNumbers, userPurchase);
        LottoResult lottoResult = lottoComparator.getLottoResult(allPurchasedLottoTickets);

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
            new LottoNumber(1),
            new LottoNumber(2),
            new LottoNumber(3),
            new LottoNumber(4),
            new LottoNumber(9),
            new LottoNumber(10)
        ));
        LottoTickets manuallyPurchasedLottoTickets = new LottoTickets();
        LottoTickets automaticallyPurchasedLottoTickets = new LottoTickets();
        automaticallyPurchasedLottoTickets.add(lottoTicket);
        AllPurchasedLottoTickets allPurchasedLottoTickets
            = new AllPurchasedLottoTickets(manuallyPurchasedLottoTickets,
            automaticallyPurchasedLottoTickets);

        LottoComparator lottoComparator = new LottoComparator(winningLottoNumbers, userPurchase);
        LottoResult lottoResult = lottoComparator.getLottoResult(allPurchasedLottoTickets);

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
            new LottoNumber(1),
            new LottoNumber(2),
            new LottoNumber(3),
            new LottoNumber(10),
            new LottoNumber(11),
            new LottoNumber(12)
        ));
        LottoTickets manuallyPurchasedLottoTickets = new LottoTickets();
        manuallyPurchasedLottoTickets.add(lottoTicket);
        LottoTickets automaticallyPurchasedLottoTickets = new LottoTickets();
        AllPurchasedLottoTickets allPurchasedLottoTickets
            = new AllPurchasedLottoTickets(manuallyPurchasedLottoTickets,
            automaticallyPurchasedLottoTickets);

        LottoComparator lottoComparator = new LottoComparator(winningLottoNumbers, userPurchase);
        LottoResult lottoResult = lottoComparator.getLottoResult(allPurchasedLottoTickets);

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
        LottoTicket manuallyPurchasedLottoTicket = new LottoTicket(Arrays.asList(
            new LottoNumber(1),
            new LottoNumber(2),
            new LottoNumber(3),
            new LottoNumber(4),
            new LottoNumber(5),
            new LottoNumber(7)
        ));
        LottoTicket automaticallyPurchasedLottoTicket = new LottoTicket(Arrays.asList(
            new LottoNumber(1),
            new LottoNumber(2),
            new LottoNumber(3),
            new LottoNumber(4),
            new LottoNumber(9),
            new LottoNumber(10)
        ));
        LottoTickets manuallyPurchasedLottoTickets = new LottoTickets();
        manuallyPurchasedLottoTickets.add(manuallyPurchasedLottoTicket);
        LottoTickets automaticallyPurchasedLottoTickets = new LottoTickets();
        automaticallyPurchasedLottoTickets.add(automaticallyPurchasedLottoTicket);
        AllPurchasedLottoTickets allPurchasedLottoTickets
            = new AllPurchasedLottoTickets(manuallyPurchasedLottoTickets,
            automaticallyPurchasedLottoTickets);

        LottoComparator lottoComparator = new LottoComparator(winningLottoNumbers, userPurchase);
        LottoResult lottoResult = lottoComparator.getLottoResult(allPurchasedLottoTickets);

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
