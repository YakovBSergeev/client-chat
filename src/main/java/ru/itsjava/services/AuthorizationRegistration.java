package ru.itsjava.services;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.log4j.Logger;

import java.net.Socket;

@RequiredArgsConstructor
public class AuthorizationRegistration {

    private static final Logger log = Logger.getLogger( AuthorizationRegistration.class );
    private final Socket socket;

    /**
     * Обрабатывает сообщения с сервера в процессе подключения к чату.
     * @return
     */
    @SneakyThrows
    public boolean authorizationRegistration() {
        Menu menu = new MenuImpl();
        String clientChoice = menu.menu();
        ClientWriter clientWriter = new ClientWriter( socket );
        if (clientChoice.equals( "До встречи!" )) {
            System.out.println( "До встречи!" );
            socket.close();
            System.exit( 1 );
        } else {
            log.info( clientWriter );
            clientWriter.clientWriter( clientChoice );
        }
        ServerEye serverEye = new ServerEye( socket );
        String serverMessage = serverEye.serverReader();
        log.info( serverMessage );
        System.out.println( serverMessage );
        if (serverMessage.contains( "Такой пользователь не зарегистрирован. Авторизуйтесь повторно." ) ||
                serverMessage.contains( "Такой пользователь уже зарегистрирован. Измените данные регистрации." ) ||
                serverMessage.contains( "Такой пользователь уже подключен." )) {
            authorizationRegistration();
        }
        return true;
    }

}
