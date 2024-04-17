package ru.itsjava.services;

import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MessageInputServiceImpl implements MessageInputService {
    private final BufferedReader bufferedReader;

    /**
     * Буфер входящего потока
     * @param inputStream
     */
    public MessageInputServiceImpl(InputStream inputStream) {
        bufferedReader = new BufferedReader( new InputStreamReader( inputStream ) );
    }

    /**
     * Возвращаем входящий поток в виде строки
     * @return
     */
    @SneakyThrows
    @Override
    public String getMessage() {
        return bufferedReader.readLine();
    }

    /**
     * Закрываем входящий поток
     * @throws IOException
     */
    public void close() throws IOException {
        bufferedReader.close();
    }
}
