package ru.itsjava.services;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

import java.net.Socket;

@AllArgsConstructor
public class ClientServiceImpl implements ClientService {
    private final static int PORT = 8081;
    private final static String HOST = "localhost";


    @SneakyThrows
    @Override
    public void start() {
        Socket socket = new Socket( HOST, PORT );
        if (socket.isConnected()) {
            Thread tread = new Thread( new SocketRunnable( socket ) );
            tread.start();

//            MessageInputService messageInputService = new MessageInputServiceImpl( socket.getInputStream() );
//            while (true) {
//                messageInputService.getMessage();
//
//            }

        }
    }


}





