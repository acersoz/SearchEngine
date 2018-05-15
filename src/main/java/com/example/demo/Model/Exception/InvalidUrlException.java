package com.example.demo.Model.Exception;

public class InvalidUrlException extends Exception {

    public InvalidUrlException(String url) {
        super("Unable to find HTML data for: " + url);
    }
}
