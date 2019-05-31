package model;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Lotto {
    public static final int PRICE = 1000;
    public static final int NUMBER_OF_NUMBERS = 6;
    private static final List<LottoNumber> BALLS = IntStream.rangeClosed(LottoNumber.MIN, LottoNumber.MAX).boxed()
                                                                .map(i -> LottoNumber.of(i))
                                                                .collect(Collectors.toList());

    private final List<LottoNumber> numbers;

    public static Lotto autoGenerate() {
        Collections.shuffle(BALLS);
        return new Lotto(new HashSet<>(BALLS.subList(0, NUMBER_OF_NUMBERS)));
    }

    public Lotto(Set<LottoNumber> numbers) {
        if (numbers.size() != NUMBER_OF_NUMBERS) {
            throw new IllegalArgumentException();
        }
        List<LottoNumber> sorted = new ArrayList<>();
        sorted.addAll(numbers);
        Collections.sort(sorted);
        this.numbers = Collections.unmodifiableList(sorted);
    }

    public Lotto(String input) {
        this(
                Stream.of(input.split(","))
                        .filter(x -> x.trim().length() != 0)
                        .map(i -> LottoNumber.of(i))
                        .collect(Collectors.toSet())
        );
    }

    public Optional<LottoRank> match() {
        Set<LottoNumber> test = new HashSet<>();
        test.addAll(numbers);
        test.removeAll(WinningNumbers.getWinningNumbers());
        return LottoRank.get(Lotto.NUMBER_OF_NUMBERS - test.size(), test.contains(WinningNumbers.getBonusNumber()));
    }

    @Override
    public String toString() {
        return String.valueOf(numbers);
    }
}