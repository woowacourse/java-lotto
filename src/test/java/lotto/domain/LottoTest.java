package lotto.domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

public class LottoTest {

    @Test
    public void 로또번호_개수_검증() {
        List<LottoNumber> lottoNumbers = Arrays.asList(1, 2, 3, 4)
                .stream()
                .map(LottoNumber::new)
                .collect(Collectors.toList());
        assertThatThrownBy(() -> new Lotto(lottoNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @Test
    public void 로또번호_중복_검증() {
        List<LottoNumber> lottoNumbers = Arrays.asList(1, 2, 3, 4, 5, 5)
                .stream()
                .map(LottoNumber::new)
                .collect(Collectors.toList());
        assertThatThrownBy(() -> new Lotto(lottoNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6})
    public void 로또번호_생성_성공(int number) {
        List<LottoNumber> lottoNumbers = Arrays.asList(1, 2, 3, 4, 5, 6)
                .stream()
                .map(LottoNumber::new)
                .collect(Collectors.toList());
        Lotto lotto = new Lotto(lottoNumbers);
        assertThat(lotto.contains(new LottoNumber(number))).isTrue();
    }
}
