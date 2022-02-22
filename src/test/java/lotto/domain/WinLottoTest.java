package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WinLottoTest {

    @DisplayName("보너스 볼과 당첨 번호가 중복되는 경우 에러 발생")
    @Test
    void duplicateBonusBallNumber() {
        final List<LottoNumber> winNumbers = Stream.of(1, 2, 3, 4, 5, 6)
                .map(LottoNumber::new)
                .collect(Collectors.toList());
        final LottoNumber duplicateBonusLottoNumber = new LottoNumber(1);

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new WinLotto(new Lotto(winNumbers), duplicateBonusLottoNumber))
                .withMessage("[ERROR] 보너스 볼이 당첨 번호와 중복됩니다.");
    }
}
