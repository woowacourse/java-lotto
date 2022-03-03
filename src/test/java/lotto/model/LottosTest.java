package lotto.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class LottosTest {

    private Lotto lotto1;
    private Lotto lotto2;
    private Lotto lotto3;
    private List<Lotto> lottoList;

    @BeforeEach
    void init() {
        lotto1 = new Lotto(convertList(List.of(1, 2, 3, 4, 5, 7)));
        lotto2 = new Lotto(convertList(List.of(1, 2, 3, 4, 5, 7)));
        lotto3 = new Lotto(convertList(List.of(1, 2, 3, 4, 5, 6)));
        lottoList = new ArrayList<>(List.of(lotto1, lotto2, lotto3));
    }

    @Test
    @DisplayName("Lottos에 Lotto 저장 테스트")
    void lottosSaveTest() {
        Lottos lottos = new Lottos(lottoList);

        assertThat(lottos.getLottos())
            .hasSize(3)
            .containsExactly(lotto1, lotto2, lotto3);
    }

    @ParameterizedTest
    @CsvSource(value = {
        "FIRST,1",
        "SECOND,2"
    })
    @DisplayName("로또의 등수 계산하는 테스트")
    void countLottoRankTest(String name, int expected) {
        Lottos lottos = new Lottos(lottoList);
        WinningLotto winningLotto = new WinningLotto(new Lotto(convertList(List.of(1, 2, 3, 4, 5, 6))), new LottoNumber(7));
        LottoStatistics statistics = lottos.checkRank(winningLotto);

        assertThat(statistics.getRankMap().get(Rank.valueOf(name)))
            .isEqualTo(expected);
    }

    List<LottoNumber> convertList(List<Integer> numbers) {
        return numbers.stream()
            .map(LottoNumber::new)
            .collect(Collectors.toList());
    }
}