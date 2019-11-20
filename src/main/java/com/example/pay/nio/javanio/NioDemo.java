package com.example.pay.nio.javanio;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class NioDemo {
    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(URI.create("file E://maxwell.txt")));
        System.out.println(lines);

    }
}
