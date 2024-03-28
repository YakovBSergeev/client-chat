package ru.itsjava.services;

public class MenuImpl implements Menu {

    @Override
    public void printMenu() {
        System.out.println( "Авторизироваться - нажмите 1, Зарегистрироваться - нажмите 2." );
    }

    @Override
    public String menu() {
        MessageInputService messageInputService =
                new MessageInputServiceImpl( System.in );
        printMenu();
        System.out.println( "Введите номер меню: " );
        String numMenu = messageInputService.getMessage();
        if (numMenu.equals( "1" )) {
            System.out.println( "Вы выбрали авторизацию." );
            return "1";
        } else if (numMenu.equals( "2" )) {
            System.out.println( "Вы выбрали регистрацию." );
            return "2";
        }
        return "0";
    }

}
