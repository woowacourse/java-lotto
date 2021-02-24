package lotto.domain.ticketresult;


import static lotto.type.LottoMatchType.FIVE_AND_BONUS_MATCH;
import static lotto.type.LottoMatchType.FIVE_MATCH;
import static lotto.type.LottoMatchType.FOUR_MATCH;
import static lotto.type.LottoMatchType.SIX_MATCH;
import static lotto.type.LottoMatchType.THREE_MATCH;
import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.Arrays;
import lotto.domain.LottoNumber;
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
    private static final int SECOND_DECIMAL_PLACE = 2;

    private PurchasePrice purchasePrice;
    private UserPurchase userPurchase;
    private WinningTicketAndBonusNumber winningTicketAndBonusNumber;

    @BeforeEach
    void setUp() {
        purchasePrice = new PurchasePrice(PURCHASE_PRICE);
        ManualTicketsSize manualTicketsSize = new ManualTicketsSize(1, purchasePrice);
        LottoTicket winnerTicket = new LottoTicket(
            Arrays.asList(
                LottoNumber.valueOf(1),
                LottoNumber.valueOf(2),
                LottoNumber.valueOf(3),
                LottoNumber.valueOf(4),
                LottoNumber.valueOf(5),
                LottoNumber.valueOf(6)
            )
        );
        LottoNumber bonusNumber = LottoNumber.valueOf(7);
        userPurchase = new UserPurchase(purchasePrice, manualTicketsSize);
        winningTicketAndBonusNumber = new WinningTicketAndBonusNumber(winnerTicket, bonusNumber);
    }

    @DisplayName("1등 당첨 - 6개 일치")
    @Test
    void Should_Return_ExpectedResult_When_SixNumbersMatched() {
        // given
        LottoTicket lottoTicket = new LottoTicket(Arrays.asList(
            LottoNumber.valueOf(1),
            LottoNumber.valueOf(2),
            LottoNumber.valueOf(3),
            LottoNumber.valueOf(4),
            LottoNumber.valueOf(5),
            LottoNumber.valueOf(6)
        ));
        LottoTickets lottoTickets = new LottoTickets();
        lottoTickets.add(lottoTicket);

        // when
        LottoComparator lottoComparator
            = new LottoComparator(winningTicketAndBonusNumber, userPurchase);
        LottoResult lottoResult = lottoComparator.getLottoResult(lottoTickets);

        // then
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
                .divide(new BigDecimal(String.valueOf(PURCHASE_PRICE)),
                    SECOND_DECIMAL_PLACE, BigDecimal.ROUND_HALF_UP)
        );
    }


    @DisplayName("2등 당첨 - 5개, 보너스 번호 일치")
    @Test
    void Should_Return_ExpectedResult_When_FiveNumbersAndBonusNumberMatched() {
        // given
        LottoTicket lottoTicket = new LottoTicket(Arrays.asList(
            LottoNumber.valueOf(1),
            LottoNumber.valueOf(2),
            LottoNumber.valueOf(3),
            LottoNumber.valueOf(4),
            LottoNumber.valueOf(5),
            LottoNumber.valueOf(7)
        ));
        LottoTickets lottoTickets = new LottoTickets();
        lottoTickets.add(lottoTicket);

        // when
        LottoComparator lottoComparator
            = new LottoComparator(winningTicketAndBonusNumber, userPurchase);
        LottoResult lottoResult = lottoComparator.getLottoResult(lottoTickets);

        // then
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
                .divide(new BigDecimal(String.valueOf(PURCHASE_PRICE)),
                    SECOND_DECIMAL_PLACE, BigDecimal.ROUND_HALF_UP)
        );
    }


    @DisplayName("3등 당첨 - 5개 일치")
    @Test
    void Should_Return_ExpectedResult_When_FiveNumbersMatched() {
        // given
        LottoTicket lottoTicket = new LottoTicket(Arrays.asList(
            LottoNumber.valueOf(1),
            LottoNumber.valueOf(2),
            LottoNumber.valueOf(3),
            LottoNumber.valueOf(4),
            LottoNumber.valueOf(5),
            LottoNumber.valueOf(9)
        ));
        LottoTickets lottoTickets = new LottoTickets();
        lottoTickets.add(lottoTicket);

        // when
        LottoComparator lottoComparator
            = new LottoComparator(winningTicketAndBonusNumber, userPurchase);
        LottoResult lottoResult = lottoComparator.getLottoResult(lottoTickets);

        // then
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
                .divide(new BigDecimal(String.valueOf(PURCHASE_PRICE)),
                    SECOND_DECIMAL_PLACE, BigDecimal.ROUND_HALF_UP)
        );
    }


    @DisplayName("4등 당첨 - 4개 일치")
    @Test
    void Should_Return_ExpectedResult_When_FourNumbersMatched() {
        // given
        LottoTicket lottoTicket = new LottoTicket(Arrays.asList(
            LottoNumber.valueOf(1),
            LottoNumber.valueOf(2),
            LottoNumber.valueOf(3),
            LottoNumber.valueOf(4),
            LottoNumber.valueOf(9),
            LottoNumber.valueOf(10)
        ));
        LottoTickets lottoTickets = new LottoTickets();
        lottoTickets.add(lottoTicket);

        // when
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

        // then
        assertThat(lottoResult.getProfit()).isEqualTo(
            new BigDecimal(String.valueOf(FOUR_MATCH.getPrizeMoney()))
                .divide(new BigDecimal(String.valueOf(PURCHASE_PRICE)),
                    SECOND_DECIMAL_PLACE, BigDecimal.ROUND_HALF_UP)
        );
    }


    @DisplayName("5등 당첨 - 3개 일치")
    @Test
    void Should_Return_ExpectedResult_When_ThreeNumbersMatched() {
        // given
        LottoTicket lottoTicket = new LottoTicket(Arrays.asList(
            LottoNumber.valueOf(1),
            LottoNumber.valueOf(2),
            LottoNumber.valueOf(3),
            LottoNumber.valueOf(10),
            LottoNumber.valueOf(11),
            LottoNumber.valueOf(12)
        ));
        LottoTickets lottoTickets = new LottoTickets();
        lottoTickets.add(lottoTicket);

        // when
        LottoComparator lottoComparator
            = new LottoComparator(winningTicketAndBonusNumber, userPurchase);
        LottoResult lottoResult = lottoComparator.getLottoResult(lottoTickets);

        // then
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
                .divide(new BigDecimal(String.valueOf(PURCHASE_PRICE)),
                    SECOND_DECIMAL_PLACE, BigDecimal.ROUND_HALF_UP)
        );
    }

    @DisplayName("여러 개 당첨 - 2등, 4등")
    @Test
    void Should_Return_ExpectedResult_When_2ndAnd4thWinning() {
        // given
        LottoTicket lottoTicket1 = new LottoTicket(Arrays.asList(
            LottoNumber.valueOf(1),
            LottoNumber.valueOf(2),
            LottoNumber.valueOf(3),
            LottoNumber.valueOf(4),
            LottoNumber.valueOf(5),
            LottoNumber.valueOf(7)
        ));
        LottoTicket lottoTicket2 = new LottoTicket(Arrays.asList(
            LottoNumber.valueOf(1),
            LottoNumber.valueOf(2),
            LottoNumber.valueOf(3),
            LottoNumber.valueOf(4),
            LottoNumber.valueOf(9),
            LottoNumber.valueOf(10)
        ));
        LottoTickets lottoTickets = new LottoTickets();
        lottoTickets.add(lottoTicket1);
        lottoTickets.add(lottoTicket2);

        PurchasePrice purchasePrices4Tickets = new PurchasePrice(4000);
        ManualTicketsSize manual2TicketsSize
            = new ManualTicketsSize(2, purchasePrices4Tickets);
        UserPurchase userPurchase2Manual2Auto = new UserPurchase(purchasePrice, manual2TicketsSize);

        // when
        LottoComparator lottoComparator
            = new LottoComparator(winningTicketAndBonusNumber, userPurchase2Manual2Auto);
        LottoResult lottoResult = lottoComparator.getLottoResult(lottoTickets);

        // then
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
                .divide(new BigDecimal(String.valueOf(PURCHASE_PRICE)),
                    SECOND_DECIMAL_PLACE, BigDecimal.ROUND_HALF_UP)
        );
    }
}
