package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StoreTest {

    @ParameterizedTest
    @MethodSource("buyLottoArguments")
    @DisplayName("로또를 구매한다.")
    void createLotto(LottoMoney money, List<Lotto> manualLottos, int size) {
        assertThat(Store.buyLottos(money, manualLottos)).hasSize(size);
    }

    private static Stream<Arguments> buyLottoArguments() {
        return Stream.of(
                Arguments.of(LottoMoney.createLottoMoney(1_000), Collections.emptyList(), 1),
                Arguments.of(LottoMoney.createLottoMoney(2_000), List.of(givenNumbers(1, 2, 3, 4, 5, 6)), 2),
                Arguments.of(LottoMoney.createLottoMoney(1_000), List.of(givenNumbers(1, 2, 3, 4, 5, 6)), 1)
        );
    }

    private static List<LottoNumber> givenNumbers(int... numbers) {
        return Arrays.stream(numbers)
                .mapToObj(LottoNumber::new)
                .collect(Collectors.toList());
    }

}
