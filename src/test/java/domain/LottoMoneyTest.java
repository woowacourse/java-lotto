package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.lotto.LottoMoney;
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
        assertThatThrownBy(() -> LottoMoney.from(input)).isInstanceOf(Exception.class);
    }

    @ParameterizedTest
    @ValueSource(ints = {1500, 2060, 9999})
    void 천으로_나누어_떨어지지_않는_경우_예외처리(int input) {
        assertThatThrownBy(() -> LottoMoney.from(input)).isInstanceOf(Exception.class);
    }
}