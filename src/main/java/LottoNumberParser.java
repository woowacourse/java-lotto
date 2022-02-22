import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.stream.Stream;

public class LottoNumberParser {

    private static final int LOTTO_NUMBER_LENGTH = 6;

    static final String INVALID_LOTTO_NUMBER_LENGTH_MESSAGE = "당첨 번호는 6개여야 합니다.";
    static final String DUPLICATED_LOTTO_NUMBER_MESSAGE = "중복된 당첨 번호는 허용하지 않습니다.";

    public List<Integer> parse(String numbers) {
        String[] tokens = numbers.split(", ");
        checkLottoNumberLength(tokens);
        List<Integer> result = Stream.of(tokens).map(Integer::valueOf).collect(toList());
        checkDuplicatedLottoNumber(result);
        return result;
    }

    private void checkLottoNumberLength(String[] tokens) {
        if (tokens.length != LOTTO_NUMBER_LENGTH) {
            throw new IllegalArgumentException(INVALID_LOTTO_NUMBER_LENGTH_MESSAGE);
        }
    }

    private void checkDuplicatedLottoNumber(List<Integer> result) {
        if (result.stream().distinct().count() != result.size()) {
            throw new IllegalArgumentException(DUPLICATED_LOTTO_NUMBER_MESSAGE);
        }
    }
}
