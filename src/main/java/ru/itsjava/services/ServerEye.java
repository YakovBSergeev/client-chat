package ru.itsjava.services;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import java.io.IOException;
import java.net.Socket;

@RequiredArgsConstructor
public class ServerEye {
    private final Socket socket;

    /**
     *Возвращаем входящее сообщение
     * @return
     */
    @SneakyThrows
    public String serverReader() {
        MessageInputService serverReader = new MessageInputServiceImpl( socket.getInputStream() );
        return serverReader.getMessage();
    }

    /**
     * Печать входящего сообщения
     * @throws IOException
     */
    public void serverPrinting() throws IOException {
        MessageInputService serverReader = new MessageInputServiceImpl( socket.getInputStream() );
        System.out.println( serverReader.getMessage() );
    }
}
