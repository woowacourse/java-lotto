package domain.lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class WinningLottoTest {

    @DisplayName("WinningLotto 정상 생성된다.")
    @Test
    void winningLotto_생성_테스트() {
        assertThatCode(() -> new WinningLotto(Arrays.asList(1, 2, 3, 4, 5, 6)))
                .doesNotThrowAnyException();
    }

    @DisplayName("WinningLotto 중복된다면 에러를 출력한다.")
    @Test
    void winningLotto_생성_중복_테스트() {
        assertThatThrownBy(() -> new WinningLotto(Arrays.asList(1, 2, 2, 2, 3, 4)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("WinningLotto 번호가 1~45가 아니면 에러가 발생한다.")
    @Test
    void WinningLotto_생성시_번호_에러테스트() {
        List<Integer> winningLottoNumbers = Arrays.asList(-1, 47, 48, 49, 50, 51);
        assertThatThrownBy(() -> new Lotto(winningLottoNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("1~45 사이의 번호만 허용합니다.");
    }

    @DisplayName("WinningLotto 번호가 6개가 아니면 에러가 발생한다.")
    @Test
    void WinningLotto_생성시_갯_에러테스트() {
        List<Integer> winningLottoNumbers = Arrays.asList(1, 2, 3, 4, 5);
        assertThatThrownBy(() -> new Lotto(winningLottoNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("6개의 로또 번호가 필요합니다.");
    }
}
