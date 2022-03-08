package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lotto.domain.generator.NumberGenerator;
import lotto.domain.generator.RandomNumberGenerator;

class LottoLineTest {

    @Test
    @DisplayName("로또 라인이 정상적으로 생성되는지 확인")
    void createLottoTicket() {
        // given
        List<LottoNumber> numbers = new ArrayList<>(
            List.of(LottoNumber.from(1), LottoNumber.from(2), LottoNumber.from(3),
                LottoNumber.from(4), LottoNumber.from(5), LottoNumber.from(6))
        );
        // when
        LottoLine lottoLine = new LottoLine(numbers);
        // then
        assertThat(lottoLine).isNotNull();
    }

    @Test
    @DisplayName("숫자 생성기로 로또를 한 줄 생성할 수 있다.")
    public void createLineWithNumberGenerator() {
        // given
        NumberGenerator generator = new RandomNumberGenerator(LottoNumber.MIN, LottoNumber.MAX);
        // when
        LottoLine line = LottoLine.createLine(generator);
        // then
        Assertions.assertThat(line).isNotNull();
    }

    @Test
    @DisplayName("길이가 정상이 아니면 예외처리")
    void validateLength() {
        // given
        List<LottoNumber> numbers = new ArrayList<>(
            List.of(LottoNumber.from(1), LottoNumber.from(2), LottoNumber.from(3),
                LottoNumber.from(4), LottoNumber.from(5))
        );
        // then
        assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> new LottoLine(numbers));
    }

    @Test
    @DisplayName("로또 번호는 중복될 수 없다.")
    void validateDistinct() {
        // given
        List<LottoNumber> numbers = new ArrayList<>(
            List.of(LottoNumber.from(1), LottoNumber.from(2), LottoNumber.from(3),
                LottoNumber.from(4), LottoNumber.from(5), LottoNumber.from(5))
        );
        // then
        assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> new LottoLine(numbers));
    }
}
