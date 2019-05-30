package lotto.domain;

import java.util.List;
import java.util.Objects;

public class ManualLotto {

    private final LottoTicket lottoTicket;

    public ManualLotto(List<Integer> inputNumbers) {
        this.lottoTicket = WinningLotto.addManualLottoNumbers(inputNumbers);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ManualLotto that = (ManualLotto) o;
        return Objects.equals(lottoTicket, that.lottoTicket);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoTicket);
    }
}
