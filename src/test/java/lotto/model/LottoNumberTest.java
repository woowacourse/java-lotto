package lotto.model;

import lotto.exception.OverRangeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LottoNumberTest {
    private LottoNumber one = LottoNumber.lottoNumber(1);
    private LottoNumber two = LottoNumber.lottoNumber(2);
    private LottoNumber three = LottoNumber.lottoNumber(3);
    private LottoNumber four = LottoNumber.lottoNumber(4);
    private LottoNumber five = LottoNumber.lottoNumber(5);
    private LottoNumber six = LottoNumber.lottoNumber(6);

    @Test
    @DisplayName("숫자가 1 ~ 45 범위를 초과할 경우")
    void checkLottoNumberRange() {
        assertThatThrownBy(() -> {
            LottoNumber.lottoNumber(0);
        }).isInstanceOf(OverRangeException.class);

        assertThatThrownBy(() -> {
            LottoNumber.lottoNumber(46);
        }).isInstanceOf(OverRangeException.class);
    }

    @Test
    @DisplayName("정렬되는지 확인")
    void sortLottoNumber() {
        List<LottoNumber> 예상값 = Arrays.asList(one, two, three, four, five, six);
        List<LottoNumber> 입력값 = Arrays.asList(six, five, four, three, two, one);
        LottoNumber.sortLottoNumber(입력값);
        for (int i = 0; i < 입력값.size(); i++) {
            assertTrue(예상값.get(i).equals(입력값.get(i)));
        }
    }
}
