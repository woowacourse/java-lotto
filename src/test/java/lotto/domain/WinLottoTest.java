package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WinLottoTest {

    @DisplayName("중복된 당첨 번호가 존재하는 경우 에러 발생")
    @Test
    void duplicateLottoNumber() {
        final List<LottoNumber> duplicateNumbers = Stream.of(1, 1, 1, 1, 1, 1)
                .map(LottoNumber::new)
                .collect(Collectors.toList());

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new WinLotto(duplicateNumbers, new LottoNumber(5)))
                .withMessage("[ERROR] 중복된 당첨 번호가 존재합니다.");
    }

    @DisplayName("보너스 볼과 당첨 번호가 중복되는 경우 에러 발생")
    @Test
    void duplicateBonusBallNumber() {
        final List<LottoNumber> winNumbers = Stream.of(1, 2, 3, 4, 5, 6)
                .map(LottoNumber::new)
                .collect(Collectors.toList());
        final LottoNumber duplicateBonusLottoNumber = new LottoNumber(1);

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new WinLotto(winNumbers, duplicateBonusLottoNumber))
                .withMessage("[ERROR] 보너스 볼이 당첨 번호와 중복됩니다.");
    }
}
