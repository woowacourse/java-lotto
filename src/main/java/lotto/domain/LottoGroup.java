package lotto.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class LottoGroup {
    private final List<Lotto> lottoGroup = new ArrayList<>();

    public void processLottoTicketGeneration(Money money, LottoGenerator lottoGenerator) {
        List<Lotto> lottoTickets = generateLottoTickets(money.getLottoTicketCount(), lottoGenerator);
        addLottoTicketsToGroup(lottoTickets);
    }

    private List<Lotto> generateLottoTickets(int count, LottoGenerator lottoGenerator) {
        return IntStream.range(0, count)
                .mapToObj(i -> generateLotto(lottoGenerator))
                .toList();
    }

    private void addLottoTicketsToGroup(List<Lotto> lottoTickets) {
        lottoGroup.addAll(lottoTickets);
    }

    private Lotto generateLotto(LottoGenerator lottoGenerator) {
        final List<Integer> lottoNumbers = lottoGenerator.generateRandomNumbers();
        return new Lotto(generateLottoNumbers(lottoNumbers));
    }

    private LottoNumbers generateLottoNumbers(List<Integer> lottoNumbers) {
        return new LottoNumbers(lottoNumbers.stream().map(LottoNumber::new).toList());
    }

    public List<Lotto> getLottoGroup() {
        return lottoGroup;
    }
}
