package lotto.domain.lotto;

import lotto.domain.InvalidCustomGenerateLotto;
import lotto.util.AscendingNumber;

import java.util.Arrays;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
import java.util.stream.Collectors;

public class CustomGenerateLotto extends Lotto {

    public CustomGenerateLotto(String[] customLotto) {
        this.lottoNumbers = invalidNumberOfLotto(customGenerateLotto(customLotto));
        Collections.sort(lottoNumbers, new AscendingNumber());
    }

    private List<LottoNumber> customGenerateLotto(String[] customLotto){
        return invalidCustomGenerateLotto(customLotto);
    }

    private List<LottoNumber> invalidCustomGenerateLotto(String[] customLotto){
        try{
            return Arrays.stream(customLotto)
                    .mapToInt(Integer::parseInt)
                    .mapToObj(LottoNumber::new)
                    .collect(Collectors.toList());
        } catch (NumberFormatException e){
            throw new InvalidCustomGenerateLotto("잘못된 문자를 입력했습니다.");
        } catch (InputMismatchException e){
            throw new InvalidCustomGenerateLotto("정수 외의 수를 입력했습니다.");
        }
    }

}
