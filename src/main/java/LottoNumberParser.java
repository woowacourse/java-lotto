import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.stream.Stream;

public class LottoNumberParser {
    private static final int LOTTO_NUMBER_LENGTH = 6;

    public List<Integer> parse(String numbers) {
        String[] tokens = numbers.split(", ");
        checkLottoNumberLength(tokens);
        return Stream.of(tokens).map(Integer::valueOf).collect(toList());
    }

    private void checkLottoNumberLength(String[] tokens) {
        if (tokens.length != LOTTO_NUMBER_LENGTH) {
            throw new IllegalArgumentException("당첨 번호는 6개여야 합니다.");
        }
    }
}
