package model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import model.generator.LottoGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class IssuedLottosTest {

    static Stream<Arguments> provideNumbers() {
        return Stream.of(
                Arguments.of(List.of(1,2,3,4,5,5)),
                Arguments.of(List.of(1,2,3,4,5,6,7)),
                Arguments.of(List.of(1,2,3,4,5,46)),
                Arguments.of(List.of(0,1,2,3,4,5)),
                Arguments.of(List.of(-1,1,2,3,4,5))
        );
    }

    @Test
    @DisplayName("수동으로 로또 발급하기")
    void issueManualLotto() {
        IssuedLottos issuedLottos = new IssuedLottos(new Budget(1000),null,List.of(Lotto.of(LottoNumber.convertAll(List.of(1,2,3,4,5,6)))));
        assertThat(issuedLottos.getManualLottoCount()).isEqualTo(1);
    }

    @ParameterizedTest
    @MethodSource("provideNumbers")
    @DisplayName("수동으로 잘못된 로또 발급 시도")
    void checkInvalidManualLotto(List<Integer> numbers) {
        assertThatThrownBy(() -> new IssuedLottos(new Budget(1000),null,List.of(Lotto.of(LottoNumber.convertAll(numbers)))))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("자동으로 로또 발급")
    void issueAutoLotto() {
        Lotto lotto = Lotto.of(LottoNumber.convertAll(List.of(1, 2, 3, 4, 5, 6)));
        IssuedLottos issuedLottos = new IssuedLottos(new Budget(5000), () -> lotto, new ArrayList<>());
        assertThat(issuedLottos.getAutoIssuedLotto().get(0)).isEqualTo(lotto);
        assertThat(issuedLottos.getAutoIssuedLotto().size()).isEqualTo(5);
    }
}
