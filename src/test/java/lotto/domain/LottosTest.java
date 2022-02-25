package lotto.domain;

import static lotto.domain.LottoTest.createLottoNumbers;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottosTest {

    private static Lottos createLottos() {
        return new Lottos(Stream.of(createLottoNumbers(1, 2, 3, 4, 5, 6),
                createLottoNumbers(2, 3, 4, 5, 6, 7))
                .map(Lotto::new)
                .collect(Collectors.toList()));
    }

    @DisplayName("null로 객체생성하면 예외 발생")
    @Test
    void nullLottosException() {
        assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() -> new Lottos(null))
                .withMessage("[ERROR] Lotto는 null로 생성할 수 없습니다.");
    }

    @DisplayName("LottoResult 로또 결과 객체를 계산하여 반환할 수 있다.")
    @Test
    void createLottoResult() {
        // given
        final WinLotto winLotto = new WinLotto(new Lotto(createLottoNumbers(1, 2, 3, 4, 5, 6)), LottoNumber.valueOf(7));
        final Lottos lottos = createLottos();

        final Map<Rank, Integer> resultMap = Rank.createInitResultMap();
        resultMap.put(Rank.FIRST, 1);
        resultMap.put(Rank.SECOND, 1);
        final LottoResult expected = new LottoResult(resultMap);

        // when
        final LottoResult result = lottos.createResult(winLotto);

        // then
        assertThat(result).isEqualTo(expected);
    }
}
