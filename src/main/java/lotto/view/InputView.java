package lotto.view;

import java.util.Scanner;

public class InputView {
    private Scanner scanner;

    public InputView(){
        this.scanner=new Scanner(System.in);
    }

    public void terminate(){
        scanner.close();
    }

    public String getInput(){
        return scanner.nextLine();
    }
}
