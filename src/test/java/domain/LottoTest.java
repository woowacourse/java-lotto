package domain;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class LottoTest {

    @Test
    @DisplayName("일치하는 번호 개수 확인 테스트")
    public void checkMatchNumber() {
        WinningNumber winningNumber = new WinningNumber(List.of(LottoNumber.valueOf(1), LottoNumber.valueOf(2), LottoNumber.valueOf(3), LottoNumber.valueOf(4), LottoNumber.valueOf(5), LottoNumber.valueOf(6)));
        Lotto lotto = new Lotto(List.of(LottoNumber.valueOf(1), LottoNumber.valueOf(2), LottoNumber.valueOf(3), LottoNumber.valueOf(4), LottoNumber.valueOf(5), LottoNumber.valueOf(6)));
        int winning = lotto.match(winningNumber);

        assertEquals(winning, 6);
    }

    @ParameterizedTest
    @CsvSource(value = {"1, true", "2, true", "3, true", "7, false", "34, false"})
    @DisplayName("보너스 볼과 일치하는 번호가 있는지 테스트")
    public void checkBonusBallMatchTest(int number, boolean expected) {
        LottoNumber bonusBall = LottoNumber.valueOf(number);
        Lotto lotto = new Lotto(List.of(LottoNumber.valueOf(1), LottoNumber.valueOf(2), LottoNumber.valueOf(3), LottoNumber.valueOf(4), LottoNumber.valueOf(5), LottoNumber.valueOf(6)));

        assertEquals(lotto.hasBonusBall(bonusBall), expected);
    }
}
