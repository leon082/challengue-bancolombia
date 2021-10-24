package com.app.service;

import com.app.utils.Constants;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import org.springframework.stereotype.Service;

@Service
public class TableWriter {

  public void writeFile(String log) {

    File file = new File(Constants.PATH_FILE + Constants.RESULT + Constants.FILE_NAME);
    try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true)))) {
      writer.write(log + "\n");
      writer.flush();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e1) {
      e1.printStackTrace();
    }
  }
}
