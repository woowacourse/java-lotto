package view;

import java.util.Scanner;

public interface InputView<T> {

    Scanner scanner = new Scanner(System.in);

    T getUserInputData();
}
