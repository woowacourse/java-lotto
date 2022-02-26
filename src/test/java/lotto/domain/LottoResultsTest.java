package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;
import lotto.domain.vo.Money;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LottoResultsTest {

    public static final String DISPLAY_NAME_ARGUMENTS = "{displayName} : {arguments}";

    private static final Map<LottoPrize, Integer> INIT_RESULT = new HashMap<>();
    private static final Money LOTTO_PRICE = new Money(1000);

    @BeforeEach
    void setup() {
        for (LottoPrize prize : LottoPrize.values()) {
            INIT_RESULT.put(prize, 0);
        }
    }

    @DisplayName("LottoResults 생성자 테스트")
    @Test
    void lottoResults_constructor_test() {
        Map<LottoPrize, Integer> forResult = new HashMap<>();
        for (LottoPrize prize : LottoPrize.values()) {
            forResult.put(prize, 0);
        }

        assertThatNoException().isThrownBy(() -> new LottoResults(forResult, LOTTO_PRICE));
    }

    @DisplayName("LottoResults null 입력 예외 테스트")
    @Test
    void lottoResults_constructor_error_on_null_test() {
        assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() -> new LottoResults(null, LOTTO_PRICE))
                .withMessage("로또 결과 생성자의 인자는 null이면 안됩니다.");
    }

    @DisplayName("LottoResults 등수 누락 입력 예외 테스트")
    @Test
    void lottoResults_constructor_error_on_key_test() {
        Map<LottoPrize, Integer> result = new HashMap<>(INIT_RESULT);
        result.remove(LottoPrize.FIFTH);

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new LottoResults(result, LOTTO_PRICE))
                .withMessage("로또 결과가 잘못되었습니다.");
    }

    @DisplayName("getRateReturn 메소드 테스트")
    @Test
    void getRateReturn_test() {
        Map<LottoPrize, Integer> result = new HashMap<>(INIT_RESULT);
        result.put(LottoPrize.MISS, 9); // 0
        result.put(LottoPrize.FIFTH, 1); // 5000

        LottoResults lottoResults = new LottoResults(result, LOTTO_PRICE);

        assertThat(lottoResults.getRateReturn()).isEqualTo(0.5);
    }

    @DisplayName("getPrizeNumber 메소드 테스트")
    @ParameterizedTest(name = DISPLAY_NAME_ARGUMENTS)
    @ValueSource(ints = {1, 2, 3})
    void getPrizeNumber_constructor_test(int input) {
        Map<LottoPrize, Integer> result = new HashMap<>(INIT_RESULT);
        result.put(LottoPrize.FIRST, input);

        LottoResults lottoResults = new LottoResults(result, LOTTO_PRICE);

        assertThat(lottoResults.getPrizeNumber(LottoPrize.FIRST)).isEqualTo(input);
    }
}
