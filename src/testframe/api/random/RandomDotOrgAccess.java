package testframe.api.random;

import java.io.InputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;
// TODO: new URL, cast URL.getContent() to InputStream for Scanner,

class RandomDotOrgAccess extends ExternalRandomnessProvider {
    
    // TODO: Write tests for this
    @Override
    public int[] giveNumbers(int amount, int minimum, int maximum) 
            throws IOException {
        int[] numbers = {};
        return numbers;
    }

}
