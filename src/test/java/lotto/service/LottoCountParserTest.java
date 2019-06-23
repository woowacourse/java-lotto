package lotto.service;

import lotto.domain.Money;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class LottoCountParserTest {
    @Test
    void 로또_개수_예외_테스트() {
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
            LottoCountParser.parse(new Money(5000), "");
        }).withMessage("로또의 개수는 숫자 입니다.");
    }
}