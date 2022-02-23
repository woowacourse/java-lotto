package view;

import java.util.Scanner;

public interface InputView<T> extends View {

    Scanner scanner = new Scanner(System.in);

    T getUserInputData();
}
