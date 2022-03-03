package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Lotto {

    public static final int PRICE = 1000;
    private static final int LOTTO_SIZE = 6;
    private static final String ERROR_LOTTO_SIZE = "로또는 여섯 개의 숫자를 입력하셔야 합니다.";
    private static final String ERROR_LOTTO_DUPLICATE_BALL = "로또는 중복되는 숫자가 없어야 합니다.";

    private List<LottoBall> lottoBalls;

    public Lotto(List<Integer> numbers) {
        checkRightSize(numbers);
        checkNoDuplicated(numbers);
        lottoBalls = new ArrayList<>();
        numbers.stream().sorted()
                .forEach(number -> lottoBalls.add(new LottoBall(number)));
    }

    public boolean hasBall(LottoBall lottoBall) {
        return lottoBalls.contains(lottoBall);
    }

    public int matchedEachOther(Lotto lotto) {
        return lottoBalls.stream()
                .filter(lotto::hasBall)
                .collect(Collectors.toList())
                .size();
    }

    private void checkRightSize(List<Integer> numbers) {
        if (numbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException(ERROR_LOTTO_SIZE);
        }
    }

    private void checkNoDuplicated(List<Integer> numbers) {
        Set<Integer> uniqueNumber = numbers.stream().collect(Collectors.toSet());
        if (uniqueNumber.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException(ERROR_LOTTO_DUPLICATE_BALL);
        }
    }

    public List<LottoBall> getLottoNumbers() {
        return lottoBalls;
    }

}
