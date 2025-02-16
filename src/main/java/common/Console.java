package common;

import java.util.Scanner;

public final class Console implements AutoCloseable {

    private static final Scanner scanner = new Scanner(System.in);

    public String readLine() {
        return scanner.nextLine();
    }

    @Override
    public void close() {
        scanner.close();
    }
}
