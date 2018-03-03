package com.crm.GenericLib;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class CommonUtils {
String path1="./data/testData.xlsx";
public String returnData(String sheetName,int rowNum,int colNume) throws Throwable
{
	FileInputStream fis=new FileInputStream(path1);
	Workbook wb=WorkbookFactory.create(fis);
	org.apache.poi.ss.usermodel.Sheet sh=wb.getSheet(sheetName);
	Row row=sh.getRow(rowNum);
	String data=row.getCell(colNume).getStringCellValue();
	wb.close();
	return data;
	}
String path2="./data/commondata.properties";  
public Properties getPropertyDataObj() throws Throwable
{
	FileInputStream fisp=new FileInputStream(path2);
	Properties pobj=new Properties();
	pobj.load(fisp);
	return pobj;
	
	}
}
