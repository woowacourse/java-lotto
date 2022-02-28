package lotto.view.input.reader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import lotto.exception.LottoException;
import lotto.exception.reader.ReaderExceptionStatus;

public class ConsoleReader implements Reader {

    @Override
    public String readLine() {
        try {
            final InputStreamReader inputStreamReader = new InputStreamReader(System.in);
            final BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            return bufferedReader.readLine();
        } catch (IOException exception) {
            throw new LottoException(ReaderExceptionStatus.READER_CANNOT_READ);
        }
    }

}
