package ru.itsjava.services;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

@RequiredArgsConstructor
public class SocketRunnable implements Runnable {
    private final Socket socket;

    @SneakyThrows
    @Override
    public void run() {
        MessageInputService serverReader = new MessageInputServiceImpl( socket.getInputStream() );
        if (authorizationRegistration( serverReader )) {
            try {
                PrintWriter serverWriter = new PrintWriter( socket.getOutputStream(), true );
                MessageInputService messageInputService = new MessageInputServiceImpl( System.in );
                String consoleMessage = "";

                while (!consoleMessage.equals( "Exit" )) {
                    consoleMessage = messageInputService.getMessage();
                    serverWriter.println( consoleMessage );
                    System.out.println( serverReader.getMessage() );

                }
                socket.close();
                messageInputService.close();
                serverWriter.close();

            } catch (IOException i) {
//                i.printStackTrace();

            }

        }

    }

    private boolean authorizationRegistration(MessageInputService serverReader) throws IOException {
        PrintWriter serverWriter = new PrintWriter( socket.getOutputStream(), true );
        Menu menu = new MenuImpl();
        String youChoice = menu.menu();
        if (youChoice.equals( "До встречи!" )) {
            System.out.println( "До встречи!" );
            socket.close();
            serverWriter.close();
        } else {
            serverWriter.println( youChoice );

        }
        String serverMessage = serverReader.getMessage();
//        while (serverMessage != null) {
        System.out.println( serverMessage );
        if (serverMessage.contains( "Такой пользователь не зарегистрирован. Авторизуйтесь повторно." ) || serverMessage.contains( "Такой пользователь уже зарегистрирован. Измените данные регистрации." )) {
            MessageInputService serverReaderNext = new MessageInputServiceImpl( socket.getInputStream() );
            authorizationRegistration( serverReaderNext );
//            }break;

        }
        return true;
    }

}

