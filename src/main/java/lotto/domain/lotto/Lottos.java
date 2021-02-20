package lotto.domain.lotto;

import lotto.domain.reword.Reword;
import lotto.domain.reword.Rewords;
import lotto.utils.RandomUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Lottos {

    private static final int START_LOTTO_NUMBER = 1;
    private static final int END_LOTTO_NUMBER = 45;
    private static final int LOTTO_SIZE = 6;
    private static final List<Integer> ALL_LOTTO_NUMBERS = IntStream
        .range(START_LOTTO_NUMBER, END_LOTTO_NUMBER + 1).boxed().collect(Collectors.toList());

    private final List<Lotto> lottos;

    public Lottos(final int count) {
        this(count, ALL_LOTTO_NUMBERS, null);
    }

    public Lottos(final int autoPurchaseCount, final List<Lotto> manual) {
        this(autoPurchaseCount, ALL_LOTTO_NUMBERS, manual);
    }

    public Lottos(final int count, final List<Integer> auto, final List<Lotto> manual) {
        lottos = createLottoTickets(count, auto, manual);
    }

    private List<Lotto> createLottoTickets(final int autoCount, final List<Integer> auto, final List<Lotto> manual) {
        List<Lotto> lottoTickets = new ArrayList<>();

        for (int i = 0; i < autoCount; i++) {
            lottoTickets.add(new Lotto(RandomUtils.generateRandomNumbers(auto, LOTTO_SIZE)));
        }

        if (!Objects.isNull(manual)) {
            lottoTickets.addAll(manual);
        }

        return Collections.unmodifiableList(lottoTickets);
    }

    public Rewords createRewords(final WinningLotto winningLotto) {
        List<Reword> rewords = new ArrayList<>();

        for (Lotto lotto : lottos) {
            rewords.add(winningLotto.matchAll(lotto));
        }

        return new Rewords(rewords);
    }

    public List<Lotto> getLottos() {
        return lottos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lottos that = (Lottos) o;
        return Objects.equals(lottos, that.lottos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottos);
    }
}
