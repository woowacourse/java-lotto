package service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.stream.Collectors;
import model.LottoFactory;
import model.LottoPurchase;
import model.Prize;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoFactoryTest {

    private List<Integer> extractNumbersFromLotto(String lotto) {
        return Arrays.stream(lotto
                        .replaceAll("[\\[\\]]", "")
                        .split(", "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    private LottoFactory lottoFactory;

    @BeforeEach
    void beforeEach() {
        LottoPurchase lottoPurchase = LottoPurchase.of(Integer.toString(10000));
        lottoFactory = LottoFactory.of(lottoPurchase);
    }

    @Test
    @DisplayName("구매 티켓 갯수는 구매 금액에 나누기 1000이여야 한다.")
    void validTicketNumber() {
        String expected = "10";
        assertThat(lottoFactory.lottoCountToString()).isEqualTo(expected);
    }

    @Test
    @DisplayName("발행된 티켓 내 숫자들은 모두 1 부터 45 사이여야 한다.")
    void validTicketRange() {
        for (String issuedTicket : lottoFactory.issuedLottoTicketsToString()) {
            List<Integer> numbers = extractNumbersFromLotto(issuedTicket);
            numbers.forEach(number -> assertThat(number).isBetween(1, 45));
        }
    }

    @Test
    @DisplayName("발행된 티켓 내 숫자 갯수는 6개여야 한다.")
    void validTicketSize() {
        for (String issuedTicket : lottoFactory.issuedLottoTicketsToString()) {
            List<Integer> numbers = extractNumbersFromLotto(issuedTicket);
            assertThat(numbers.size()).isEqualTo(6);
        }
    }

    @Test
    @DisplayName("수익률은 당첨합계 / 원금 이어야 한다.")
    void validBenefit() {
        EnumMap<Prize, Integer> prizeMap = Prize.initialize();
        prizeMap.put(Prize.FIFTH_PLACE, 3);

        double benefit = lottoFactory.getWinningAmount(prizeMap);
        double expected = 1.5;
        assertThat(benefit).isEqualTo(expected);
    }
}
