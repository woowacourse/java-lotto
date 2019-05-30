package lotto.view.validator;

import lotto.domain.exception.InvalidLottoNumberException;
import lotto.view.exception.InputBuyPriceException;
import lotto.view.exception.InputDuplicateException;
import lotto.view.exception.InputLottoFormatException;
import lotto.view.exception.InputManualBuyLottoCountException;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static lotto.domain.LottoNumber.LOTTO_LAST_NUMBER;
import static lotto.domain.LottoNumber.LOTTO_START_NUMBER;

public class InputValidator {
    private static final Pattern BUY_PRICE_REGEX = Pattern.compile("^[1-9][0-9]{3,}$");
    private static final Pattern WINNING_NUMBER_REGEX = Pattern.compile("^([0-9]{1,2}, )+[0-9]{1,2}$");

    public static void inputValidateBuyPrice(final String buyPrice) {
        if (!BUY_PRICE_REGEX.matcher(buyPrice).matches()) {
            throw new InputBuyPriceException("알맞은 가격을 입력해주세요. (1000원 이상, 문자 불가)");
        }
    }

    public static void inputValidateLottoNumber(final String inputLottoNumber) {
        if (!WINNING_NUMBER_REGEX.matcher(inputLottoNumber).matches()) {
            throw new InputLottoFormatException("로또 번호 포멧에 맞지 않습니다.");
        }
        List<Integer> lottoNumbers = Arrays.stream(inputLottoNumber.split(", "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        validateDuplicate(lottoNumbers);
    }

    private static void validateDuplicate(List<Integer> lottoNumbers) {
        if (lottoNumbers.size() != new HashSet<>(lottoNumbers).size()) {
            throw new InputDuplicateException("로또 번호에 중복이 있습니다.");
        }
    }

    public static void inputValidateBonusBall(final int bonusBall) {
        if (!(LOTTO_START_NUMBER < bonusBall && bonusBall <= LOTTO_LAST_NUMBER)) {
            throw new InvalidLottoNumberException("보너스 볼의 범위는 1 ~ 45입니다.");
        }
    }

    public static void inputValidateManualBuyLottoCount(final int buyPrice, final int countOfManualBuyLotto) {
        int maxBuyLotto = buyPrice / 1000;
        if (!(maxBuyLotto - countOfManualBuyLotto >= 0)) {
            throw new InputManualBuyLottoCountException("수동으로 사려는 로또의 숫자가 입력한 가격과 맞지 않습니다. 최대 " + maxBuyLotto + "개");
        }
    }
}
