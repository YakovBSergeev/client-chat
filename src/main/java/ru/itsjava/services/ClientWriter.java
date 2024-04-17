package ru.itsjava.services;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import java.io.PrintWriter;
import java.net.Socket;

@RequiredArgsConstructor
public class ClientWriter {
    private final Socket socket;

    /**
     * метод для отправки сообщений на сервер
     * @param string
     */
    @SneakyThrows
    public void clientWriter(String string){
        PrintWriter serverWriter = new PrintWriter( socket.getOutputStream() );
        serverWriter.println( string );
        serverWriter.flush();
    }
}
