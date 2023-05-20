package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


// separar main num outro package 
// colocar o readFile no main package como uma classe separada

public class Main{
    public static void main(String[] args){
        ParameterReader.readingMode(args[0]);
        if(ParameterReader.getReadingMode() == 0){
            ParameterReader.readParameters(args);
        } else if(ParameterReader.getReadingMode() == 1){
            
        }
    }
}