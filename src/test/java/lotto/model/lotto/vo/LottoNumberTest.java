package lotto.model.lotto.vo;

import static lotto.model.lotto.vo.LottoNumber.MAX;
import static lotto.model.lotto.vo.LottoNumber.MIN;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LottoNumberTest {

    @ParameterizedTest
    @ValueSource(ints = {MIN - 1, MAX + 1})
    void 로또_번호가_최솟값_최댓값_사이가_아니면_예외_발생(int value) {
        assertThrows(IllegalArgumentException.class, () -> LottoNumber.from(value));
    }
}