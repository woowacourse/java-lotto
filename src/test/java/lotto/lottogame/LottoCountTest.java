package lotto.lottogame;

import lotto.money.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class LottoCountTest {

    @Test
    @DisplayName("로또 개수 객체 생성")
    void lottoCountCreate() {
        LottoCount lottoCount = new LottoCount(new Money("1000"));
        assertThat(lottoCount).isEqualTo(new LottoCount(new Money("1000")));
    }
}
