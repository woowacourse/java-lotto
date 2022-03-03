package domain.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertAll;

import domain.Lotto;
import domain.LottoNumber;
import domain.Lottos;
import domain.service.LottoService;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AutoLottoServiceImplTest {
    public static final int LOTTO_SIZE = 6;

    private final LottoService lottoService = new AutoLottoServiceImpl();

    @Test
    @DisplayName("자동 로또 생성 통합 테스트")
    void autoLottoService() {
        // given
        int expected = 5;

        // when
        Lottos autoLottos = lottoService.createLottosByQuantity(expected);
        List<Lotto> lottos = autoLottos.getLottos();
        int actual = lottos.size();

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("자동 로또 생성 구현부 테스트")
    void getLottoNumbersInAutoLottoService() {
        // given
        List<LottoNumber> lottoNumbers = lottoService.getLottoNumbers();

        // when
        long actual = lottoNumbers.stream()
                .distinct()
                .count();

        // then
        assertAll(
                () -> assertThat(actual).isEqualTo(LOTTO_SIZE),
                () -> assertThatCode(() -> new Lotto(lottoNumbers)).doesNotThrowAnyException()
        );
    }
}
