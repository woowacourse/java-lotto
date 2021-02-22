package lotto.domain;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomNumberGenerator implements LottoNumberGenerator {
    
    private static final int MIN_LOTTO_NUM = 1;
    private static final int MAX_LOTTO_NUM = 45;
    
    private static final int LOTTO_LENGTH = 6;
    
    private static final List<Integer> NUMBERS = IntStream.rangeClosed(MIN_LOTTO_NUM, MAX_LOTTO_NUM)
                                                          .boxed()
                                                          .collect(Collectors.toList());
    
    @Override
    public Set<Integer> generateNumbers() {
        Collections.shuffle(NUMBERS);
        
        return NUMBERS.stream()
                      .limit(LOTTO_LENGTH)
                      .collect(Collectors.toSet());
    }
}
