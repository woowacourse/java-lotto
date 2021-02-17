package domain.lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.*;

public class WinningLottoTest {

    @DisplayName("WinningLotto 정상 생성된다.")
    @Test
    void winningLotto_생성_테스트() {
        assertThatCode(() -> new WinningLotto(LottoNumbers.generate(Arrays.asList(1, 2, 3, 4, 5, 6)), new BonusNumber(new LottoNumber(7))))
                .doesNotThrowAnyException();
    }

    @DisplayName("WinningLotto내 LottoNumbers와 BonusNumber 중복 검사")
    @Test
    void winningLotto_중복_테스트() {
        assertThatThrownBy(() -> new WinningLotto(LottoNumbers.generate(Arrays.asList(1, 2, 3, 4, 5, 6)), new BonusNumber(new LottoNumber(6))))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
