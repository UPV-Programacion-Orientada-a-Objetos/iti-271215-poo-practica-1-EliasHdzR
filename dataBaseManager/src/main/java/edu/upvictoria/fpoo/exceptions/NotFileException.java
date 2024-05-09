package edu.upvictoria.fpoo.exceptions;

import java.nio.file.FileSystemException;

public class NotFileException extends FileSystemException {

    public NotFileException(String message) {
        super(message);
    }

}
