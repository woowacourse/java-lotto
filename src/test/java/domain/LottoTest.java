package domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LottoTest {

    private static final LottoFactory lottoFactory = new LottoFactory();

    @Test
    @DisplayName("Lotto 객체가 정상적으로 생성되는 경우")
    void createLotto() {
        Lotto lotto = lottoFactory.generateLotto(List.of(1, 2, 3, 4, 5, 6));

        assertThat(lotto).isNotNull();
    }

    @Test
    @DisplayName("Lotto 객체에 null 값이 들어가는 경우")
    void createLottoCheckNull() {
        assertThatThrownBy(() -> new Lotto(null))
            .isInstanceOf(NullPointerException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"1,2,3,4,5", "1,2,3,4,5,6,7"})
    @DisplayName("Lotto 객체 생성 시 LottoNumber 갯수 유효하지 않은 경우")
    void createLottoNotInSize(String value) {
        List<Integer> lottoNumbers = Arrays.stream(value.split(","))
            .map(Integer::parseInt)
            .collect(Collectors.toList());

        assertThatThrownBy(() -> lottoFactory.generateLotto(lottoNumbers))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Lotto 객체 생성 시 LottoNumber 가 중복되는 경우")
    void createLottoFromDuplicatedNumber() {
        assertThatThrownBy(() -> lottoFactory.generateLotto(List.of(1, 2, 3, 4, 6, 6)))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Lotto와 WinningLotto의 일치 갯수를 확인하는 경우")
    void calculateSameLottoNumber() {
        Lotto purchaseLotto = lottoFactory.generateLotto(List.of(1, 2, 3, 14, 4, 6));
        Lotto winningLotto = lottoFactory.generateLotto(List.of(1, 2, 13, 15, 16, 17));

        int matchCount = purchaseLotto.calculateMatchCount(winningLotto);

        assertThat(matchCount).isEqualTo(2);
    }
}
