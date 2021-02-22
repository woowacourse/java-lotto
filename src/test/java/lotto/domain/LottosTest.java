package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class LottosTest {

    @Test
    @DisplayName("구입한 로또 매수만큼 로또 생성")
    void createLottos() {
        int expectedLottoSize = 14;
        Lottos lottos = new Lottos(expectedLottoSize);
        assertThat(lottos.getSize()).isEqualTo(expectedLottoSize);
    }
}