package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LottoResultsTest {

    public static final String DISPLAY_NAME_ARGUMENTS = "{displayName} : {arguments}";

    @DisplayName("LottoResults 생성자 테스트")
    @Test
    void lottoResults_constructor_test() {
        Map<LottoPrize, Integer> forResult = new HashMap<>();
        for (LottoPrize prize : LottoPrize.values()) {
            forResult.put(prize, 0);
        }

        assertThatNoException().isThrownBy(() -> new LottoResults(forResult));
    }

    @DisplayName("LottoResults 생성자 예외 테스트")
    @Test
    void lottoResults_constructor_error_on_null_test() {
        assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() -> new LottoResults(null))
                .withMessage("로또 결과 생성자의 인자는 null이면 안됩니다.");
    }

    @DisplayName("getPrizeNumber 메소드 테스트")
    @ParameterizedTest(name = DISPLAY_NAME_ARGUMENTS)
    @ValueSource(ints = {1, 2, 3})
    void getPrizeNumber_constructor_test(int input) {
        Map<LottoPrize, Integer> map = new HashMap<>();
        map.put(LottoPrize.FIRST, input);

        LottoResults lottoResults = new LottoResults(map);

        assertThat(lottoResults.getPrizeNumber(LottoPrize.FIRST)).isEqualTo(input);
    }
}
