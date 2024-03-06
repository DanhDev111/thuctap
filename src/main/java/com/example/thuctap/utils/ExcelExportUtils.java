package com.example.thuctap.utils;

import com.example.thuctap.dto.UserDTO;
import com.example.thuctap.entity.User;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ExcelExportUtils {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<User> userDTOList;

    public ExcelExportUtils( List<User> userDTOList) {
        this.workbook = new XSSFWorkbook();
        this.userDTOList = userDTOList;
    }

    public void createCell(Row row,int columnCount, Object value, CellStyle style){
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        // LocalDateTime



        if (value instanceof Integer){
            cell.setCellValue((Integer) value);
        } else if (value instanceof Double) {
            cell.setCellValue((Double) value);
        } else if (value instanceof Boolean){
            cell.setCellValue((Boolean) value);
        } else if (value instanceof Long){
            cell.setCellValue((Long) value);
        } else if (value instanceof Float){
            cell.setCellValue((Float) value);
        } else if (value instanceof LocalDateTime) {
            LocalDateTime localDateTime = (LocalDateTime) value;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedDateTime = localDateTime.format(formatter);
            cell.setCellValue(formattedDateTime);
        } else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }
    public void createHeaderRow(){
        sheet = workbook.createSheet("User Information");
        Row row = sheet.createRow(0);
        CellStyle cellStyle = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(12);
        cellStyle.setFont(font);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        createCell(row,0,"User Information",cellStyle);
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 8));
        font.setFontHeightInPoints((short) 10);

        row = sheet.createRow(1);
        font.setBold(true);
        font.setFontHeight(12);
        cellStyle.setFont(font);

        createCell(row,0,"ID",cellStyle);
        createCell(row,1,"Name",cellStyle);
        createCell(row,2,"Username",cellStyle);
        createCell(row,3,"Password",cellStyle);
        createCell(row,4,"Email",cellStyle);
        createCell(row,5,"Gender",cellStyle);
        createCell(row,6,"Created_at",cellStyle);
        createCell(row,7,"Updated_at",cellStyle);


    }
    public void writeUserData(){
        int rowCount = 2;
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);
        for (User userDTO : userDTOList){
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
            createCell(row,columnCount++,userDTO.getId(),style);
            createCell(row,columnCount++,userDTO.getName(),style);
            createCell(row,columnCount++,userDTO.getUsername(),style);
            createCell(row,columnCount++,userDTO.getPassword(),style);
            createCell(row,columnCount++,userDTO.getEmail(),style);
            createCell(row,columnCount++,userDTO.getGender(),style);
            createCell(row,columnCount++,userDTO.getCreatedAt(),style);
            createCell(row,columnCount++,userDTO.getUpdatedAt(),style);

        }
    }

    public void exportDataToExcel(HttpServletResponse response) throws IOException {
        createHeaderRow();
        writeUserData();
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }
}
