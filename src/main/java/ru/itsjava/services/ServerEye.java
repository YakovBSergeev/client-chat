package ru.itsjava.services;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import java.io.IOException;
import java.net.Socket;

@RequiredArgsConstructor
public class ServerEye {
    private final Socket socket;

    @SneakyThrows
    public String serverReader() {
        MessageInputService serverReader = new MessageInputServiceImpl( socket.getInputStream() );
        return serverReader.getMessage();
    }

    public void serverPrinting() throws IOException {
        MessageInputService serverReader = new MessageInputServiceImpl( socket.getInputStream() );
        System.out.println( serverReader.getMessage() );
    }
}
