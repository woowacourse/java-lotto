package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoGeneratorTest {

    LottoGenerator lottoGenerator = new LottoGenerator();

    @Test
    @DisplayName("금액에 맞는 수의 로또를 만드는지 확인")
    void auto() {
        int count = 3;
        LottoGroup lottos = lottoGenerator.autoLotto(count);
        assertThat(lottos.getLottoGroup().size() == count).isTrue();
    }

    @Test
    @DisplayName("입력한 로또 번호대로 로또를 생성하는지 확인")
    void manual() {
        List<String> input = new ArrayList<>(Arrays.asList(
            "1, 2, 3, 4, 5, 6",
            "7, 8, 9, 10, 11, 12"
        ));
        LottoGroup lottos = lottoGenerator.manualLotto(input);

        Lotto one = new Lotto(new HashSet<>(Arrays.asList(
            LottoNumber.of(1),
            LottoNumber.of(2),
            LottoNumber.of(3),
            LottoNumber.of(4),
            LottoNumber.of(5),
            LottoNumber.of(6)
        )));
        Lotto two = new Lotto(new HashSet<>(Arrays.asList(
            LottoNumber.of(7),
            LottoNumber.of(8),
            LottoNumber.of(9),
            LottoNumber.of(10),
            LottoNumber.of(11),
            LottoNumber.of(12)
        )));
        assertThat(lottos.getLottoGroup().contains(one));
        assertThat(lottos.getLottoGroup().contains(two));
    }
}
