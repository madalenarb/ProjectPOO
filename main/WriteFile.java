package main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import cycle.Cycle;
import ant.AntColony;
public class WriteFile {

    public File fileName;
    public FileWriter fileWriter;
    public WriteFile(String newFileName) {
        try {
            makeFile(newFileName);
            setFileWriter();
            writeinFile();
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void makeFile(String newFileName) {
        this.fileName = new File(newFileName);
    }

    public void setFileWriter() throws IOException {
        this.fileWriter = new FileWriter(this.fileName);
    }
    public void writeinFile() throws IOException {
        //only works if hamiltonianCycleQueue is public
        AntColony antColony = AntColony.getInstance();
//        for(Cycle c : antColony.hamiltonianCycleQueue) {
//            fileWriter.write(c.toString());
//            fileWriter.write("\n");
//        }
    }
}


