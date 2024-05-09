package edu.upvictoria.fpoo.exceptions;

import java.nio.file.FileSystemException;

public class WrongFileExtensionException extends FileSystemException {

    public WrongFileExtensionException (String message) {
        super(message);
    }

}
