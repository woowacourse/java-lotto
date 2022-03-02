package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LottoNumberTest {

    @Test
    @DisplayName("로또 번호 정상 생성 테스트")
    public void createLottoNumberTest_true() {
        LottoNumber lottoNumber = LottoNumber.valueOf(10);
        assertEquals(lottoNumber.toString(), "10");
    }

    @Test
    @DisplayName("로또 번호 음수 입력 예외 발생 테스트")
    public void createLottoNumberTest_notNatural_exception() {
        assertThrows(IllegalArgumentException.class, () -> LottoNumber.valueOf(-7));
    }

    @Test
    @DisplayName("로또 번호 입력 허용 범위 예외 발생 테스트")
    public void createLottoNumberTest_allowance_exception() {
        assertThrows(IllegalArgumentException.class, () -> LottoNumber.valueOf(47));
    }
}
