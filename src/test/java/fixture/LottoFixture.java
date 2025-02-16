package fixture;

import domain.Lotto;
import domain.LottoNumber;

import java.util.List;
import java.util.stream.Collectors;

public class LottoFixture {

    public static LottoNumber createLottoNumber(int number) {
        return LottoNumber.from(number);
    }

    public static List<LottoNumber> createLottoNumbers(List<Integer> numbers) {
        return numbers.stream()
                .map(LottoNumber::from)
                .collect(Collectors.toList());
    }

    public static Lotto createLotto(List<Integer> numbers) {
        return Lotto.from(createLottoNumbers(numbers));
    }
}