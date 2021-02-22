package lotto.view;

import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.domain.PaymentAmount;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class InputView {
    
    private static final String PAY_AMOUNT_QUESTION = "구입금액을 입력해 주세요.";
    
    private static final String WINNING_LOTTO_NUMBER_QUESTION = "지난 주 당첨 번호를 입력해 주세요.";
    
    private static final String BONUS_LOTTO_NUMBER_QUESTION = "보너스 볼을 입력해 주세요.";
    
    private static final String DELIMITER = ",";
    
    private final Scanner scanner;
    
    public InputView() {
        scanner = new Scanner(System.in);
    }
    
    public PaymentAmount readPaymentAmount() {
        String input = read(PAY_AMOUNT_QUESTION);
        
        return PaymentAmount.from(input);
    }
    
    public Lotto readWinningLotto() {
        String input = read(WINNING_LOTTO_NUMBER_QUESTION);
        
        Set<LottoNumber> lottoNumbers = Arrays.stream(input.split(DELIMITER))
                                              .map(LottoNumber::fromStringLottoNumber)
                                              .collect(Collectors.toSet());
        
        return Lotto.fromNumbers(lottoNumbers);
    }
    
    public LottoNumber readBonusNumber() {
        String input = read(BONUS_LOTTO_NUMBER_QUESTION);
        
        return LottoNumber.fromStringLottoNumber(input);
    }
    
    private String read(String question) {
        System.out.println(question);
        
        return scanner.nextLine();
    }
}
