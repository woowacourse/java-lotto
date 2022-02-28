package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@SuppressWarnings("NonAsciiCharacters")
class PurchaseTest {

    @Test
    void 구매_정상_생성_확인() {
        Purchase purchase = new Purchase(20000, 1);
        assertThat(purchase.getAutoCount())
            .isEqualTo(19);
    }

    @Test
    void 구매_금액_이상의_로또_구매_에러() {
        assertThatThrownBy(() -> new Purchase(20000, 21)).isInstanceOf(Exception.class);
    }


    @ParameterizedTest
    @ValueSource(ints = {-1, 200, 999})
    void 천_미만_값일_경우_예외처리(int input) {
        assertThatThrownBy(() -> new Purchase(input, 1)).isInstanceOf(Exception.class);
    }

    @ParameterizedTest
    @ValueSource(ints = {1500, 2060, 9999})
    void 천으로_나누어_떨어지지_않는_경우_예외처리(int input) {
        assertThatThrownBy(() -> new Purchase(input, 1)).isInstanceOf(Exception.class);
    }
}