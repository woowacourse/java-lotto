package lotto.model;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toUnmodifiableList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

public class Lottoes implements Iterable<Lotto>{

    private final List<Lotto> lottoes;

    public Lottoes(List<Lotto> lottoes) {
        this.lottoes = Collections.unmodifiableList(lottoes);
    }

    public <R> Collection<R> mapAndCollect(Function<Lotto, R> mapper) {
        return lottoes.stream().map(mapper).collect(toUnmodifiableList());
    }

    public int size() {
        return lottoes.size();
    }

    public Lottoes combine(Lottoes other) {
        return Stream.concat(lottoes.stream(), other.lottoes.stream())
            .collect(collectingAndThen(toList(), Lottoes::new));
    }

    public Money getPrice() {
        return Lotto.PRICE.multiply(lottoes.size());
    }

    @Override
    public Iterator<Lotto> iterator() {
        return lottoes.iterator();
    }
}
