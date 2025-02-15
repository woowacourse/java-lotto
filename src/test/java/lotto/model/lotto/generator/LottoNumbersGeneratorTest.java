package lotto.model.lotto.generator;

import static lotto.model.lotto.Lotto.LOTTO_SIZE;
import static lotto.model.lotto.LottoNumber.MAX_LOTTO_NUMBER;
import static lotto.model.lotto.LottoNumber.MIN_LOTTO_NUMBER;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoNumbersGeneratorTest {

    @DisplayName("로또 번호 생성기는 필요한 로또 숫자 개수만큼 반환한다.")
    @Test
    void generateLottoNumbersWithValidSize() {
        NumbersGenerator generator = new LottoNumbersGenerator();

        assertThat(generator.generate()).hasSize(LOTTO_SIZE);
    }

    @DisplayName("로또 번호 생성기는 로또 숫자 범위 내의 숫자만 반환한다.")
    @Test
    void generateLottoNumbersWithValidRange() {
        NumbersGenerator generator = new LottoNumbersGenerator();

        assertThat(generator.generate()).allSatisfy((number) -> {
            assertThat(number).isBetween(MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER);
        });
    }

}
