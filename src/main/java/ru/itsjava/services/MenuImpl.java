package ru.itsjava.services;

public class MenuImpl implements Menu {

    @Override
    public void printMenu() {
        System.out.println( "Авторизироваться - нажмите 1, Зарегистрироваться - нажмите 2." );
    }

    @Override
    public String menu() {
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

            return youChoice + "!autho!" + login + ":" + password;

        } else {
            return "До встречи!";
        }

    }

}
