package lotto.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Lotto {
    
    private static final int LOTTO_LENGTH = 6;
    
    private final Set<LottoNumber> lottoNumbers;
    
    private Lotto(Set<LottoNumber> lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
    }
    
    public static Lotto fromGenerator(LottoNumberGenerator generator) {
        return fromNumbers(generator.generateNumbers());
    }
    
    public static Lotto fromNumbers(List<Integer> numbers) {
        Set<LottoNumber> lottoNumbers = mapIntToLottoNumber(numbers);
        
        if (!matchesLength(lottoNumbers.size())) {
            throw new IllegalArgumentException();
        }
        
        return new Lotto(lottoNumbers);
    }
    
    private static boolean matchesLength(int size) {
        return size == LOTTO_LENGTH;
    }
    
    private static Set<LottoNumber> mapIntToLottoNumber(List<Integer> numbers) {
        return numbers.stream()
                      .map(LottoNumber::from)
                      .collect(Collectors.toSet());
    }
    
    public long countMatchingNumber(Lotto lotto) {
        return this.lottoNumbers.stream()
                                .filter(lotto::contains)
                                .count();
    }
    
    public boolean contains(LottoNumber number) {
        return lottoNumbers.contains(number);
    }
    
    public Set<LottoNumber> getLottoNumbers() {
        return new HashSet<>(lottoNumbers);
    }
}
