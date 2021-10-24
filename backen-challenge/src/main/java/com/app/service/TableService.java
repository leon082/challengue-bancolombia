package com.app.service;

import com.app.dto.ClientDTO;
import com.app.dto.TableDTO;
import com.app.facade.IDecrypt;
import com.app.model.Client;
import com.app.model.Table;
import com.app.repository.IRepository;
import com.app.utils.Constants;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

import static com.app.utils.Utils.writeConsole;

@Service
public class TableService {

  private TableReader readerService;
  private TableWriter writerService;
  private IRepository<Client> clientRepository;
  private IDecrypt decrypt;

  private List<TableDTO> tablesDto = new ArrayList<>();
  private List<Table> tablesModel;

  public TableService(TableReader readerService, TableWriter writerService, IRepository<Client> clientRepository, IDecrypt decrypt) {
    this.readerService = readerService;
    this.writerService = writerService;
    this.clientRepository = clientRepository;
    this.decrypt = decrypt;
  }

  public void runProcess() {
    this.loadTablesModel();
    this.filterClientsByRanges();
    this.decryptProcess();
    this.orderList();
    this.loadTablesDTO();

    this.writeResult();
  }

  private void loadTablesModel() {
    this.tablesModel = readerService.getTables();
    this.tablesModel.forEach(t -> t.setListClients(clientRepository.findByCriteria(t.getCriteria())));

  }


  private void filterClientsByRanges() {
    this.tablesModel.forEach(table -> {
      if (table.getFinalRange() != null && table.getInitialRange() != null) {
        table.setListClients(
            table.getListClients().stream().filter(client -> validateBetweenRange(client.getTotalBalance(), table.getInitialRange(), table.getFinalRange())).collect(Collectors.toList()));
      } else if (table.getFinalRange() != null && table.getInitialRange() == null) {
        table.setListClients(
            table.getListClients().stream().filter(client -> validateFinalRange(client.getTotalBalance(), table.getFinalRange())).collect(Collectors.toList()));
      } else if (table.getFinalRange() == null && table.getInitialRange() != null) {
        table.setListClients(
            table.getListClients().stream().filter(client -> validateInitialRange(client.getTotalBalance(), table.getInitialRange())).collect(Collectors.toList()));
      }
    });
  }


  private boolean validateBetweenRange(BigDecimal balance, BigDecimal ir, BigDecimal fr) {
    return ((balance.compareTo(ir) == 0 || balance.compareTo(ir) == 1) &&
        (balance.compareTo(fr) == 0 || balance.compareTo(fr) == -1));
  }

  private boolean validateFinalRange(BigDecimal balance, BigDecimal fr) {
    return (balance.compareTo(fr) == 0 || balance.compareTo(fr) == -1);
  }

  private boolean validateInitialRange(BigDecimal balance, BigDecimal ir) {
    return (balance.compareTo(ir) == 0 || balance.compareTo(ir) == 1);

  }

  private void decryptProcess() {
    this.tablesModel.forEach(i -> i.getListClients().forEach(client -> {
      if (client.getEncrypt()) {
        client.setCode(this.decrypt.decrypt(client.getCode()));
      }
    }));
  }

  private void orderList(){
    this.tablesModel.forEach(table-> table.setListClients(table.getListClients().stream().sorted(Comparator.comparing(Client::getTotalBalance).reversed().thenComparing(Client::getCode)).collect(Collectors.toList())));
  }

  //Add one female and one male. by step
  private void loadTablesDTO() {
    for (Table table : this.tablesModel) {

      TableDTO dto = new TableDTO();
      dto.setName(table.getName());
      dto.setFinalRange(table.getFinalRange());
      dto.setInitialRange(table.getInitialRange());

      boolean nextGender = true;
      String lastCompany = "";
      List<Client> temp = new ArrayList<>();
      for (int index = 0; index < table.getListClients().size(); index++) {

        Client client = findNextGenderAndDifferentCompany(nextGender, index, table.getListClients(), lastCompany, temp);
        temp.add(client);
        if (client == null) {
          break;
        }

        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setCode(client.getCode());
        clientDTO.setCompany(client.getCompany());
        clientDTO.setEncrypt(client.getEncrypt());
        clientDTO.setMale(client.getMale());
        clientDTO.setType(client.getType());
        clientDTO.setTotalBalance(client.getTotalBalance());
        dto.getListClients().add(clientDTO);

        nextGender = !client.getMale();
        lastCompany = table.getListClients().get(index).getCompany();
      }
      //less than 8 and odd
      if (validateMinimumSize(dto.getListClients().size())) {
        dto.setStatus(Constants.CANCELADA);
      }
      //grater than 8 so take the first 8
      if (dto.getListClients().size() > 8) {
        dto.setListClients(dto.getListClients().subList(0, 8));
      }

      this.tablesDto.add(dto);

    }

  }

  private Client findNextGenderAndDifferentCompany(boolean nextGender, int index, List<Client> listClient, String lastCompany, List<Client> temp) {
    if (index == 0) {
      return listClient.get(index);
    }
    for (int i = index; i < listClient.size(); i++) {

      if (listClient.get(i).getMale() == nextGender
          && !listClient.get(i).getCompany().equalsIgnoreCase(lastCompany)
          && !temp.contains(listClient.get(i))) {
        return listClient.get(i);
      }
    }
    return null;
  }

  private boolean validateMinimumSize(int size) {
    return size < 8 && size != 6 && size != 4;
  }



  public void writeResult() {
    StringBuilder result = new StringBuilder("");

    this.tablesDto.forEach(i -> {

      result.append(i.getName()).append("\n");
      if (i.getStatus() != null && i.getStatus().equalsIgnoreCase(Constants.CANCELADA)) {
        result.append(i.getStatus()).append("\n");
      } else {
        StringBuilder clients = new StringBuilder("");
        i.getListClients().stream().sorted(Comparator.comparing(ClientDTO::getTotalBalance).reversed().thenComparing(ClientDTO::getCode)).forEach(c -> clients.append(clients.length() == 0 ? c.getCode() : "," + c.getCode()));
        result.append(clients).append("\n");
      }

    });
    writeConsole(result.toString());
    this.writerService.writeFile(result.toString());


  }

}
