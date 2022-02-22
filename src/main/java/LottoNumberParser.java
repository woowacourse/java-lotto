import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.stream.Stream;

public class LottoNumberParser {

    public List<Integer> parse(String numbers) {
        String[] tokens = numbers.split(", ");
        return Stream.of(tokens).map(Integer::valueOf).collect(toList());
    }
}
