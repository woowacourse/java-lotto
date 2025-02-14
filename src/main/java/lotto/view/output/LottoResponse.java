package lotto.view.output;

import java.util.List;
import lotto.model.lotto.Lotto;

public class LottoResponse {

    private final List<Integer> numbers;

    public LottoResponse(final Lotto lotto) {
        this.numbers = lotto.getNumbers();
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    @Override
    public String toString() {
        return "LottoResponse{" +
                "numbers=" + numbers +
                '}';
    }
}
