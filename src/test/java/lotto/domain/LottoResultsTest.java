package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatNoException;

import java.util.EnumMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LottoResultsTest {

    public static final String DISPLAY_NAME_ARGUMENTS = "{displayName} : {arguments}";

    @DisplayName("LottoResults 생성자는 당첨 결과 맵을 입력받아 값을 초기화한다.")
    @Test
    void constructor() {
        Map<LottoPrize, Integer> forResult = new EnumMap<>(LottoPrize.class);
        for (LottoPrize prize : LottoPrize.values()) {
            forResult.put(prize, 0);
        }

        assertThatNoException().isThrownBy(() -> new LottoResults(forResult));
    }

    @DisplayName("LottoResults 생성자는 입력받은 맵이 null일 경우 예외를 발생한다.")
    @Test
    void constructor_errorOnNull() {
        assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() -> new LottoResults(null))
                .withMessage("로또 결과 생성자의 인자는 null이면 안됩니다.");
    }

    @DisplayName("getRateReturn 메서드는 당첨 결과에 따른 수익률을 반환한다.")
    @Test
    void getRateReturn() {
        Map<LottoPrize, Integer> result = new EnumMap<>(LottoPrize.class);
        result.put(LottoPrize.MISS, 9); // 0
        result.put(LottoPrize.FIFTH, 1); // 5000

        LottoResults lottoResults = new LottoResults(result);

        assertThat(lottoResults.getRateReturn()).isEqualTo(0.5);
    }

    @DisplayName("getPrizeNumber 메서드는 당첨 결과에서 입력받은 LottoPrize의 개수를 반환한다.")
    @ParameterizedTest(name = DISPLAY_NAME_ARGUMENTS)
    @ValueSource(ints = {1, 2, 3})
    void getPrizeNumber(int input) {
        Map<LottoPrize, Integer> map = new EnumMap<>(LottoPrize.class);
        map.put(LottoPrize.FIRST, input);

        LottoResults lottoResults = new LottoResults(map);

        assertThat(lottoResults.getPrizeNumber(LottoPrize.FIRST)).isEqualTo(input);
    }
}
