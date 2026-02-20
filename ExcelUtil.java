package com.healthproof.qa;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

public class ExcelUtil {

    static String filePath = "objects.xlsx";

    public static LocatorData getLocator(String objectName) {

        LocatorData data = new LocatorData();

        try {

            FileInputStream fis =
                    new FileInputStream(filePath);

            Workbook workbook =
                    new XSSFWorkbook(fis);

            Sheet sheet =
                    workbook.getSheetAt(0);

            for (Row row : sheet) {

                if (row.getRowNum() == 0)
                    continue;

                if (row.getCell(0)
                        .getStringCellValue()
                        .equals(objectName)) {

                    data.rowIndex =
                            row.getRowNum();

                    // CHECK healedLocator first

                    String healed =
                            getCellValue(
                                    row.getCell(7));

                    if (healed != null &&
                            !healed.isEmpty()) {

                        String parts[] =
                                healed.split("=",2);

                        data.type =
                                parts[0];

                        data.value =
                                parts[1];

                        workbook.close();

                        return data;

                    }

                    // ELSE use original locator

                    if (getCellValue(row.getCell(1)) != null) {

                        data.type="name";

                        data.value=getCellValue(row.getCell(1));

                    }

                    else if(getCellValue(row.getCell(2)) != null){

                        data.type="id";

                        data.value=getCellValue(row.getCell(2));

                    }

                    else if(getCellValue(row.getCell(3)) != null){

                        data.type="linkText";

                        data.value=getCellValue(row.getCell(3));

                    }

                    else if(getCellValue(row.getCell(4)) != null){

                        data.type="partialLinkText";

                        data.value=getCellValue(row.getCell(4));

                    }

                    else if(getCellValue(row.getCell(5)) != null){

                        data.type="cssSelector";

                        data.value=getCellValue(row.getCell(5));

                    }

                    else if(getCellValue(row.getCell(6)) != null){

                        data.type="xpath";

                        data.value=getCellValue(row.getCell(6));

                    }

                    workbook.close();

                    return data;

                }

            }

            workbook.close();

        }

        catch(Exception e){

            e.printStackTrace();

        }

        return null;

    }

    public static void writeHealedLocator(

            LocatorData data,

            String healedLocator){

        try{

            FileInputStream fis =
                    new FileInputStream(filePath);

            Workbook workbook =
                    new XSSFWorkbook(fis);

            Sheet sheet =
                    workbook.getSheetAt(0);

            Row row =
                    sheet.getRow(data.rowIndex);

            Cell cell =
                    row.getCell(7);

            if(cell==null)

                cell=row.createCell(7);

            cell.setCellValue(
                    data.type+"="+healedLocator);

            FileOutputStream fos =
                    new FileOutputStream(filePath);

            workbook.write(fos);

            fos.close();

            workbook.close();

            System.out.println(
                    "Excel updated with healed locator");

        }

        catch(Exception e){

            e.printStackTrace();

        }

    }

    static String getCellValue(Cell cell){

        if(cell==null) return null;

        if(cell.getCellType()==CellType.STRING)

            return cell.getStringCellValue();

        return null;

    }

}
