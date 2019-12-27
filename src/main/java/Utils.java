import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.functions.Column;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

public class Utils {
    HSSFWorkbook workbook;
    Utils(){
        try {
            workbook=getWorkBook();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private HSSFWorkbook getWorkBook() throws IOException {
        Properties properties=new Properties();
        properties.load(new FileInputStream(System.getProperty("user.dir")+"\\TestProp.properties"));
        FileInputStream file=new FileInputStream(System.getProperty("user.dir")+"\\"+properties.getProperty("excelName"));
        return new HSSFWorkbook(file);
    }

    private HSSFSheet getSheet(String sheetName) throws IOException {
        return getWorkBook().getSheet(sheetName);
    }

    public void getData() throws IOException {
        HSSFSheet sheet = null;
        System.out.println("No.of Sheets : "+ workbook.getNumberOfSheets());
        // getting sheet in which we going to work
        for (int i=0;i<workbook.getNumberOfSheets();i++){
            if (workbook.getSheetName(i).equalsIgnoreCase("Item")){
                sheet=workbook.getSheetAt(i);
                break;
            }
        }
        // getting column id using name on which we going to fetch test inputs
        Iterator <Row> row=sheet.iterator();
        Row header=row.next();
        Iterator<Cell> cellIterator=header.cellIterator();
        int i=0;
        int getColumn=0;
        while (cellIterator.hasNext()){
            Cell cell=cellIterator.next();
            if (cell.getStringCellValue().equalsIgnoreCase("Name")){
                getColumn=i;
            }
            i++;
        }
        // getting all values in a row based on th input
        while (row.hasNext()){
            Row row1=row.next();
            if (row1.getCell(getColumn).getStringCellValue().equalsIgnoreCase("rice")){
                Iterator <Cell> cellIterator1=row1.cellIterator();
                while (cellIterator1.hasNext()){
                    System.out.println(cellIterator1.next().toString());
                }
                break;
            }
        }
    }

    private Map<String,String> getItem(String itemname) throws IOException {
        Map<String,String> itemMap=new HashMap<String, String>();
        HSSFSheet sheet=getSheet("Item");
        Iterator<Row> rowIterator=sheet.rowIterator();
        Row header=rowIterator.next();
        while (rowIterator.hasNext()){
            int column=0;
            Row item=rowIterator.next();
            if (item.getCell(0).getStringCellValue().equalsIgnoreCase(itemname)){
                Iterator<Cell> cellIterator=item.cellIterator();
                while (cellIterator.hasNext()){
                    itemMap.put(header.getCell(column).toString(),cellIterator.next().toString());
                    column++;
                }
            }
        }
        return itemMap;
    }

    public void poTest() throws IOException {
        System.out.println(getItem("watch").get("hsn"));
        System.out.println(getItem("rice").get("hsn"));

}


}
