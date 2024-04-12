package ru.itsjava;


import org.apache.log4j.BasicConfigurator;
import ru.itsjava.services.ClientService;
import ru.itsjava.services.ClientServiceImpl;

public class Main {


    public static void main(String[] args) {

        BasicConfigurator.configure();

        ClientService clientService = new ClientServiceImpl();
        clientService.start();




    }
}
