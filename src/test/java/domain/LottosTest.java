package domain;

import fixture.LottoFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.LottoNumberGenerator;

import java.security.SecureRandom;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class LottosTest {

    private final LottoNumberGenerator lottoNumberGenerator = new LottoNumberGenerator(new SecureRandom());

    @Test
    @DisplayName("Lottos는 여러 개의 Lotto를 포함할 수 있다")
    void Lottos는_여러개의_Lotto를_포함할_수_있다() {
        // given
        int count = 3;

        // when
        Lottos lottos = lottoNumberGenerator.generateLottos(count);

        // then
        assertThat(lottos.getLottoCount())
                .isEqualTo(count);
    }

    @Test
    @DisplayName("Lottos의 리스트는 변경할 수 없다 (불변 테스트)")
    void Lottos의_리스트는_변경할_수_없다() {
        // given
        Lottos lottos = lottoNumberGenerator.generateLottos(3);
        List<Lotto> immutableLottos = lottos.getLottos();

        Lotto lotto = LottoFixture.createLotto(List.of(1, 2, 3, 4, 5, 6));

        // when
        // then
        assertThatThrownBy(() -> immutableLottos.add(lotto))
                .isInstanceOf(UnsupportedOperationException.class);
    }
}
