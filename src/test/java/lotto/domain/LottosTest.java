package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LottosTest {
    private List<Lotto> lotto1;
    private List<Lotto> lotto2;
    private List<Lotto> lotto3;

    @BeforeEach
    void setUp() {
        lotto1 = Collections.singletonList(Lotto.from(new String[]{"1", "2", "3", "4", "5", "6"}));
        lotto2 = Collections.singletonList(Lotto.from(new String[]{"7", "8", "9", "10", "11", "12"}));
        lotto3 = new ArrayList<>();
        lotto3.add(Lotto.from(new String[]{"1", "2", "3", "4", "5", "6"}));
        lotto3.add(Lotto.from(new String[]{"7", "8", "9", "10", "11", "12"}));
    }

    @DisplayName("직접 생성한 Lottos와 combineAll 메서드로 만든 Lottos가 같은지 확인")
    @Test
    void testIsEqualSingleLottosAndLottosByCombineAll() {
        List<Lottos> lottos = Arrays.asList(new Lottos(lotto1), new Lottos(lotto2));
        Lottos result = Lottos.combineAll(lottos);
        Lottos expected = new Lottos(lotto3);

        assertThat(result.equals(expected)).isTrue();
    }
}
