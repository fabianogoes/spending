package com.spending.service;

import com.spending.dto.LoadResponseDTO;
import com.spending.model.Registry;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class LoadSheetBradescoImplService implements LoadSheetService {

    @Autowired
    private RegistryService registryService;

    @Override
    public LoadResponseDTO load(MultipartFile multipartFile) {
        LoadResponseDTO loadResponseDTO = new LoadResponseDTO();
        List<Registry> registryToSave = new ArrayList<>();
        try{
            HSSFWorkbook workbook = new HSSFWorkbook(multipartFile.getInputStream());
            HSSFSheet sheet = workbook.getSheetAt(0);
            Iterator rowIterator = sheet.rowIterator();
            int totalPersisted = 0;
            while (rowIterator.hasNext()) {
                Row row = (Row)rowIterator.next();

                /**********************************************
                 * Descantando as 7 primeiras linhas de Header
                 **********************************************/
                if(row.getRowNum() < 8){
                    continue;
                }

                if("Total".equalsIgnoreCase(row.getCell(1).getStringCellValue())) {
                    break;
                }

                Iterator cellIterator = row.cellIterator();
                String date = null;
                String description = null;
                String value = null;
                while (cellIterator.hasNext()) {
                    Cell cell = (Cell) cellIterator.next();

                    switch (cell.getColumnIndex()) {
                        case 0:
                            date = cell.getStringCellValue();
                            break;
                        case 1:
                            if(StringUtils.isEmpty(date)) break;
                            description = cell.getStringCellValue();

                            Row nextRow = sheet.getRow(row.getRowNum()+1);
                            String nextDate = nextRow.getCell(0) != null ? nextRow.getCell(0).getStringCellValue() : null;
                            String nextDescription = nextRow.getCell(1) != null ? nextRow.getCell(1).getStringCellValue() : null;

                            if(StringUtils.isEmpty(nextDate) && !StringUtils.isEmpty(nextDescription)){
                                description = description.concat(" ").concat(nextDescription);
                            }

                            break;
                        case 4:
                            if(StringUtils.isEmpty(date)) break;
                            value = cell.getStringCellValue()
                                        .replace(".", "")
                                        .replace("-", "")
                                        .replace(",", ".");
                            break;
                    }
                }
                if(!StringUtils.isEmpty(date) && !StringUtils.isEmpty(description) && !StringUtils.isEmpty(value)) {
                    Registry registry = Registry.builder().date(date).description(description).value(Double.parseDouble(value)).build();
                    if(!this.registryService.exist(registry)) {
                        totalPersisted++;
                        registryToSave.add(registry);
                    }
                }
            }
            this.registryService.deleteAll();
            this.registryService.save(registryToSave);
            workbook.close();
            loadResponseDTO.setTotalPersisted(totalPersisted);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return loadResponseDTO;
    }


}
