package lotto.domain;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class LottoFactory {
    
    private LottoFactory() {}
    
    public static Lotto fromNumbers(List<Integer> numbers) {
        Set<LottoNumber> lottoNumbers = numbers.stream()
                                               .map(LottoNumber::from)
                                               .collect(Collectors.toSet());
        
        return Lotto.fromNumbers(lottoNumbers);
    }
}
