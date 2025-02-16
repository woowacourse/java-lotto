package service;

import domain.LottoRule;
import domain.Lottos;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.security.SecureRandom;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoNumberGeneratorTest {

    private final LottoNumberGenerator lottoNumberGenerator = new LottoNumberGenerator(new SecureRandom());

    @Test
    @DisplayName("요청된 개수만큼 로또를 생성해야 한다")
    void 로또_개수만큼_생성되어야_한다() {
        // given
        int repeatCount = 5;

        // when
        Lottos lottos = lottoNumberGenerator.generateLottos(repeatCount);

        // then
        assertThat(lottos.getLottoCount())
                .isEqualTo(repeatCount);
    }

    @Test
    @DisplayName("각 로또는 정해진 개수를 가져야 한다")
    void 각_로또는_정해진_개수를_가져야_한다() {
        // given
        int repeatCount = 5;

        // when
        Lottos lottos = lottoNumberGenerator.generateLottos(repeatCount);

        // then
        lottos.getLottos().forEach(lotto ->
                assertThat(lotto.getNumbers()).hasSize(LottoRule.LOTTO_SELECTION_SIZE.getValue())
        );
    }

    @Test
    @DisplayName("각 로또는 중복된 숫자를 가지면 안 된다")
    void 각_로또는_중복된_숫자를_가지면_안된다() {
        // given
        int repeatCount = 10;

        // when
        Lottos lottos = lottoNumberGenerator.generateLottos(repeatCount);

        // then
        lottos.getLottos().forEach(lotto ->
                assertThat(lotto.getNumbers().stream().distinct().count())
                        .isEqualTo(LottoRule.LOTTO_SELECTION_SIZE.getValue())
        );
    }

    @Test
    @DisplayName("각 로또는 정해진 범위의 숫자만 포함해야 한다")
    void 각_로또는_정해진_범위의_숫자만_포함해야_한다() {
        // given
        int repeatCount = 10;

        // when
        Lottos lottos = lottoNumberGenerator.generateLottos(repeatCount);

        // then
        lottos.getLottos().forEach(lotto ->
                assertThat(lotto.getNumbers()).allMatch(number ->
                        number.getValue() >= LottoRule.MIN_LOTTO_NUMBER.getValue() &&
                                number.getValue() <= LottoRule.MAX_LOTTO_NUMBER.getValue()
                )
        );
    }

    @Test
    @DisplayName("잘못된 반복 횟수 입력 시 예외가 발생해야 한다")
    void 잘못된_반복_횟수_입력시_예외가_발생해야_한다() {
        // given
        int invalidRepeatCount = 0;

        // when
        // then
        assertThatThrownBy(() -> lottoNumberGenerator.generateLottos(invalidRepeatCount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("회 이상 반복해야 합니다.");
    }
}
