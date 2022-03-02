package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LottoFactory {

    public List<Lotto> generateLottoTicket(List<List<Integer>> manualLottoNumbers, int autoLottoQuantity) {
        List<Lotto> lottoTicket = new ArrayList<>(generateManualLottoTicket(manualLottoNumbers));
        lottoTicket.addAll(generateAutoLottoTicket(autoLottoQuantity));
        return lottoTicket;
    }

    private List<Lotto> generateManualLottoTicket(List<List<Integer>> manualLottoNumbers) {
        return manualLottoNumbers.stream()
                .map(Lotto::from)
                .collect(Collectors.toList());
    }

    private List<Lotto> generateAutoLottoTicket(int autoLottoQuantity) {
        List<Lotto> autoLottoTicket = new ArrayList<>();
        while (autoLottoQuantity-- > 0) {
            autoLottoTicket.add(generateLottoAutomatically());
        }
        return autoLottoTicket;
    }

    private Lotto generateLottoAutomatically() {
        List<Number> numbers = new ArrayList<>(Number.values());
        Collections.shuffle(numbers);
        return new Lotto(new ArrayList<>(pickLottoNumbers(numbers)));
    }

    private List<Number> pickLottoNumbers(List<Number> numbers) {
        return numbers.subList(0, 6);
    }
}
