package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoNumbersTest {
    @DisplayName("로또번호 리스트 생성 테스트")
    @Test
    void 로또번호_리스트_생성_테스트() {
        List<Integer> randomNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        LottoNumbers testLottoNumbers = new LottoNumbers(randomNumbers);

        assertThat(testLottoNumbers.list())
            .containsExactly(new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(6));
    }

    @DisplayName("예외 처리 : 로또 번호 리스트의 개수가 6개가 아닐 경우")
    @Test
    void 로또번호_범위_예외_테스트() {
        List<Integer> randomNumbers = Arrays.asList(1, 2, 3, 4, 5);

        assertThatThrownBy(() -> new LottoNumbers(randomNumbers))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("예외 처리 : 로또 번호 리스트 요소 중 중복이 있을 경우")
    @Test
    void 로또번호_중복_예외_테스트() {
        List<Integer> randomNumbers = Arrays.asList(1, 2, 3, 4, 4, 5);

        assertThatThrownBy(() -> new LottoNumbers(randomNumbers))
            .isInstanceOf(IllegalArgumentException.class);
    }
}