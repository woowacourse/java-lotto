package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoGeneratorTest {

    @DisplayName("수동 로또 구매시 입력한 금액보다 더 많은 개수 구매로 인한 에러")
    @Test
    void buyLottosManualOverInputMoney() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> LottoGenerator.of(new Money(14000), 15))
                .withMessage("[ERROR] 금액이 부족합니다.");
    }

    @DisplayName("수동 로또 번호 입력시 구매할 값보다 더 많은 입력을 했을 때 에러")
    @Test
    void manualNumberInputOverManualCountException() {
        final LottoGenerator lottoGenerator = LottoGenerator.of(new Money(14000), 2);

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> lottoGenerator.generateLottos(List.of(List.of(1, 2, 3, 4, 5, 6))))
                .withMessage("[ERROR] 수동 번호가 올바르지 않습니다.");
    }

    @DisplayName("수동, 자동 로또 정상 생성")
    @Test
    void generateLottos() {
        final LottoGenerator lottoGenerator = LottoGenerator.of(new Money(14000), 2);

        Lottos lottos = lottoGenerator.generateLottos(List.of(List.of(1, 2, 3, 4, 5, 6),
                List.of(2, 3, 4, 5, 6, 7)));

        assertThat(lottos.getLottos().size()).isEqualTo(14);

    }
}
