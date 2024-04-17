package ru.itsjava.services;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class MenuImpl implements Menu {

    @Override
    public void printMenu() {
        System.out.println( "Авторизироваться - нажмите 1, Зарегистрироваться - нажмите 2." );
    }

    /**
     * Подготавливает регистрационную информацию для сервера.
     * Шифрует пароль.
     * @return
     */
    @Override
    public String menu() throws UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        MessageInputService messageMenu =
                new MessageInputServiceImpl( System.in );
        printMenu();
        String youChoice;
        System.out.println( "Введите номер меню: " );

        String numMenu = messageMenu.getMessage();
        if (numMenu.equals( "1" )) {
            System.out.println( "Вы выбрали авторизацию." );
            youChoice = "1";
        } else if (numMenu.equals( "2" )) {
            System.out.println( "Вы выбрали регистрацию." );
            youChoice = "2";
        } else {
            youChoice = "0";
        }

        if (!youChoice.equals( "0" )) {

            System.out.println( "Введите свой логин:" );
            String login = messageMenu.getMessage();

            System.out.println( "Введите свой пароль:" );
            String password = messageMenu.getMessage();

            byte[] key = (password).getBytes("UTF-8");
            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16); // используем только первые 128 бит
            SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");

            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);

            byte[] encrypted = cipher.doFinal(("Text to encrypt").getBytes());
            String passwordCrypt = Arrays.toString( encrypted );


            return youChoice + "!autho!" + login + ":" + passwordCrypt;

        } else {
            return "До встречи!";
        }

    }

}
