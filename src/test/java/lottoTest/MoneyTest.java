package lottoTest;

import static org.assertj.core.api.Assertions.assertThat;

import lotto.Money;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
public class MoneyTest {

    @Test
    public void 구입한_로또_개수_테스트() {
        Money money = new Money(14000);
        int result = money.getLottoCount();
        assertThat(result).isEqualTo(14);
    }
}
