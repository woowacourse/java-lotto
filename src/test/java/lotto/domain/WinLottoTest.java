package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WinLottoTest {

    @DisplayName("중복된 당첨 번호가 존재하는 경우 에러 발생")
    @Test
    void duplicateLottoNumber() {
        final List<Integer> duplicateNumbers = Arrays.asList(1, 1, 1, 1, 1, 1);

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new WinLotto(duplicateNumbers, 5))
                .withMessage("[ERROR] 중복된 당첨 번호가 존재합니다.");
    }

    @DisplayName("보너스 볼과 당첨 번호가 중복되는 경우 에러 발생")
    @Test
    void duplicateBonusBallNumber() {
        final List<Integer> winNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        final int duplicateBonusNumber = 1;

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new WinLotto(winNumbers, duplicateBonusNumber))
                .withMessage("[ERROR] 보너스 볼이 당첨 번호와 중복됩니다.");
    }
}
