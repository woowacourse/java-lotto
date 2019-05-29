package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class LottoServiceTest {

    private List<LottoNumber> convertNumbersToLottoNumbers(final List<Integer> numbers) {
        return numbers.stream()
                .map(number -> LottoNumber.of(number))
                .collect(Collectors.toList());
    }

    @Test
    public void test() {
        List<Integer> winningNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);   // BUY
        List<Integer> fifthNumbers = Arrays.asList(42, 2, 3, 4, 10, 11);  //FIFTH
        List<Integer> numbers6 = Arrays.asList(1, 2, 17, 18, 19, 20);  //MISS

        LottoService service = new LottoService(2000);

        service.buy(fifthNumbers);
        while (service.canBuy()) {
            service.buy(numbers6);
        }

        LottoGameResult gameResult = service.result(winningNumbers, LottoNumber.of(11));
        assertThat(gameResult.profit(LottoMachine.LOTTO_MONEY)).isEqualTo(2.5);
    }
}