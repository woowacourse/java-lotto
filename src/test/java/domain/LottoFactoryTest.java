package domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import domain.strategy.LottoGeneratorStrategy;

class LottoFactoryTest {

    @Test
    @DisplayName("로또를 생성하는 기능")
    void createLotto() {
        assertThat(LottoFactory.createLotto(List.of(1, 2, 3, 4, 5, 6))).isNotNull();
    }

    @ParameterizedTest
    @ValueSource(strings = {"-1,2,3,4,5,6", "0,1,2,3,4,5", "1,2,3,4,5,6,7", "1,1,2,3,4,5"})
    @DisplayName("로또를 유효하지 못하게 생성하는 경우")
    void createInvalidLotto(String value) {
        List<Integer> numbers = Arrays.stream(value.split(","))
            .map(Integer::parseInt)
            .collect(Collectors.toList());

        assertThatThrownBy(() -> LottoFactory.createLotto(numbers))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("수동 로또와 랜덤 로또들을 생성하는 기")
    void createLottos() {
        List<List<Integer>> lottoNumbers = List.of(
            List.of(1, 2, 3, 4, 5, 6), List.of(2, 3, 4, 5, 6, 7), List.of(3, 4, 5, 6, 7, 8));
        LottoPurchaseCount purchaseCount = new LottoPurchaseCount(3, 1);
        LottoGeneratorStrategy lottoGeneratorStrategy = () -> List.of(4, 5, 6, 7, 8, 9).stream()
            .map(LottoNumber::valueOf)
            .collect(Collectors.toList());

        assertThat(LottoFactory.generateLottos(lottoNumbers, purchaseCount, lottoGeneratorStrategy)).isNotNull();
    }
}
