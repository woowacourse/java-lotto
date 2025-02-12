package controller;

import java.util.Scanner;

public class InputView {
    
    public final Scanner SC;
    
    public InputView(Scanner SC) {
        this.SC = SC;
    }
    
    public int inputMoney() {
        return SC.nextInt();
    }
}
