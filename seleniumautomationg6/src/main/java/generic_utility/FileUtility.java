package generic_utility;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class FileUtility {

	public String getDataFromPropertiesFile(String key) throws IOException {
		FileInputStream fis = new FileInputStream("./src/test/resources/commondata.properties");
		Properties pro = new Properties();
		pro.load(fis);
		String value = pro.getProperty(key);
		return value;
	}

	public String getDataFromJsonFile(String key) throws IOException, ParseException {
		FileReader fr = new FileReader("./src/test/resources/commondata.json");
		JSONParser jp = new JSONParser();
		Object obj = jp.parse(fr);
		JSONObject jobj = (JSONObject) obj;
		String value = jobj.get(key).toString();
		return value;
	}

	public String getDataFromExcelFile(String sheet, int row, int cell)
			throws EncryptedDocumentException, IOException {
		FileInputStream fis = new FileInputStream("./test/resources/testscriptdata.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet st = wb.getSheet(sheet);
		Row rw=st.getRow(row);
		Cell cl=rw.getCell(cell);
		String value=cl.getStringCellValue();
		return value;
	}

}
