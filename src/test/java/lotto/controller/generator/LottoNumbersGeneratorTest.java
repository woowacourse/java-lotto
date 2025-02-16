package lotto.controller.generator;

import static lotto.model.lotto.LottoNumber.MAX_LOTTO_NUMBER;
import static lotto.model.lotto.LottoNumber.MIN_LOTTO_NUMBER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoNumbersGeneratorTest {

    private static final int LOTTO_SIZE = 6;
    private static final int CUSTOM_MIN_LOTTO_NUMBER = 1;
    private static final int CUSTOM_MAX_LOTTO_NUMBER = 6;
    private NumbersGenerator generator;

    @BeforeEach
    void setUp() {
        generator = new LottoNumbersGenerator(CUSTOM_MIN_LOTTO_NUMBER, CUSTOM_MAX_LOTTO_NUMBER, LOTTO_SIZE);
    }

    @DisplayName("로또 번호 생성기는 0개 보다 작은 숫자들을 만들 수 없습니다.")
    @Test
    void createGeneratorWithSizeZero() {
        assertThatThrownBy(() -> new LottoNumbersGenerator(CUSTOM_MIN_LOTTO_NUMBER, CUSTOM_MAX_LOTTO_NUMBER, -1))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호 생성기의 최소값은 최대값보다 클 수 없습니다.")
    @Test
    void createGeneratorWithMinGreaterThanMax() {
        assertThatThrownBy(() -> new LottoNumbersGenerator(7, CUSTOM_MAX_LOTTO_NUMBER, LOTTO_SIZE))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호 생성기는 필요한 로또 숫자 개수만큼 반환한다.")
    @Test
    void generateLottoNumbersWithValidSize() {
        assertThat(generator.generate()).hasSize(LOTTO_SIZE);
    }

    @DisplayName("로또 번호 생성기는 로또 숫자 범위 내의 숫자만 반환한다.")
    @Test
    void generateLottoNumbersWithValidRange() {
        assertThat(generator.generate()).allSatisfy((number) -> {
            assertThat(number).isBetween(MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER);
        }).containsExactlyInAnyOrder(1, 2, 3, 4, 5, 6);
    }

}
