package domain;

import exception.LottoException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import utility.StringUtility;

public class WinningNumber {
    private static final String DUPLICATE_LOTTO_NUMBERS = "당첨번호는 중복될 수 없습니다!";
    private static final String INVALID_WINNING_NUMBER = "유효하지 않은 당첨 번호입니다.";
    private static final int LOTTO_LENGTH = 6;

    private final List<LottoNumber> lottoNumbers;


    public WinningNumber(String inputWinningNumber){
        validateWinningNumber(inputWinningNumber);
        String[] winningNumbers = inputWinningNumber.split(",");
        List<Integer> parsedWinningNumbers = Arrays.stream(winningNumbers)
                .map(((winningNumber) -> Integer.parseInt(winningNumber.trim())))
                .toList();
        lottoNumbers = parsedWinningNumbers.stream().map(LottoNumber::new).toList();
    }

    public boolean isContain(LottoNumber lottoNumber) {
        return lottoNumbers.stream()
                .anyMatch(num -> lottoNumber.equals(num));
    }

    private void validateWinningNumber(String inputWinningNumber) {
        validateIsEmpty(inputWinningNumber);
        String[] winningNumbers = inputWinningNumber.split(",");
        validateSizeCheck(winningNumbers);
        for (int i = 0; i < winningNumbers.length; i++) {
            winningNumbers[i] = winningNumbers[i].trim();
            validateIsNumber(winningNumbers[i]);
        }
        List<Integer> parsedWinningNumbers = Arrays.stream(winningNumbers)
                .map(((winningNumber) -> Integer.parseInt(winningNumber)))
                .toList();
        validateDuplication(parsedWinningNumbers);
    }

    private void validateDuplication(List<Integer> lottoNumbers) {
        Set<Integer> duplicationSet = new HashSet<>(lottoNumbers);
        if(lottoNumbers.size() != duplicationSet.size()) {
            throw new LottoException(DUPLICATE_LOTTO_NUMBERS);
        }
    }

    private static void validateSizeCheck(String[] winningNumbers) {
        if(winningNumbers.length != LOTTO_LENGTH){
            throw new LottoException(INVALID_WINNING_NUMBER);
        }
    }

    private static void validateIsEmpty(String inputWinningNumber) {
        if(inputWinningNumber == null){
            throw new LottoException(INVALID_WINNING_NUMBER);
        }
    }

    private void validateIsNumber(String winningNumber) {
        if(!StringUtility.isNumber(winningNumber)){
            throw new LottoException(INVALID_WINNING_NUMBER);
        }
    }
}
