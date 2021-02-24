package lotto.domain;

import lotto.domain.generator.LottoNumberGenerator;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Lotto {
    
    private static final int LOTTO_LENGTH = 6;
    
    private static final String ERROR_ILLEGAL_LOTTO_LENGTH = "로또 번호의 길이가 6이 아닙니다.";
    
    private final Set<LottoNumber> lottoNumbers;
    
    private Lotto(Set<LottoNumber> lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
    }
    
    public static Lotto fromGenerator(LottoNumberGenerator generator) {
        final Set<Integer> numbers = generator.generateNumbers();
        
        return fromNumbers(mapIntToLottoNumber(numbers));
    }
    
    public static Lotto fromNumbers(Set<LottoNumber> lottoNumbers) {
        if (matchesLength(lottoNumbers.size())) {
            return new Lotto(lottoNumbers);
        }
        
        throw new IllegalArgumentException(ERROR_ILLEGAL_LOTTO_LENGTH);
    }
    
    private static boolean matchesLength(int size) {
        return size == LOTTO_LENGTH;
    }
    
    private static Set<LottoNumber> mapIntToLottoNumber(Set<Integer> numbers) {
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
