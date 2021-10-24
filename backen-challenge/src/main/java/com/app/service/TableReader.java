package com.app.service;


import com.app.model.Table;
import com.app.utils.Constants;
import com.app.utils.Criteria;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.stereotype.Service;

import static com.app.utils.Utils.writeConsole;

@Service
public class TableReader {

  private List<Table> tables = new ArrayList<>();

  public List<Table> getTables() {

    try (Stream<Path> files = Files.list(Paths.get(Constants.PATH_FILE))) {
      files.forEach(i -> setTable(i.toString()));
    } catch (IOException e) {
      writeConsole(e.getMessage());
    }

    return this.tables;

  }

  private void setTable(String file) {

    Path path = Paths.get(file);
    try (Stream<String> stream = Files.lines(path)) {

      Table table = new Table();

      List<String> lines = stream.collect(Collectors.toList());

      for (String line : lines) {

        if (line.contains("<")) {
          this.tables.add(table);
          table = new Table();
          table.setName(line);
          continue;
        }
        if (line.contains(":") && line.split(":").length > 1) {
          Criteria criteria = Criteria.valueOf(line.split(":")[0]);
          String value = line.split(":")[1];
          switch (criteria) {
            case TC:
              table.getCriteria().put(criteria, Integer.valueOf(value));
              break;
            case UG:
              table.getCriteria().put(criteria, value);
              break;
            case RI:
              table.setInitialRange(new BigDecimal(value));
              break;
            case RF:
              table.setFinalRange(new BigDecimal(value));
              break;
          }
        }

      }
      this.tables.add(table);
      this.tables.remove(0);
    } catch (IOException e) {
      writeConsole(e.getMessage());

    } catch (StringIndexOutOfBoundsException e) {
      writeConsole(e.getMessage());
    }

  }
}
