package com.dialer.contactschecker.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dialer.contactschecker.model.CampaignRecord;

public class CampaignUtils {
	private static Logger logger = LoggerFactory.getLogger(CampaignUtils.class);
	
	public static CampaignRecord transToRecordValue(long calId,String[] nextLine, int telphoneCol, int invalid, int blankCol) {
		CampaignRecord camRecord = new CampaignRecord();
		camRecord.setCalId(calId);
		camRecord.setPhone(nextLine[telphoneCol]);
		camRecord.setStatus(AppConstants.DIAL_STATUS_UNDIALED);
		camRecord.setAttempts(0);
		camRecord.setValid(invalid);
		camRecord.setIvrResult("");
		List<String> otherColumns = new ArrayList<String>();
		// add file data
		for (int i = 0; i < nextLine.length; i++) {
			if (i != telphoneCol) {
				otherColumns.add(nextLine[i]);
			}
		}
		
		// if file column is less than table column, then put blank for these column
		for (int j = 0; j < blankCol; j ++) {
			otherColumns.add("");
		}
		camRecord.setOtherColumns(otherColumns);
		return camRecord;
	}
	
	public static String getTableName(String campaignName) {
		// rule for campaigns table name:MCALLING_LIST_<Campaignname>_CAL
		return ("mcalling_list_" + campaignName + "_cal").toLowerCase();
	}
	
	public static String[] generateHeaders(int columnNumber) {
		String[] headers = new String[columnNumber];
		int count = 1;
		for (int i = 0; i < columnNumber; i++) {
			headers[i] = "DATA" + count;
			count++;
		}
		return headers;
	}
	
	public static boolean deleteFolder(String folder){
    	//browsing the file directory and delete recursively using java nio
		try 
		{
			if(folder==null) return true;
			
			if(new File(folder).exists())
			{
				Files.walkFileTree(Paths.get(folder), new FileVisitor<Path>() {
	
					public FileVisitResult postVisitDirectory(Path dir,IOException arg1) throws IOException {
						Files.delete(dir);
						return FileVisitResult.CONTINUE;
					}
	
					public FileVisitResult preVisitDirectory(Path arg0,	BasicFileAttributes arg1) throws IOException {
						return FileVisitResult.CONTINUE;
					}
	
					public FileVisitResult visitFile(Path file,BasicFileAttributes arg1) throws IOException {
						Files.delete(file);
						return FileVisitResult.CONTINUE;
					}
	
					public FileVisitResult visitFileFailed(Path file,IOException arg1) throws IOException {
						return FileVisitResult.CONTINUE;
					}
				});
			}
			return true;
		} 
		catch (IOException ex)
		{
			logger.error("Cannot delete folder '"+folder+"' :",ex);
			return false;
		}
			
     }
}
