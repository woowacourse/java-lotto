package lotto.domain;

import lotto.domain.exception.InvalidCustomGenerateLottoException;
import lotto.util.AscendingNumber;

import java.util.Arrays;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
import java.util.stream.Collectors;

public class CustomGenerateLotto extends Lotto {

    private final static String SPLIT_REGEX = ",";

    public CustomGenerateLotto(String customLotto) {
        this.lottoNumbers = invalidNumberOfLotto(customGenerateLotto(customLotto));
        Collections.sort(lottoNumbers, new AscendingNumber());
    }

    private List<LottoNumber> customGenerateLotto(String customLotto){
        return invalidCustomGenerateLotto(customLotto);
    }

    private List<LottoNumber> invalidCustomGenerateLotto(String customLotto){
        try{
            return Arrays.stream(customLotto.split(SPLIT_REGEX))
                    .mapToInt(Integer::parseInt)
                    .mapToObj(LottoNumber::new)
                    .collect(Collectors.toList());
        } catch (NumberFormatException e){
            throw new InvalidCustomGenerateLottoException(e);
        } catch (InputMismatchException e){
            throw new InvalidCustomGenerateLottoException(e);
        }
    }

}
