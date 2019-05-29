package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoMachineBuyTest {

    LottoMachine machine;

    @BeforeEach
    public void setUp() {
        machine = new LottoMachine(5500);
    }

    private List<LottoNumber> getLottoNumbers(final List<Integer> numbers) {
        return numbers.stream()
                .map(number -> LottoNumber.of(number))
                .collect(Collectors.toList());
    }

    @Test
    public void 로또_기계에서_구입이_잘이루어지는지_검사() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        List<LottoNumber> lottoNumbers = getLottoNumbers(numbers);

        Lotto lotto = machine.buy(numbers);
        assertThat(lotto.equals(Lotto.of(lottoNumbers))).isTrue();
    }

    @Test
    public void 랜덤한_로또를_반환() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        List<LottoNumber> lottoNumbers = getLottoNumbers(numbers);

        Lotto lotto = machine.buy(numbers);
        assertThat(lotto.equals(Lotto.of(lottoNumbers))).isTrue();
    }

    @Test
    public void 남은_로또_횟수_확인() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);

        machine.buy(numbers);
        machine.buy(numbers);
        machine.buy(numbers);
        assertThat(machine.isRemainMoney()).isTrue();
    }
}
