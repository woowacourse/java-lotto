package lotto.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

@Nested
@DisplayName("로또 뭉치는")
class LottosTest {
    @Nested
    @DisplayName("수동으로 구매할 로또가 없다면")
    class if_input_not_manual {
        @ParameterizedTest
        @ValueSource(ints = {1, 3, 5, 7})
        @DisplayName("구매금액에 맞춰 자동으로만 로또를 생성한다.")
        void create_lottos(int count) {
            assertThat(new Lottos(new ArrayList<>(), count).getLottos()).hasSize(count);
        }
    }

    @Nested
    @DisplayName("수동으로 구매할 로또가 있다면")
    class if_input_manual {
        @Test
        @DisplayName("수동으로 구매한 로또에 더해 자동으로 나머지 로또를 생성한다.")
        void create_lottos() {
            List<LottoNumber> manualLottoNumbers = Arrays.asList(1, 2, 3, 4, 5, 6)
                    .stream()
                    .map(LottoNumber::from)
                    .collect(Collectors.toList());
            List<Lotto> manualLottos = new ArrayList<>();
            manualLottos.add(Lotto.createManualLotto(manualLottoNumbers));
            assertThat(new Lottos(manualLottos, 4).getLottos()).hasSize(4);
        }
    }
}
