package lotto.domain.factory;

import lotto.domain.LottoTickets;
import lotto.domain.Money;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class LottoTicketsFactoryTest {
    private List<String> customLottoNumbers = Arrays.asList("1,2,3,4,5,6", "1,12,14,21,29,38");

    @Test
    void LottoTickets_인스턴스_생성_테스트() {
        Money money = new Money(2000);

        assertThat(LottoTicketsFactory.getInstance().create(money, customLottoNumbers))
                .isEqualTo(LottoTicketsFactory.getInstance().create(money, customLottoNumbers));
    }

    @Test
    void 반환값_클래스_확인() {
        Money money = new Money(2000);

        assertThat(LottoTicketsFactory.getInstance().create(money, customLottoNumbers)).isInstanceOf(LottoTickets.class);
    }

    @Test
    void 입력한_금액만큼_로또티켓_만드는지_확인() {
        Money money = new Money(100_000);

        assertThat(LottoTicketsFactory.getInstance().create(money, customLottoNumbers).getLottoTickets().size()).isEqualTo(100);
    }
}
