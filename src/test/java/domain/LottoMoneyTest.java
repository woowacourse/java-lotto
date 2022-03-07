package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.lotto.LottoMoney;
import exception.lottoMoney.LottoMoneyDivideException;
import exception.lottoMoney.LottoMoneyLessException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@SuppressWarnings("NonAsciiCharacters")
class LottoMoneyTest {

    @Test
    void 돈_로또개수로_변경_확인() {
        LottoMoney lottoMoney = LottoMoney.from(20000);
        assertThat(lottoMoney.toLottoCount())
                .isEqualTo(20);
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 200, 999})
    void 천_미만_값일_경우_예외처리(int input) {
        assertThatThrownBy(() -> LottoMoney.from(input))
                .isInstanceOf(LottoMoneyLessException.class)
                .hasMessage("로또 구입 금액은 1000원 이상이어야 합니다. : " + input);
    }

    @ParameterizedTest
    @ValueSource(ints = {1500, 2060, 9999})
    void 천으로_나누어_떨어지지_않는_경우_예외처리(int input) {
        assertThatThrownBy(() -> LottoMoney.from(input))
                .isInstanceOf(LottoMoneyDivideException.class)
                .hasMessage("로또 구입 금액은 1000 단위여야 합니다. : " + input);
    }
}