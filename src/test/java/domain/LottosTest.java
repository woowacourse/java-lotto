package domain;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

public class LottosTest {
    private List<Lotto> lottos = new ArrayList<>();

    @BeforeEach
    void setupLottos() {
        Lotto lottoNumbers1 = new Lotto(List.of(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                new LottoNumber(4), new LottoNumber(5), new LottoNumber(6)));
        Lotto lottoNumbers2 = new Lotto(List.of(new LottoNumber(13), new LottoNumber(14), new LottoNumber(15),
                new LottoNumber(10), new LottoNumber(11), new LottoNumber(12)));
        Lotto lottoNumbers3 = new Lotto(List.of(new LottoNumber(21), new LottoNumber(22), new LottoNumber(23),
                new LottoNumber(28), new LottoNumber(25), new LottoNumber(45)));

        lottos = List.of(lottoNumbers1, lottoNumbers2, lottoNumbers3);
    }

    @Test
    @DisplayName("List<Lotto> 를 전달받아 Lottos 생성")
    void createLottos() {
        // given
        Lottos validLottos = new Lottos(lottos);

        // when & then
        assertThat(validLottos).isNotNull();
    }

    @ParameterizedTest(name = "{0} 을 전달했을 때")
    @NullAndEmptySource
    @DisplayName("Lottos 생성자에 null또는 빈 값이 전달됐을 때, IAE 발생")
    void createLottosWithNullAndEmptyShouldFail(List<Lotto> lottos) {
        assertThatThrownBy(() -> new Lottos(lottos))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("Lotto 목록이 비었습니다");
    }
}
