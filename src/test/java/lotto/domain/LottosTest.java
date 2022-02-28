package lotto.domain;

import static org.assertj.core.api.Assertions.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.vo.LottoNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottosTest {

    private static final Lotto LOTTO = new Lotto(givenNumbers(1, 2, 3, 4, 5, 6));

    @Test
    @DisplayName("로또 개수는 1개 이상이어야 한다.")
    void createLottos() {
        List<Lotto> lottos = List.of(LOTTO);

        assertThat(new Lottos(lottos)).isNotNull();
    }

    @Test
    @DisplayName("로또가 한개라도 없다면 Lottos를 생성시 예외가 발생한다.")
    void throwExceptionWhenEmptyLottos() {
        assertThatIllegalArgumentException()
            .isThrownBy(() -> new Lottos(Collections.emptyList()))
            .withMessage("구매한 로또 개수는 1개 이상이어야 한다.");
    }

    @Test
    @DisplayName("로또들의 등수들을 반환한다.")
    void findRanks() {
        Lottos lottos = new Lottos(List.of(
            new Lotto(givenNumbers(1, 2, 3, 4, 5, 6)),
            new Lotto(givenNumbers(1, 2, 3, 4, 5, 7)),
            new Lotto(givenNumbers(1, 2, 3, 4, 5, 8)),
            new Lotto(givenNumbers(1, 2, 3, 4, 9, 10)),
            new Lotto(givenNumbers(1, 2, 3, 9, 10, 11)),
            new Lotto(givenNumbers(11, 12, 13, 14, 15, 16)))
        );

        List<Rank> ranks = lottos.match(new WinnerLotto(LOTTO, new LottoNumber(7)));

        assertThat(ranks).containsExactly(Rank.FIRST, Rank.SECOND, Rank.THIRD, Rank.FOURTH, Rank.FIFTH, Rank.NONE);
    }

    @Test
    @DisplayName("외부에서 생성된 로또가 변경되어도 생성된 로또에 영향을 주지 않는다.")
    void immutabilityLottos() {
        List<Lotto> lottos = new ArrayList<>();
        lottos.add(LOTTO);

        Lottos newLottos = new Lottos(lottos);
        lottos.add(LOTTO);

        assertThat(newLottos.getLottos()).hasSize(1);
    }

    @Test
    @DisplayName("로또의 개수를 확인한다.")
    void checkAmount() {
        Lottos lottos = new Lottos(List.of(LOTTO));

        assertThat(lottos.getLottos().size()).isEqualTo(1);
    }

    private static List<LottoNumber> givenNumbers(int... numbers) {
        return Arrays.stream(numbers)
            .mapToObj(LottoNumber::new)
            .collect(Collectors.toList());
    }
}
