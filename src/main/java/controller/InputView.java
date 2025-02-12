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
        return SC.nextInt();
    }
    
    public List<Integer> inputMatchLotto() {
        String numbersString = SC.next();
        
        String[] numbers = numbersString.split(",");
        
        return Arrays.stream(numbers)
                .map(String::trim)
                .map(Integer::parseInt)
                .toList();
    }
}
