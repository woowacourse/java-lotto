package lotto.domain;

import java.util.List;
import java.util.stream.Collectors;

public class Lottoes {
    private final List<Lotto> lottoes;

    public Lottoes(List<Lotto> lottoes) {
        this.lottoes = lottoes;
    }

    public int getLottoesSize(){
        return lottoes.size();
    }

    @Override
    public String toString() {
        return lottoes.stream()
                .map(l -> l.toString())
                .collect(Collectors.joining("\n"));
    }
}
