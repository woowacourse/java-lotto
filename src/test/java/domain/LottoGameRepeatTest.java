package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class LottoGameRepeatTest {
    @ParameterizedTest
    @ValueSource(ints = {1, 10, 20})
    void 생성_테스트(int value) {
        LottoGameRepeat lottoGameRepeat = new LottoGameRepeat(value);
        Assertions.assertThat(lottoGameRepeat).hasFieldOrPropertyWithValue("repeat", value);
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, -200})
    void 생성_예외_테스트(int value) {
        Assertions.assertThatThrownBy(() -> new LottoGameRepeat(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("횟수는 0 미만으로 입력될 수 없습니다.");
    }

    @ParameterizedTest
    @CsvSource(value = {"10,true", "5,true", "0,false"})
    void 생성_예외_테스트(int value, boolean exp) {
        LottoGameRepeat lottoGameRepeat = new LottoGameRepeat(5);
        Assertions.assertThat(lottoGameRepeat.checkLoopTerminate(value)).isEqualTo(exp);
    }
}