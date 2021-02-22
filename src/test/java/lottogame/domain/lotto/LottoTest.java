package lottogame.domain.lotto;

import lottogame.utils.DuplicateLottoNumberException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoTest {
    private Lotto lotto;

    @BeforeEach
    void setUp() {
        List<Integer> values = Arrays.asList(8, 21, 23, 41, 42, 43);
        List<LottoNumber> lottoNumbers = values.stream()
                .map(value -> new LottoNumber(value))
                .collect(Collectors.toList());
        lotto = new Lotto(lottoNumbers);
    }

    @DisplayName("로또의 당첨 개수가 올바르게 체크되는지 테스트")
    @ParameterizedTest
    @CsvSource(value = {"8, 21, 23, 41, 42, 43:7:6",
            "3, 5, 11, 16, 32, 38:8:0",
            "7, 11, 16, 35, 36, 44:5:0",
            "1, 8, 11, 31, 41, 42:3:3"}, delimiter = ':')
    void 일치하는_번호_갯수(String values, int bonus, int matchCount) {
        List<Integer> numbers = Arrays.stream(values.split(", "))
                .mapToInt(value -> Integer.parseInt(value))
                .boxed()
                .collect(Collectors.toList());
        WinningLotto winningLotto = new WinningLotto(numbers, bonus);
        assertThat(lotto.matchNumberCount(winningLotto)).isEqualTo(matchCount);
    }

    @Test
    void 중복된_로또_번호_테스트() {
        List<LottoNumber> lottoNumbers = new ArrayList<>();
        lottoNumbers.add(new LottoNumber(1));
        lottoNumbers.add(new LottoNumber(2));
        lottoNumbers.add(new LottoNumber(3));
        lottoNumbers.add(new LottoNumber(4));
        lottoNumbers.add(new LottoNumber(5));
        lottoNumbers.add(new LottoNumber(2));
        assertThatThrownBy(() -> new Lotto(lottoNumbers))
                .isInstanceOf(DuplicateLottoNumberException.class);
    }

    @Test
    void 정상적인_로또_번호_테스트() {
        List<LottoNumber> lottoNumbers = new ArrayList<>();
        lottoNumbers.add(new LottoNumber(1));
        lottoNumbers.add(new LottoNumber(2));
        lottoNumbers.add(new LottoNumber(3));
        lottoNumbers.add(new LottoNumber(4));
        lottoNumbers.add(new LottoNumber(5));
        lottoNumbers.add(new LottoNumber(6));
        assertThat(new Lotto(lottoNumbers)).isEqualTo(new Lotto(lottoNumbers));
    }
}
