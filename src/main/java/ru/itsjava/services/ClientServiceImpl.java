package ru.itsjava.services;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.log4j.Logger;

import java.net.Socket;


@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private static final Logger log = Logger.getLogger( AuthorizationRegistration.class );
    private final static int PORT = 8081;
    private final static String HOST = "localhost";

    /**
     * Метод подключает/отключает к серверу и отправляет сообщения.
     *
     */
    @SneakyThrows
    @Override
    public void start() {
        Socket socket = new Socket( HOST, PORT );
        if (socket.isConnected()) {
            Thread tread = new Thread( new SocketRunnable( socket ) );

            AuthorizationRegistration authorizationRegistration = new AuthorizationRegistration( socket );
            log.info( authorizationRegistration );

            if (authorizationRegistration.authorizationRegistration()) {
                tread.start();
                log.info( tread );
                ClientWriter clientWriter = new ClientWriter( socket );
                MessageInputService messageInputService =
                        new MessageInputServiceImpl( System.in );

                while (true) {
                    String consoleMessage = messageInputService.getMessage();
                    if (consoleMessage.equals( "Exit" )) {
                        clientWriter.clientWriter( consoleMessage );
                        messageInputService.close();
                        socket.close();
//                        System.exit( 1 );
                        break;
                    } else if (consoleMessage.isEmpty()) {
                        System.out.println( "Введите сообщение." );
                    } else {
                        clientWriter.clientWriter( consoleMessage );
                    }
                }
            }
        }
    }
}





