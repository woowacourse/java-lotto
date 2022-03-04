package domain.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertAll;

import domain.Lotto;
import domain.LottoNumber;
import domain.Lottos;
import domain.service.LottoService;
import java.io.ByteArrayInputStream;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ManualLottoServiceImplTest {
    public static final int LOTTO_SIZE = 6;

    private final LottoService lottoService = new ManualLottoServiceImpl();

    @Test
    @DisplayName("수동 로또 생성 통합 테스트")
    void autoLottoService() {
        // given
        System.setIn(new ByteArrayInputStream("1, 2, 3, 4, 5, 6\n2, 3, 4, 5, 6, 7\n1, 3, 5, 7, 8, 45".getBytes()));
        int expected = 3;

        // when
        Lottos autoLottos = lottoService.createLottosByQuantity(expected);
        List<Lotto> lottos = autoLottos.getLottos();
        int actual = lottos.size();

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("수동 로또 번호 입력 구현부 테스트")
    void getLottoNumbersInManualLottoService() {
        // given
        System.setIn(new ByteArrayInputStream("1, 45, 7, 22, 31, 42\n22, 33, 44, 13, 7, 8\n".getBytes()));

        // when
        List<LottoNumber> lottoNumbers = lottoService.getLottoNumbers();
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
