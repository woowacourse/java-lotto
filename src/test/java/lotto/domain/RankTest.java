package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class RankTest {
    private Lotto lotto;
    private Lotto anotherLotto;

    private List<LottoNumber> getLottoNumbers(final List<Integer> numbers) {
        return numbers.stream()
                .map(number -> LottoNumber.of(number))
                .collect(Collectors.toList());
    }

    @Test
    public void 등수_1등() {
        lotto = Lotto.of(getLottoNumbers(Arrays.asList(1, 2, 3, 4, 5, 6)));
        assertThat(Rank.valueOf(lotto.matchCount(lotto), true)).isEqualTo(Rank.FIRST);
    }

    @Test
    public void 등수_2등() {
        lotto = Lotto.of(getLottoNumbers(Arrays.asList(1, 2, 3, 4, 5, 6)));

        List<Integer> anotherNumbers = Arrays.asList(4, 2, 5, 1, 3, 7);
        anotherLotto = Lotto.of(getLottoNumbers(anotherNumbers));

        assertThat(Rank.valueOf(lotto.matchCount(anotherLotto), true)).isEqualTo(Rank.SECOND);
    }


    @Test
    public void 등수_3등() {
        lotto = Lotto.of(getLottoNumbers(Arrays.asList(1, 2, 3, 4, 5, 6)));

        List<Integer> anotherNumbers = Arrays.asList(4, 2, 5, 1, 3, 7);
        anotherLotto = Lotto.of(getLottoNumbers(anotherNumbers));

        assertThat(Rank.valueOf(lotto.matchCount(anotherLotto), false)).isEqualTo(Rank.THIRD);
    }

    @Test
    public void 등수_4등() {
        lotto = Lotto.of(getLottoNumbers(Arrays.asList(1, 2, 3, 4, 5, 6)));

        List<Integer> anotherNumbers = Arrays.asList(4, 2, 5, 1, 8, 7);
        anotherLotto = Lotto.of(getLottoNumbers(anotherNumbers));

        assertThat(Rank.valueOf(lotto.matchCount(anotherLotto), false)).isEqualTo(Rank.FOURTH);
    }

    @Test
    public void 등수_5등() {
        lotto = Lotto.of(getLottoNumbers(Arrays.asList(1, 2, 3, 4, 5, 6)));

        List<Integer> anotherNumbers = Arrays.asList(4, 9, 5, 1, 8, 7);
        anotherLotto = Lotto.of(getLottoNumbers(anotherNumbers));

        assertThat(Rank.valueOf(lotto.matchCount(anotherLotto), false)).isEqualTo(Rank.FIFTH);
    }

    @Test
    public void 당첨안됬을때() {
        lotto = Lotto.of(getLottoNumbers(Arrays.asList(1, 2, 3, 4, 5, 6)));

        List<Integer> anotherNumbers = Arrays.asList(12, 6, 10, 5, 8, 7);
        anotherLotto = Lotto.of(getLottoNumbers(anotherNumbers));

        assertThat(Rank.valueOf(lotto.matchCount(anotherLotto), false)).isEqualTo(Rank.MISS);
    }

    @Test
    public void 당첨금_계산_테스트() {
        lotto = Lotto.of(getLottoNumbers(Arrays.asList(1, 2, 3, 4, 5, 6)));

        List<Integer> anotherNumbers = Arrays.asList(4, 9, 5, 1, 8, 7);
        anotherLotto = Lotto.of(getLottoNumbers(anotherNumbers));

        Rank rank = Rank.valueOf(lotto.matchCount(anotherLotto), true);
        assertThat(rank.prize(10)).isEqualTo(50_000);
    }
}