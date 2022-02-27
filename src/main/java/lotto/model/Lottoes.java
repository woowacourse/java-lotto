package lotto.model;

import static java.util.stream.Collectors.toUnmodifiableList;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

public class Lottoes implements Iterable<Lotto>{

    private final Collection<Lotto> lottoes;

    public Lottoes(List<Lotto> lottoes) {
        this.lottoes = Collections.unmodifiableList(lottoes);
    }

    public <R> Collection<R> mapAndCollect(Function<Lotto, R> mapper) {
        return lottoes.stream().map(mapper).collect(toUnmodifiableList());
    }

    public Stream<Lotto> stream() {
        return lottoes.stream();
    }

    public int size() {
        return lottoes.size();
    }

    @Override
    public Iterator<Lotto> iterator() {
        return lottoes.iterator();
    }
}
