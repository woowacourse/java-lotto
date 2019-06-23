package lotto.domain;

import lotto.service.LottoCountParser;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class LottoCountTest {
    @Test
    void 로또_개수_금액_초과_테스트() {
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
            LottoCountParser.parse(new Money(5000), "6");
        }).withMessage("금액이 부족합니다.");
    }
}