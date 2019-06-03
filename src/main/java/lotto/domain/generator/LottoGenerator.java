package lotto.domain.generator;

import lotto.domain.Lotto;
import lotto.domain.LottoNumber;

import java.util.*;
import java.util.stream.Collectors;

import static lotto.domain.LottoNumber.getAllLottoNumbers;

public class LottoGenerator {
    public static final String WINNING_NUMBER_DELIMITER = ", ";

    public static Lotto generateAutoLotto() {
        List<LottoNumber> allLottoNumbers = getAllLottoNumbers();
        Collections.shuffle(allLottoNumbers);
        List<LottoNumber> numbers = allLottoNumbers.subList(0, 6);
        numbers.sort(Comparator.comparingInt(LottoNumber::getNumber));
        return new Lotto(numbers);
    }

    public static List<Lotto> generateManualLottos(List<String> inputLottos) {
        List<Lotto> lottos = new ArrayList<>();
        for (String inputLottoNumbers : inputLottos) {
            lottos.add(generateManualLotto(inputLottoNumbers));
        }
        return lottos;
    }

    public static Lotto generateManualLotto(String inputLottoNumbers) {
        return new Lotto(makeLottoNumberList(inputLottoNumbers));
    }

    private static List<LottoNumber> makeLottoNumberList(final String inputLottoNumbers) {
        return Arrays.stream(inputLottoNumbers.split(WINNING_NUMBER_DELIMITER))
                .map(Integer::parseInt)
                .map(LottoNumber::getLottoNumber)
                .collect(Collectors.toList());
    }

}
