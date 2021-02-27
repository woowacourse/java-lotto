package lotto.domain.lotto;

import java.util.ArrayList;
import java.util.List;

public class LottoDto {

    private final List<LottoNumber> numbers;

    private LottoDto(List<LottoNumber> numbers) {
        this.numbers = numbers;
    }

    public static LottoDto from(Lotto lotto) {
        List<LottoNumber> numbers = new ArrayList<>();
        for (LottoNumber number : lotto.getNumbers()) {
            numbers.add(number);
        }
        return new LottoDto(numbers);
    }

    public List<LottoNumber> getNumbers() {
        return numbers;
    }
}
