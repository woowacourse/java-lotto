package lottoTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import lotto.Money;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@SuppressWarnings("NonAsciiCharacters")
class MoneyTest {

    @Test
    void 구입한_로또_개수_테스트() {
        Money money = new Money(14000);
        int result = money.getLottoCount();
        assertThat(result).isEqualTo(14);
    }

    @ParameterizedTest(name = "[{index}] 숫자: {0}")
    @ValueSource(ints = {0, -1})
    void 구입_금액이_양의_정수가_아닌_경우_테스트(int inputMoney) {
        assertThatThrownBy(() -> new Money(inputMoney))
                .hasMessageContaining("구입 금액은 양의 정수 형태로 입력해야 합니다.")
                .isInstanceOf(RuntimeException.class);
    }

    @Test
    void 입력이_로또_금액_단위로_나누어_떨어지_않는_경우_테스트() {
        assertThatThrownBy(() -> new Money(13500))
                .hasMessageContaining("구입 금액은")
                .hasMessageContaining("단위로 나누어 떨어져야 합니다")
                .isInstanceOf(RuntimeException.class);
    }
}
