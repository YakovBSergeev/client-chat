package ru.itsjava.services;

import java.io.IOException;

public interface MessageInputService {
    String getMessage();
    void close() throws IOException;
}
