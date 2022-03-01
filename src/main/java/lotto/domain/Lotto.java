package lotto.domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lotto.validator.LottoValidator;

public class Lotto {

    private static final String INPUT_NUMBERS_DELIMITER = ",";

    private final List<LottoNumber> lottoNumbers;

    private Lotto(List<LottoNumber> lottoNumbers) {
        LottoValidator.validate(lottoNumbers);
        this.lottoNumbers = List.copyOf(lottoNumbers);
    }

    public static Lotto generateLottoByAuto() {
        return new Lotto(LottoNumber.getRandomLottoNumbers());
    }

    public static Lotto generateLottoByConsole(String consoleInput) {
        return new Lotto(receiveThroughConsole(consoleInput));
    }

    private static List<LottoNumber> receiveThroughConsole(String consoleInput) {
        return convertToLottoNumbers(parseConsoleInput(consoleInput));
    }

    private static List<Integer> parseConsoleInput(String consoleInput) {
        return Arrays.stream(consoleInput.split(INPUT_NUMBERS_DELIMITER))
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    private static List<LottoNumber> convertToLottoNumbers(List<Integer> numbers) {
        return numbers.stream()
                .map(LottoNumber::findByNumber)
                .sorted()
                .collect(Collectors.toList());
    }

    public Rank getRank(WinningLotto winningLotto, BonusNumber bonusNumber) {
        int matchCount = getMatchCount(winningLotto);
        boolean contains = isContain(bonusNumber.getBonusNumber());
        return Rank.getRank(matchCount, contains);
    }

    private int getMatchCount(WinningLotto winningLotto) {
        return (int) lottoNumbers.stream()
                .filter(winningLotto.getWinningLotto()::isContain)
                .count();
    }

    public boolean isContain(LottoNumber bonusNumber) {
        return lottoNumbers.contains(bonusNumber);
    }

    @Override
    public String toString() {
        return lottoNumbers.toString();
    }
}
