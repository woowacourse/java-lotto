package lotto.domain.vo;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lotto.domain.LottoPrize;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottosTest {

    @DisplayName("구매한 로또들을 가진 객체를 생성한다")
    @Test
    void lottos_constructor_test() {
        // given
        List<LottoNumber> lottoNumbers = List.of(1, 2, 3, 4, 5, 6).stream()
                .map(LottoNumber::valueOf)
                .collect(Collectors.toList());
        List<Lotto> lotto = List.of(new Lotto(lottoNumbers));

        // when
        Lottos lottos = new Lottos(lotto);

        // then
        assertThat(lottos.get()).hasSize(1);
        assertThat(lottos.get()).containsAll(lotto);
    }

    @DisplayName("null가 파라미터로 주어지면 예외를 발생시킨다")
    @Test
    void constructor_range_exception_test() {
        assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() -> new Lottos(null))
                .withMessage("null로 Lottos를 생성할 수 없습니다.");
    }

    @DisplayName("구매한 모든 로또들의 당첨 결과를 반환한다.")
    @Test
    void confirmWinnings_test() {
        // given
        List<LottoNumber> lottoNumbers = List.of(1, 2, 3, 4, 5, 6).stream()
                .map(LottoNumber::valueOf)
                .collect(Collectors.toList());
        LottoNumber bonusNumber = LottoNumber.valueOf(30);
        WinningNumbers winningNumbers = new WinningNumbers(new Lotto(lottoNumbers), bonusNumber);
        List<Lotto> lotto = List.of(new Lotto(lottoNumbers));
        Lottos lottos = new Lottos(lotto);

        // when
        Map<LottoPrize, Integer> lottoMatches = lottos.confirmWinnings(winningNumbers);

        // then
        assertThat(lottoMatches.get(LottoPrize.FIRST)).isEqualTo(1);
    }
}
