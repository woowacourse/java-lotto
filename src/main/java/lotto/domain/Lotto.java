package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Lotto {
    private static final int CHECK_BONUS_COUNT = 5;
    private static final int MINIMUM_NUMBER = 1;
    private static final int MAXIMUM_NUMBER = 45;
    private static final int LOTTO_COUNT = 6;
    private static final String ERROR_LOTTO_COUNT = LOTTO_COUNT + "개의 숫자를 입력해주세요";
    private static final String ERROR_DUPLICATED_NUMBER = "번호가 중복됩니다!";

    private final List<Ball> lotto = new ArrayList<>();

    public Lotto() {
        this(selectNumbers());
    }

    public Lotto(final List<String> numbers) {
        checkValidNumbers(numbers);

        for (String number : numbers) {
            this.lotto.add(new Ball(number));
        }
    }

    public List<String> getLottoNumbers() {
        return lotto.stream()
            .map(Ball::toString)
            .collect(Collectors.toList());
    }

    public boolean contains(Ball number) {
        return lotto.contains(number);
    }

    public Rank getRank(WinningLotto winningLotto) {
        int matchingCount = getMatchingCount(winningLotto.getWinningLotto());
        if (matchingCount == CHECK_BONUS_COUNT && lotto.contains(winningLotto.getBonusBall())) {
            return Rank.getRank(matchingCount, true);
        }
        return Rank.getRank(matchingCount, false);
    }

    private int getMatchingCount(Lotto compareLotto) {
        return (int)lotto.stream()
            .filter(compareLotto::contains).count();
    }

    private static List<String> selectNumbers() {
        List<Integer> lottoNumbers = getTotalLottoNumbers();
        Collections.shuffle(lottoNumbers);

        List<Integer> selectedIntNumbers = splitLottoNumbers(lottoNumbers);
        return selectedIntNumbers.stream()
            .sorted()
            .map(Object::toString)
            .collect(Collectors.toList());
    }

    private static ArrayList<Integer> splitLottoNumbers(List<Integer> lottoNumbers) {
        return new ArrayList<>(lottoNumbers.subList(0, LOTTO_COUNT));
    }

    private static List<Integer> getTotalLottoNumbers() {
        return IntStream.range(MINIMUM_NUMBER, MAXIMUM_NUMBER + 1)
            .boxed()
            .collect(Collectors.toList());
    }

    private void checkValidNumbers(final List<String> numbers) {
        checkDuplicatedNumber(numbers);
        checkLottoCount(numbers);
    }

    private void checkLottoCount(List<String> numbers) {
        if (numbers.size() != LOTTO_COUNT) {
            throw new IllegalArgumentException(ERROR_LOTTO_COUNT);
        }
    }

    private void checkDuplicatedNumber(List<String> numbers) {
        Set<String> distinctNumbers = new HashSet<>(numbers);
        if (distinctNumbers.size() != numbers.size()) {
            throw new IllegalArgumentException(ERROR_DUPLICATED_NUMBER);
        }
    }
}
