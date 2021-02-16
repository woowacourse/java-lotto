package domain.lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoTest {

    @DisplayName("Lotto 정상 생성된다.")
    @Test
    public void Lotto_생성() {
        List<Integer> lottoNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        assertThatCode(() -> new Lotto(lottoNumbers)).doesNotThrowAnyException();
    }

    @DisplayName("중복된 값이면 에러가 발생한다. ")
    @Test
    void Lotto_생성시_중복_에러테스트() {
        List<Integer> lottoNumbers = Arrays.asList(1, 2, 2, 4, 5, 6);
        assertThatThrownBy(() -> new Lotto(lottoNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("중복된 번호는 허용하지 않습니다.");
    }

    @DisplayName("로또 번호가 1~45가 아니면 에러가 발생한다.")
    @Test
    void Lotto_생성시_번호_에러테스트() {
        List<Integer> lottoNumbers = Arrays.asList(-1, 47, 48, 49, 50, 51);
        assertThatThrownBy(() -> new Lotto(lottoNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("1~45 사이의 번호만 허용합니다.");
    }

    @DisplayName("로또 번호가 6개가 아니면 에러가 발생한다.")
    @Test
    void Lotto_생성시_갯_에러테스트() {
        List<Integer> lottoNumbers = Arrays.asList(1, 2, 3, 4, 5);
        assertThatThrownBy(() -> new Lotto(lottoNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("6개의 로또 번호가 필요합니다.");
    }
}
