package lotto.utils;

import java.util.stream.Stream;

public class Parser {
    public static int[] parsingLottoNumbers(String[] numbers){
        return Stream.of(numbers)
                .mapToInt(Integer::parseInt)
                .toArray();
    }
}
