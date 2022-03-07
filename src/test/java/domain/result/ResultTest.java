package domain.result;

import static org.assertj.core.api.Assertions.assertThat;

import domain.lotto.Lotto;
import domain.lotto.LottoFactory;
import domain.lotto.LottoGroup;
import domain.lotto.WinningLotto;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.NumsGenerator;

@SuppressWarnings("NonAsciiCharacters")
class ResultTest {
    private WinningLotto winningLotto;

    @BeforeEach
    void setup() {
        winningLotto = LottoFactory.createWinNums(Arrays.asList(1, 2, 3, 4, 5, 6),
                10);
    }

    @Test
    void 생성자_1등_검사() {
        //given
        Lotto lotto = Lotto.from(NumsGenerator.generate(Arrays.asList(1, 2, 3, 4, 5, 6)));

        //when
        Result result = Result.of(LottoGroup.of(List.of(lotto)), winningLotto);

        //then
        assertThat(result.getRankCount(Rank.FIRST)).isEqualTo(1);
    }

    @Test
    void 생성자_1등_2개_검사() {
        //given
        Lotto lotto = Lotto.from(NumsGenerator.generate(Arrays.asList(1, 2, 3, 4, 5, 6)));

        //when
        Result result = Result.of(LottoGroup.of(List.of(lotto, lotto)), winningLotto);

        //then
        assertThat(result.getRankCount(Rank.FIRST)).isEqualTo(2);
    }

    @Test
    void 생성자_안뽑힘_검사() {
        //given
        Lotto lotto2 = Lotto.from(NumsGenerator.generate(Arrays.asList(1, 2, 7, 8, 9, 11)));
        Lotto lotto1 = Lotto.from(NumsGenerator.generate(Arrays.asList(1, 7, 8, 9, 11, 12)));
        Lotto lotto0 = Lotto.from(NumsGenerator.generate(Arrays.asList(7, 8, 9, 11, 12, 13)));

        //when
        Result result = Result.of(LottoGroup.of(List.of(lotto2, lotto1, lotto0)), winningLotto);

        //then
        assertThat(result.getRankCount(Rank.NONE)).isEqualTo(3);
    }

    @Test
    void 일등_2명_상금_일치_검사() {
        //given
        Lotto lotto = Lotto.from(NumsGenerator.generate(Arrays.asList(1, 2, 3, 4, 5, 6)));

        //when
        Result result = Result.of(LottoGroup.of(List.of(lotto, lotto)), winningLotto);

        assertThat(result.getPrize()).isEqualTo(4000000000L);
    }

    @Test
    void 꼴등_셋_상금_일치_검사() {
        //given
        Lotto lotto2 = Lotto.from(NumsGenerator.generate(Arrays.asList(1, 2, 7, 8, 9, 11)));
        Lotto lotto1 = Lotto.from(NumsGenerator.generate(Arrays.asList(1, 7, 8, 9, 11, 12)));
        Lotto lotto0 = Lotto.from(NumsGenerator.generate(Arrays.asList(7, 8, 9, 11, 12, 13)));

        //when
        Result result = Result.of(LottoGroup.of(List.of(lotto2, lotto1, lotto0)), winningLotto);

        //then
        assertThat(result.getPrize()).isEqualTo(0);
    }
}