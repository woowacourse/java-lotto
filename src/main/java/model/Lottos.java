package model;

import java.util.List;
import java.util.stream.Stream;

public class Lottos {
    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public List<Lotto> getLottos() {
        return lottos;
    }

    public int computeTicketCount() {
        return lottos.size();
    }

    // 스트림을 반환하는 메서드 추가
    public Stream<Lotto> stream() {
        return lottos.stream();
    }
}
