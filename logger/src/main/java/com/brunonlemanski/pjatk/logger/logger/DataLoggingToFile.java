package com.brunonlemanski.pjatk.logger.logger;


import com.brunonlemanski.pjatk.logger.model.Bolid;
import com.brunonlemanski.pjatk.logger.model.Race;

import java.io.*;

public class DataLoggingToFile implements LoggerInterface {

    private final String FILENAME_BOLID_LOGS = "bolid_logs.txt";

    private final String FILENAME_RACE_LOGS = "race_logs.txt";

    private static PrintWriter printWriter;

    @Override
    public void log(Bolid bolid) {

        String path1 = LOG_PATH + FILENAME_BOLID_LOGS;

        File directory = new File(LOG_PATH);

        if(!directory.exists()) {
            directory.mkdir();
        }


        try{

            File file1 = new File(path1);
            file1.setReadable(true);
            file1.setWritable(true);

            printWriter = new PrintWriter(new BufferedWriter(new FileWriter(file1, true)));
            //file.getAbsolutePath();
            printWriter.println(bolid.toString());

        } catch (Exception ex) {
            ex.printStackTrace();

        } finally {
            printWriter.flush();
            printWriter.close();
        }
    }

    @Override
    public void log(Race race) {

        String path = LOG_PATH + FILENAME_RACE_LOGS;

        File directory = new File(LOG_PATH);

        if(!directory.exists()) {
            directory.mkdir();
        }

        try {
            File file = new File(path);
            file.setWritable(true);
            file.setReadable(true);

            printWriter = new PrintWriter(new BufferedWriter(new FileWriter(file, true)));
            //file.getAbsolutePath();
            printWriter.println(race.toString());

        } catch (IOException ex) {
            ex.printStackTrace();

        } finally {
            printWriter.flush();
            printWriter.close();
        }

    }
}
