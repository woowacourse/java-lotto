package domain.buyingstrategy;

import domain.buyingstrategy.buyinginformation.BuyingInformation;
import domain.buyingstrategy.buyinginformation.Money;
import domain.lottonumbers.LottoTicket;
import domain.lottonumbers.lottonumber.LottoNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toSet;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("로또 구매 테스트")
public class BuyingStrategyTest {

    @Test
    @DisplayName("랜덤 티켓들 생성 확인")
    void generateRandomTicket() {
        BuyingInformation buyingInformation = new BuyingInformation(new Money(10000), new ArrayList<>());

        assertThat(new RandomBuyingStrategy().generateTickets(buyingInformation).size()).isEqualTo(10);
    }

    @Test
    @DisplayName("수동 티켓들 생성 확인")
    void generateManualTickets() {
        BuyingInformation buyingInformation = new BuyingInformation(new Money(10000), generateLottoTickets());

        List<LottoTicket> lottoTickets = new ManualBuyingStrategy().generateTickets(buyingInformation);

        assertThat(lottoTickets.size()).isEqualTo(2);
        assertThat(lottoTickets.contains(generateLottoTicket(1, 2, 3, 4, 5, 6))).isTrue();
        assertThat(lottoTickets.contains(generateLottoTicket(11, 12, 13, 14, 15, 16))).isTrue();
    }

    List<LottoTicket> generateLottoTickets() {
        return Arrays.asList(generateLottoTicket(1, 2, 3, 4, 5, 6),
                generateLottoTicket(11, 12, 13, 14, 15, 16));
    }

    LottoTicket generateLottoTicket(int number1, int number2, int number3, int number4, int number5, int number6) {
        List<Integer> integers = Arrays.asList(number1, number2, number3, number4, number5, number6);

        return integers.stream()
                .map(LottoNumber::of)
                .collect(collectingAndThen(toSet(), LottoTicket::new));
    }
}
