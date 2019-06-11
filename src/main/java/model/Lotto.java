package model;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Lotto {
    public static final int PRICE = 1000;
    public static final int NUMBER_OF_PICKS = 6;
    private static final int RECENT_ROUND = 862;
    private static final LocalDate RECENT_DATE = LocalDate.of(2019, 6, 8);
    private static final int WEEK = 7;

    private static final List<LottoNumber> balls = IntStream.rangeClosed(LottoNumber.MIN, LottoNumber.MAX).boxed()
                                                    .map(i -> LottoNumber.of(i))
                                                    .collect(Collectors.toList());

    private final List<LottoNumber> numbers;

    public static Lotto autoGenerate() {
        Collections.shuffle(balls);
        return new Lotto(new HashSet<>(balls.subList(0, NUMBER_OF_PICKS)));
    }

    public static int recentRound() {
        return RECENT_ROUND + Period.between(RECENT_DATE, LocalDate.now()).getDays() / WEEK;
    }

    public Lotto(Set<LottoNumber> numbers) {
        validation(numbers);
        List<LottoNumber> sorted = new ArrayList<>(numbers);
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

    private void validation(Set<LottoNumber> numbers) {
        if (numbers.size() != NUMBER_OF_PICKS) {
            throw new IllegalArgumentException();
        }
    }


    public Optional<LottoRank> match(WinningNumbers winningNumbers) {
        Set<LottoNumber> intersectionTest = new HashSet<>(this.numbers);
        intersectionTest.removeAll(winningNumbers.mainNumbers());
        return LottoRank.valueOf(
                Lotto.NUMBER_OF_PICKS - intersectionTest.size(),
                intersectionTest.contains(winningNumbers.bonusNumber())
        );
    }

    @Override
    public String toString() {
        return String.valueOf(this.numbers);
    }
}