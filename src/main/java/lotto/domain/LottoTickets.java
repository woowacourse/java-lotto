package lotto.domain;

import lotto.utils.RandomUtils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoTickets {

    private static final int START_LOTTO_NUMBER = 1;
    private static final int END_LOTTO_NUMBER = 45;
    private static final int LOTTO_SIZE = 6;
    private static final List<Integer> ALL_LOTTO_NUMBERS = IntStream
        .range(START_LOTTO_NUMBER, END_LOTTO_NUMBER + 1).boxed().collect(Collectors.toList());

    private final List<Lotto> lottoTickets;

    public LottoTickets(final int count) {
        this(count, ALL_LOTTO_NUMBERS, null);
    }

    public LottoTickets(final int autoPurchaseCount, final List<Lotto> lottoTickets) {
        this(autoPurchaseCount, ALL_LOTTO_NUMBERS, lottoTickets);
    }

    public LottoTickets(final int count, final List<Integer> auto, final List<Lotto> manual) {
        lottoTickets = createLottoTickets(count, auto, manual);
    }

    private List<Lotto> createLottoTickets(final int autoCount, final List<Integer> auto, final List<Lotto> manual) {
        List<Lotto> lottoTickets = new ArrayList<>();

        for (int i = 0; i < autoCount; i++) {
            lottoTickets.add(new Lotto(RandomUtils.generateRandomNumbers(auto, LOTTO_SIZE)));
        }
        lottoTickets.addAll(manual);

        return Collections.unmodifiableList(lottoTickets);
    }

    public List<Lotto> getLottoTickets() {
        return lottoTickets;
    }

    public Rewords createRewords(final WinningLotto winningLotto) {
        List<Reword> rewords = new ArrayList<>();

        for (Lotto lotto : lottoTickets) {
            rewords.add(winningLotto.match(lotto));
        }

        return new Rewords(rewords);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoTickets that = (LottoTickets) o;
        return Objects.equals(lottoTickets, that.lottoTickets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoTickets);
    }
}
