package kwf2;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;


public class RedmiAppRunner 
{

	public static void main(String[] args) throws Exception, IOException 
	{
		File f=new File("E:\\swd\\Excel\\redmitests.xls");
		
		//Open Excel File for reading
		Workbook rwb= Workbook.getWorkbook(f);
		
		Sheet rsh1=rwb.getSheet(0);    //0 for Sheet1(Tests)
		int not=rsh1.getRows();
		int nouc1=rsh1.getColumns();
		
		Sheet rsh2=rwb.getSheet(1);	   //0 for Sheet2(Steps)
		int nos=rsh2.getRows();
		int nouc2=rsh2.getColumns();
		
		//Open Same Excel File for Writing 
		WritableWorkbook wwb=Workbook.createWorkbook(f, rwb);
		WritableSheet wsh1=wwb.getSheet(0); //o Sheet for results;
		WritableSheet wsh2=wwb.getSheet(1); //o Sheet for results;
		
		System.out.println(nouc1+" "+nouc2);
		
		//Set name to result Column in Sheet2
		
		Date dt=new Date();
		SimpleDateFormat df=new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
		String cname=df.format(dt);
		Label l1=new Label(nouc2,0,cname);
		wsh2.addCell(l1);
		
		//Set name to result Column in Sheet1
		Label l2=new Label(nouc1,0,cname);
		wsh1.addCell(l2);
		
		//Create Object to Methods class
		RedmiMethods gm=new RedmiMethods();
		
		//Collect methods information using Methods Class Object
		Method m[]=gm.getClass().getMethods();
		
		//Calling Methods one after other
		//1st row (index as 0) have names of Columns in Sheet1
		
		for(int i=1;i<not;i++)
		{
			int flag=0;
			
			//Get tid and mode from Sheet1
			String tid=rsh1.getCell(0, i).getContents();
			String mode=rsh1.getCell(2, i).getContents();
			
			if(mode.equalsIgnoreCase("Yes"))
			{
				//1st row (index is 0) have names of Columns in Sheet2
				
				for(int j=1;j<nos;j++)
				{
					String sid=rsh2.getCell(0,j).getContents();
					if(tid.equalsIgnoreCase(sid))
					{
						//take Step Details From Sheet2
						String mn=rsh2.getCell(2, j).getContents();
						String l=rsh2.getCell(3, j).getContents();
						String d=rsh2.getCell(4, j).getContents();
						String c=rsh2.getCell(5, j).getContents();
						
						
						System.out.println(mn+" "+l+" "+d+" "+c);
						
						for(int k=0;k<m.length;k++) 
						{
							if(m[k].getName().equals(mn))
							{
								String r=(String) m[k].invoke(gm,l,d,c);
								if(r.contains("failed") || r.contains("interrupted"))
								{
									flag=1;
								}
								
								Label lb=new Label(nouc2,j,r);
								wsh2.addCell(lb);
								
							}
							
							
						}
					}
					if(flag==0)
					{
						Label l=new Label(nouc1,i,"passed");
						wsh1.addCell(l);
						
					}
					else
					{
						Label l=new Label(nouc1,i,"failed");
						wsh1.addCell(l);
					}
				}
			}
		}
		wwb.write();
		wwb.close();
		rwb.close();

	}

}
