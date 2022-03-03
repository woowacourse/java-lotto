package domain.result;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.lotto.Lotto;
import domain.lotto.LottoFactory;
import domain.lotto.LottoNumber;
import domain.lotto.WinNumbers;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.NumsGenerator;

@SuppressWarnings("NonAsciiCharacters")
class ResultTest {
    private WinNumbers winNumbers;

    @BeforeEach
    void setup() {
        winNumbers = LottoFactory.createWinNums(NumsGenerator.generate(Arrays.asList(1, 2, 3, 4, 5, 6)),
                LottoNumber.getInstance(10));
    }

    @Test
    void 생성자_빈로또_에러() {
        //given
        Lotto lotto = null;

        //then
        assertThatThrownBy(() -> Result.of(List.of(lotto), winNumbers))
                .isInstanceOf(Exception.class);
    }

    @Test
    void 생성자_1등_검사() {
        //given
        Lotto lotto = LottoFactory.createLotto(NumsGenerator.generate(Arrays.asList(1, 2, 3, 4, 5, 6)));

        //when
        Result result = Result.of(List.of(lotto), winNumbers);

        //then
        assertThat(result.get().get(Rank.FIRST)).isEqualTo(1);
    }

    @Test
    void 생성자_1등_2개_검사() {
        //given
        Lotto lotto = LottoFactory.createLotto(NumsGenerator.generate(Arrays.asList(1, 2, 3, 4, 5, 6)));

        //when
        Result result = Result.of(Arrays.asList(lotto, lotto), winNumbers);

        //then
        assertThat(result.get().get(Rank.FIRST)).isEqualTo(2);
    }

    @Test
    void 생성자_안뽑힘_검사() {
        //given
        Lotto lotto2 = LottoFactory.createLotto(NumsGenerator.generate(Arrays.asList(1, 2, 7, 8, 9, 11)));
        Lotto lotto1 = LottoFactory.createLotto(NumsGenerator.generate(Arrays.asList(1, 7, 8, 9, 11, 12)));
        Lotto lotto0 = LottoFactory.createLotto(NumsGenerator.generate(Arrays.asList(7, 8, 9, 11, 12, 13)));

        //when
        Result result = Result.of(Arrays.asList(lotto2, lotto1, lotto0), winNumbers);

        //then
        assertThat(result.get().get(Rank.NONE)).isEqualTo(3);
    }

    @Test
    void 일등_2명_상금_일치_검사() {
        //given
        Lotto lotto = LottoFactory.createLotto(NumsGenerator.generate(Arrays.asList(1, 2, 3, 4, 5, 6)));

        //when
        Result result = Result.of(Arrays.asList(lotto, lotto), winNumbers);

        assertThat(result.getPrize()).isEqualTo(4000000000L);
    }

    @Test
    void 꼴등_셋_상금_일치_검사() {
        //given
        Lotto lotto2 = LottoFactory.createLotto(NumsGenerator.generate(Arrays.asList(1, 2, 7, 8, 9, 11)));
        Lotto lotto1 = LottoFactory.createLotto(NumsGenerator.generate(Arrays.asList(1, 7, 8, 9, 11, 12)));
        Lotto lotto0 = LottoFactory.createLotto(NumsGenerator.generate(Arrays.asList(7, 8, 9, 11, 12, 13)));

        //when
        Result result = Result.of(Arrays.asList(lotto2, lotto1, lotto0), winNumbers);

        //then
        assertThat(result.getPrize()).isEqualTo(0);
    }
}