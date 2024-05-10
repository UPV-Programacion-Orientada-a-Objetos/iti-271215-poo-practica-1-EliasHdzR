package edu.upvictoria.fpoo.exceptions;

import java.io.IOException;

public class InsuficientDataProvidedException extends IOException {

    public InsuficientDataProvidedException (String message) {
        super(message);
    }

}
