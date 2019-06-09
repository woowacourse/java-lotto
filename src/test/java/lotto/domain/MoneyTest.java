package lotto.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class MoneyTest {
    @Test
    void 돈_생성() {
        assertThat(new Money(1000)).isEqualTo(new Money(1000));
    }

    @Test
    void 로또_구매가능_개수() {
        assertThat(new Money(1000).calCountOfLotto()).isEqualTo(1);
    }

    @Test
    void 로또_구매_가능_유무() {
        assertThat(new Money(1000).canBuyLotto(1)).isEqualTo(true);
    }

    @Test
    void 금액_범위_미만_테스트() {
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
            new Money(0);
        }).withMessage("금액의 범위는 1000 ~ 100000 입니다.");
    }

    @Test
    void 금액_범위_초과_테스트() {
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
            new Money(101000);
        }).withMessage("금액의 범위는 1000 ~ 100000 입니다.");
    }

    @Test
    void 금액_단위_테스트() {
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
            new Money(1500);
        }).withMessage("금액의 단위는 1000 입니다.");
    }
}