package lotto.domain;

import static lotto.common.Constants.LOTTO_NUM_SIZE;
import static lotto.common.Constants.MAX_LOTTO_NUMBER;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import lotto.utils.RandomNumberUtils;

public class LottoGroup {
    private final List<Lotto> item = new ArrayList<>();

    public void generate(Money money) {
        IntStream.range(0, money.getLottoTicketCount())
                .mapToObj(index -> createLotto())
                .forEach(item::add);
    }

    public List<Lotto> getItem() {
        return item;
    }

    private Lotto createLotto() {
        List<Integer> randomNumbers = generateRandomNumbers();
        List<LottoNumber> lottoNumbers = convertToLottoNumbers(randomNumbers);

        return Lotto.from(LottoNumbers.from(lottoNumbers));
    }

    private List<Integer> generateRandomNumbers() {
        return RandomNumberUtils.generateRandomNumbers(LOTTO_NUM_SIZE, MAX_LOTTO_NUMBER);
    }

    private List<LottoNumber> convertToLottoNumbers(List<Integer> numbers) {
        return numbers.stream()
                .map(LottoNumber::new)
                .toList();
    }
}
