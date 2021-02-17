package lotto.domain.lottoData;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoTest {
    @Test
    void 객체_생성() {
        List<Integer> randomValues = Arrays.asList(new Integer[]{8, 21, 23, 41, 42, 43});
        Lotto lotto = new Lotto(randomValues);
        assertThat(lotto).isEqualTo(new Lotto(randomValues));
    }

//    @Test // 나중에 고칠것
//    void 랜덤_숫자_생성() {
//        LottoGenerator.generate(1, 45);
//        for (int i = 0; i < 10; i++) {
//            Lotto lotto = new Lotto(LottoGenerator.makeNumbers());
//            System.out.println(lotto.toString());
//        }
//    }

    @ParameterizedTest
    @CsvSource(value = {"8, 21, 23, 41, 42, 43:7:6",
            "3, 5, 11, 16, 32, 38:8:0",
            "7, 11, 16, 35, 36, 44:5:0",
            "1, 8, 11, 31, 41, 42:3:3"}, delimiter = ':')
    void 일치하는_번호_갯수(String numbers, String bonus, int matchCount) {
        WinningLotto winningLotto = new WinningLotto(numbers, bonus);
        assertThat(lotto.match(winningLotto.values())).isEqualTo(matchCount);
    }

    @Test
    void 보너스가_포함되었는지_테스트() {
        Lotto lotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 7));
        WinningLotto winningLotto = new WinningLotto("1, 2, 3, 4, 5, 6", "7");
        assertThat(lotto.containsBonus(winningLotto)).isTrue();
    }
}
