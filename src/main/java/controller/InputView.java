package controller;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    
    public final Scanner SC;
    
    public InputView(Scanner SC) {
        this.SC = SC;
    }
    
    public int inputMoney() {
        String moneyString = SC.nextLine();
        return Integer.parseInt(moneyString);
    }
    
    public List<Integer> inputMatchLotto() {
        String numbersString = SC.nextLine();
        
        String[] numbers = numbersString.split(",");
        
        return Arrays.stream(numbers)
                .map(String::trim)
                .map(Integer::parseInt)
                .toList();
    }
    
    public int inputBonusNumber() {
        String bonusString = SC.nextLine();
        return Integer.parseInt(bonusString);
    }
}
