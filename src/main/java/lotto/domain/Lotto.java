package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Lotto implements Comparable<Lotto> {
    private static final List<Integer> lottoTotalNumbers = new ArrayList<>();
    private static final int BALL_COUNT = 6;
    public static final int LOTTO_PRICE = 1000;
    private static final String ERROR_BALL_COUNT = BALL_COUNT + "개의 숫자를 입력해주세요";
    private static final String ERROR_DUPLICATED_NUMBER = "번호가 중복됩니다!";

    private final List<Ball> lotto = new ArrayList<>();

    static {
        lottoTotalNumbers.addAll(IntStream.range(Ball.MINIMUM_NUMBER, Ball.MAXIMUM_NUMBER + 1)
                .boxed()
                .collect(Collectors.toList()));
    }

    public Lotto(final List<Integer> numbers) {
        validateLotto(numbers);

        for (Integer number : numbers) {
            this.lotto.add(new Ball(number));
        }
    }

    public List<String> getLottoNumbers() {
        return lotto.stream()
                .map(Ball::getNumber)
                .map(String::valueOf)
                .collect(Collectors.toList());
    }

    public boolean contains(final Ball number) {
        return lotto.contains(number);
    }

    public static List<Integer> selectRandomNumbers() {
        List<Integer> lottoNumbers = lottoTotalNumbers;
        Collections.shuffle(lottoNumbers);

        List<Integer> selectedIntNumbers = splitLottoNumbers(lottoNumbers);
        return selectedIntNumbers.stream()
            .sorted()
            .collect(Collectors.toList());
    }

    private static ArrayList<Integer> splitLottoNumbers(final List<Integer> lottoNumbers) {
        return new ArrayList<>(lottoNumbers.subList(0, BALL_COUNT));
    }

    private void validateLotto(final List<Integer> numbers) {
        validateDuplicatedNumber(numbers);
        validateLottoCount(numbers);
    }

    private void validateDuplicatedNumber(final List<Integer> numbers) {
        Set<Integer> distinctNumbers = new HashSet<>(numbers);
        if (distinctNumbers.size() != numbers.size()) {
            throw new IllegalArgumentException(ERROR_DUPLICATED_NUMBER);
        }
    }

    private void validateLottoCount(final List<Integer> numbers) {
        if (numbers.size() != BALL_COUNT) {
            throw new IllegalArgumentException(ERROR_BALL_COUNT);
        }
    }

    @Override
    public int compareTo(final Lotto otherLotto) {
        return (int) lotto.stream()
                .filter(otherLotto::contains)
                .count();
    }
}
