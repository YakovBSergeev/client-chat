package ru.itsjava.services;

import lombok.SneakyThrows;

import java.io.PrintWriter;
import java.net.Socket;

public class ClientServiceImpl implements ClientService {
    public final static int PORT = 8081;
    public final static String HOST = "localhost";

    @SneakyThrows
    @Override
    public void start() {
        Socket socket = new Socket( HOST, PORT );
        if (socket.isConnected()) {
            Thread tread = new Thread( new SocketRunnable( socket ) );
            tread.start();

            PrintWriter serverWriter = new PrintWriter( socket.getOutputStream() );
            MessageInputService messageInputService =
                    new MessageInputServiceImpl( System.in );
            Menu menu = new MenuImpl();
            String youChoice = menu.menu();
            if (!youChoice.equals( "0" )) {

                System.out.println( "Введите свой логин:" );
                String login = messageInputService.getMessage();

                System.out.println( "Введите свой пароль:" );
                String password = messageInputService.getMessage();

                serverWriter.println( youChoice + "!autho!" + login + ":" + password );
                serverWriter.flush();
            } else {
                System.out.println( "До встречи!" );
            }

            while (true) {
//                System.out.println( "Введите сообщение" );
                String consoleMessage = messageInputService.getMessage();
                if (consoleMessage.equals( "Exit" )) {
                    socket.close();
                    break;
                } else {
                    serverWriter.println( consoleMessage );
                    serverWriter.flush();
                }
            }
        }

    }


}
