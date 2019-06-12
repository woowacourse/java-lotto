package lotto.domain;

import lotto.domain.exception.InvalidCustomGenerateLottoException;
import lotto.util.AscendingNumber;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;

import static java.util.Collections.sort;
import static java.util.stream.Collectors.*;
import static lotto.view.InputView.SPLIT_REGEX;

public class CustomGenerateLotto extends Lotto {

    public CustomGenerateLotto(String customLotto) {
        this.lottoNumbers = invalidNumberOfLotto(customGenerateLottoNumbers(customLotto));
        sort(lottoNumbers, new AscendingNumber());
    }

    private List<LottoNumber> customGenerateLottoNumbers(String customLotto){
        return invalidCustomGenerateLottoNumbers(customLotto);
    }

    private List<LottoNumber> invalidCustomGenerateLottoNumbers(String customLotto){
        try{
            return Arrays.stream(customLotto.split(SPLIT_REGEX))
                    .mapToInt(Integer::parseInt)
                    .mapToObj(LottoNumber::new)
                    .collect(toList());
        } catch (NumberFormatException e){
            throw new InvalidCustomGenerateLottoException(e);
        } catch (InputMismatchException e){
            throw new InvalidCustomGenerateLottoException(e);
        }
    }

}
