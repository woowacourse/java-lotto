package domain;


import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottosTest {
    private List<Lotto> lottos = new ArrayList<>();

    @BeforeEach
    void setupLottos() {
        Lotto lottoNumbers1 = new Lotto(IntStream.rangeClosed(1, 6)
                .mapToObj(LottoNumber::getInstance)
                .collect(toList()));
        Lotto lottoNumbers2 = new Lotto(IntStream.rangeClosed(10, 15)
                .mapToObj(LottoNumber::getInstance)
                .collect(toList()));
        Lotto lottoNumbers3 = new Lotto(IntStream.rangeClosed(20, 25)
                .mapToObj(LottoNumber::getInstance)
                .collect(toList()));

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

    @Test
    @DisplayName("Lottos 생성자에 빈 값이 전달됐을 때, IAE 발생")
    void createLottosWithEmptyShouldFail() {
        assertThatThrownBy(() -> new Lottos(Collections.emptyList()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("Lotto 목록이 비었습니다");
    }

    @Test
    @DisplayName("Lottos 생성자에 Null이 전달됐을 때, NPE 발생")
    void createLottosWithNullShouldFail() {
        assertThatThrownBy(() -> new Lottos(null))
                .isInstanceOf(NullPointerException.class)
                .hasMessageMatching("Lotto 목록이 비었습니다");
    }

    @Test
    @DisplayName("두 개의 Lottos를 병합했을 때의 길이는, 전달했던 두 Lottos의 합과 같아야 한다")
    void mergeTwoLottos() {
        // given
        Lottos lottos = new Lottos(this.lottos);
        Lottos anotherLottos = new Lottos(this.lottos);

        // when
        Lottos mergedLottos = lottos.merge(anotherLottos);
        int mergedSize = mergedLottos.getLottos().size();
        int expected = lottos.getLottos().size() + anotherLottos.getLottos().size();

        // then
        assertThat(mergedSize).isEqualTo(expected);
    }
}
