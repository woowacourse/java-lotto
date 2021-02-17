package lotto.domain;


import static lotto.domain.LottoMatchType.FIVE_AND_BONUS_MATCH;
import static lotto.domain.LottoMatchType.FIVE_MATCH;
import static lotto.domain.LottoMatchType.FOUR_MATCH;
import static lotto.domain.LottoMatchType.SIX_MATCH;
import static lotto.domain.LottoMatchType.THREE_MATCH;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.assertj.core.api.Assertions;
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
        List<LottoTicket> purchasedLottoTickets = new ArrayList<>();
        purchasedLottoTickets.add(purchasedLottoTicket);

        LottoComparator lottoComparator = new LottoComparator(winningLottoNumbers);
        Map<LottoMatchType, Integer> lottoResult
            = lottoComparator.getLottoResult(purchasedLottoTickets);

        Assertions.assertThat(lottoResult.get(THREE_MATCH)).isEqualTo(0);
        Assertions.assertThat(lottoResult.get(FOUR_MATCH)).isEqualTo(0);
        Assertions.assertThat(lottoResult.get(FIVE_MATCH)).isEqualTo(0);
        Assertions.assertThat(lottoResult.get(FIVE_AND_BONUS_MATCH)).isEqualTo(0);
        Assertions.assertThat(lottoResult.get(SIX_MATCH)).isEqualTo(1);
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
        List<LottoTicket> purchasedLottoTickets = new ArrayList<>();
        purchasedLottoTickets.add(purchasedLottoTicket);

        LottoComparator lottoComparator = new LottoComparator(winningLottoNumbers);
        Map<LottoMatchType, Integer> lottoResult
            = lottoComparator.getLottoResult(purchasedLottoTickets);

        Assertions.assertThat(lottoResult.get(THREE_MATCH)).isEqualTo(0);
        Assertions.assertThat(lottoResult.get(FOUR_MATCH)).isEqualTo(0);
        Assertions.assertThat(lottoResult.get(FIVE_MATCH)).isEqualTo(0);
        Assertions.assertThat(lottoResult.get(FIVE_AND_BONUS_MATCH)).isEqualTo(1);
        Assertions.assertThat(lottoResult.get(SIX_MATCH)).isEqualTo(0);
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
        List<LottoTicket> purchasedLottoTickets = new ArrayList<>();
        purchasedLottoTickets.add(purchasedLottoTicket);

        LottoComparator lottoComparator = new LottoComparator(winningLottoNumbers);
        Map<LottoMatchType, Integer> lottoResult
            = lottoComparator.getLottoResult(purchasedLottoTickets);

        Assertions.assertThat(lottoResult.get(THREE_MATCH)).isEqualTo(0);
        Assertions.assertThat(lottoResult.get(FOUR_MATCH)).isEqualTo(0);
        Assertions.assertThat(lottoResult.get(FIVE_MATCH)).isEqualTo(1);
        Assertions.assertThat(lottoResult.get(FIVE_AND_BONUS_MATCH)).isEqualTo(0);
        Assertions.assertThat(lottoResult.get(SIX_MATCH)).isEqualTo(0);
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
        List<LottoTicket> purchasedLottoTickets = new ArrayList<>();
        purchasedLottoTickets.add(purchasedLottoTicket);

        LottoComparator lottoComparator = new LottoComparator(winningLottoNumbers);
        Map<LottoMatchType, Integer> lottoResult
            = lottoComparator.getLottoResult(purchasedLottoTickets);

        Assertions.assertThat(lottoResult.get(THREE_MATCH)).isEqualTo(0);
        Assertions.assertThat(lottoResult.get(FOUR_MATCH)).isEqualTo(1);
        Assertions.assertThat(lottoResult.get(FIVE_MATCH)).isEqualTo(0);
        Assertions.assertThat(lottoResult.get(FIVE_AND_BONUS_MATCH)).isEqualTo(0);
        Assertions.assertThat(lottoResult.get(SIX_MATCH)).isEqualTo(0);
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
        List<LottoTicket> purchasedLottoTickets = new ArrayList<>();
        purchasedLottoTickets.add(purchasedLottoTicket);

        LottoComparator lottoComparator = new LottoComparator(winningLottoNumbers);
        Map<LottoMatchType, Integer> lottoResult
            = lottoComparator.getLottoResult(purchasedLottoTickets);

        Assertions.assertThat(lottoResult.get(THREE_MATCH)).isEqualTo(1);
        Assertions.assertThat(lottoResult.get(FOUR_MATCH)).isEqualTo(0);
        Assertions.assertThat(lottoResult.get(FIVE_MATCH)).isEqualTo(0);
        Assertions.assertThat(lottoResult.get(FIVE_AND_BONUS_MATCH)).isEqualTo(0);
        Assertions.assertThat(lottoResult.get(SIX_MATCH)).isEqualTo(0);
    }
}
