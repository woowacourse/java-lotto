package domain;

import domain.numbergenerator.RandomNumberGenerator;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoTest {
    @DisplayName("로또 객체 생성 테스트")
    @Test
    void 로또_객체_생성_테스트() {
        // given
        RandomNumberGenerator randomNumberGenerator = new RandomNumberGenerator();

        // when
        Lottos lottos = Lottos.of(randomNumberGenerator, 1);

        // then
        Assertions.assertThat(lottos).isInstanceOf(Lottos.class);
    }

    @DisplayName("로또 validation test")
    @Test
    void test() {
        // given
        Lotto correctLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));

        Assertions.assertThat(correctLotto).isInstanceOf(Lotto.class);
        Assertions.assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호는 중복될 수 없습니다.");
        Assertions.assertThatThrownBy(() -> new Lotto(List.of(0, 1, 2, 3, 4, 5)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("1~45 범위 이내여야 합니다.");
        Assertions.assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호의 개수는 6개여야 합니다.");
    }
}