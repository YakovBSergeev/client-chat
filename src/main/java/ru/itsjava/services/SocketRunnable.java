package ru.itsjava.services;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.log4j.Logger;

import java.net.Socket;

@RequiredArgsConstructor
public class SocketRunnable implements Runnable {

    private static final Logger log = Logger.getLogger(SocketRunnable.class);
    private final Socket socket;


    /**
     * Принимаем сообщения с сервера после авторизации
     */
    @SneakyThrows
    @Override
    public void run() {
        ServerEye serverEye = new ServerEye( socket );
        log.info( socket );

        while (true) {
            serverEye.serverPrinting();
        }
    }
}

