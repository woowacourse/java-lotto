package domain;

import Lotto.domain.Lotto;
import Lotto.domain.LottoNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoTest {
    @Test
    @DisplayName("로또에 대한 입력에 중복이 없고 6개의 옳은 범위의 숫자일 때 init 테스트")
    void createLottoWithLottoNumbers() {
        List<Integer> numbers = Arrays.asList(1, 2, 5, 10, 11, 34);
        List<LottoNumber> lotto = numbers.stream()
                .map((t) -> new LottoNumber(t))
                .collect(Collectors.toList());

        Lotto lottoInstance = new Lotto(lotto);
        assertThat(lottoInstance).isNotNull();
    }

    @Test
    @DisplayName("로또 한 줄에 대한 입력에 중복 제거 후 6개일 때 정상")
    void rightInputInit() {
        List<Integer> numbers = Arrays.asList(1,2,2,3,4,5,6);
        List<LottoNumber> lotto = numbers.stream()
                .map((t) -> new LottoNumber(t))
                .collect(Collectors.toList());

        assertThat(new Lotto(lotto)).isNotNull();
    }

    @Test
    @DisplayName("로또 한 줄에 대한 입력에 중복 제거 후 6개가 아닐 때")
    void wrongInputInit() {
        List<Integer> numbers = Arrays.asList(1,2,2,3,4,5);
        List<LottoNumber> lotto = numbers.stream()
                .map((t) -> new LottoNumber(t))
                .collect(Collectors.toList());

        assertThatThrownBy(() -> new Lotto(lotto))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("중복");
    }
}
