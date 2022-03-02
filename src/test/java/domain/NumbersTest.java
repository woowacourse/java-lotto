package domain;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NumbersTest {

    @Test
    @DisplayName("로또 번호 목록 정상 생성 테스트")
    public void numbersTest() {
        assertDoesNotThrow(()-> new Numbers(
            List.of(LottoNumber.valueOf(1), LottoNumber.valueOf(2), LottoNumber.valueOf(3), LottoNumber.valueOf(4), LottoNumber.valueOf(5), LottoNumber.valueOf(6))));
    }

    @Test
    @DisplayName("로또 번호 목록 중복 예외 발생 테스트")
    public void createNumbersTest_notNatural_exception() {
        assertThrows(IllegalArgumentException.class, ()-> new Numbers(
            List.of(LottoNumber.valueOf(1), LottoNumber.valueOf(2), LottoNumber.valueOf(3), LottoNumber.valueOf(4), LottoNumber.valueOf(5), LottoNumber.valueOf(5))));
    }

    @Test
    @DisplayName("로또 번호 입력 허용 범위 예외 발생 테스트")
    public void createNumbersTest_allowance_exception() {
        assertThrows(IllegalArgumentException.class, ()-> new Numbers(
            List.of(LottoNumber.valueOf(1), LottoNumber.valueOf(2), LottoNumber.valueOf(3), LottoNumber.valueOf(4), LottoNumber.valueOf(5), LottoNumber.valueOf(56))));
    }
}
