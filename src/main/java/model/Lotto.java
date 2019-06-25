package model;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Lotto {
    public static final int PRICE = 1000;
    public static final int NUMBER_OF_PICKS = 6;

    private static final List<LottoNumber> balls = IntStream.rangeClosed(LottoNumber.MIN, LottoNumber.MAX)
                                                            .mapToObj(i -> LottoNumber.of(i))
                                                            .collect(Collectors.toList());

    private final List<LottoNumber> numbers;

    public static Lotto autoGenerate() {
        Collections.shuffle(balls);
        return new Lotto(new HashSet<>(balls.subList(0, NUMBER_OF_PICKS)));
    }

    public Lotto(Set<LottoNumber> numbers) {
        if (numbers.size() != NUMBER_OF_PICKS) {
            throw new IllegalArgumentException();
        }
        this.numbers = new ArrayList<>(numbers).stream()
                                                .sorted()
                                                .collect(Collectors.collectingAndThen(
                                                        Collectors.toList(),
                                                        Collections::unmodifiableList)
                                                );
    }

    public Lotto(String input) {
        this(
            Stream.of(input.split(","))
                    .filter(x -> x.trim().length() != 0)
                    .map(i -> LottoNumber.of(i))
                    .collect(Collectors.toSet())
        );
    }

    public Optional<LottoRank> match(WinningNumbers winningNumbers) {
        Set<LottoNumber> intersectionTest = new HashSet<>(this.numbers);
        intersectionTest.removeAll(winningNumbers.mainNumbers());
        return LottoRank.valueOf(
                Lotto.NUMBER_OF_PICKS - intersectionTest.size(),
                intersectionTest.contains(winningNumbers.bonusNumber())
        );
    }

    public List<LottoNumber> getNumbers() {
        return this.numbers;
    }

    @Override
    public String toString() {
        return String.valueOf(this.numbers);
    }
}