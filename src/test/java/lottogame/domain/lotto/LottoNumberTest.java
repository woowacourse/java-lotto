package lottogame.domain.lotto;

import lottogame.utils.InvalidLottoNumberRangeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LottoNumberTest {
    @DisplayName("로또 번호가 범위를 넘어 가는 경우 예외 처리")
    @Test
    void 로또_번호_범위_테스트() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 64, 5, 6);
        assertThrows(InvalidLottoNumberRangeException.class,
                () -> new LottoNumber(numbers));
    }
}