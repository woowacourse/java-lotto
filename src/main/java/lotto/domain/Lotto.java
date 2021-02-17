package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Lotto {

    private static final int LOTTO_NUMBER_COUNT = 6;
    private final List<LottoNumber> numbers;

    public Lotto(final List<Integer> numbers) {
        this(numbers.stream().map(LottoNumber::new).collect(Collectors.toList()), true);
    }

    /*
    List<Integer> List<LottoNumber>를 동시에 사용했을 때, Erase~~ 에러를 띄웁니다.
    기존에는 static createByName(List<Integer> numbers)와 같은 방식으로 구현을 진행했었는데요,
    JDK HashSet 의 생성자에 경우 dummy 를 추가하는 방식으로 생성자 오버로딩을 구현해서 이런식으로 구현해봤습니다.
    현업에서는 어떻게 사용되는지 궁금합니다!
     */
    public Lotto(final List<LottoNumber> numbers, boolean dummy) {
        validateNumberCount(numbers);
        validateDistinct(numbers);
        this.numbers = numbers;
    }

    private void validateNumberCount(final List<LottoNumber> numbers) {
        if (numbers.size() != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException("잘못된 개수의 입력입니다.");
        }
    }

    private void validateDistinct(final List<LottoNumber> numbers) {
        if (numbers.stream().distinct().count() != numbers.size()) {
            throw new IllegalArgumentException("중복된 숫자 입력입니다.");
        }
    }

    public int countCommonValue(final Lotto lotto) {
        int totalCount = numbers.size() + lotto.numbers.size();
        Set<LottoNumber> set = new HashSet<>(numbers);
        set.addAll(lotto.numbers);
        return totalCount - set.size();
    }

    public boolean containNumber(final LottoNumber input) {
        return numbers.contains(input);
    }

    public List<Integer> getNumbers() {
        List<LottoNumber> copyNumbers = new ArrayList<>(numbers);
        return Collections.unmodifiableList(copyNumbers.stream()
            .map(LottoNumber::getNumber)
            .sorted()
            .collect(Collectors.toList()));
    }
}
