package com.company;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

final class Main {

    public static void main(String[] args) throws IOException {


        String[] keys = {"r", "t", "?", "w"};
      //  ComLineParser parser = new ComLineParser(keys);
       // parser.parse(args);

        ComLineParser p = new ComLineParser(keys);
       p.parse(new String[]{"-wInFileName", "-?OutFileName"});



    }



}
