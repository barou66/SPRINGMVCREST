package com.dialer.contactschecker.serviceimpl;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dialer.contactschecker.dao.VocalScriptDao;
import com.dialer.contactschecker.model.VocalScript;
import com.dialer.contactschecker.service.IVocalScriptService;

@Service
public class VocalScriptServiceImpl implements IVocalScriptService {
	
	private static Logger logger = LoggerFactory.getLogger(VocalScriptServiceImpl.class);
	
	@Autowired
	private VocalScriptDao vocalScriptDao;

	@Override
	public VocalScript add(VocalScript vocalScript) {
		
		return vocalScriptDao.add(vocalScript);
	}

	@Override
	public VocalScript update(VocalScript vocalScript) {
		
		return vocalScriptDao.update(vocalScript);
	}

	@Override
	public VocalScript delete(VocalScript vocalScript, String recordingpath) {

		return vocalScriptDao.delete(vocalScript, recordingpath);
	}

	@Override
	public List<VocalScript> getAll() {

		return vocalScriptDao.getAll();
	}

	@Override
	public VocalScript getById(String vocalScriptId) {
		
		return vocalScriptDao.getById(vocalScriptId);
	}
	
	@Override
	public boolean addMessageAudio(File soundfile,String ivrname,String filename) 
	{
		try 
		{
			//Create temp file
			String tempfolder=System.getProperty("java.io.tmpdir");
			if(!tempfolder.endsWith("/"))
				tempfolder=tempfolder+"/";
			tempfolder=tempfolder+ivrname+"/";
			File tempFile = new File(tempfolder);
			if(!tempFile.exists())
				tempFile.mkdir();
			tempFile = new File(tempfolder+filename);
			//Copy files
			Files.move(soundfile.toPath(), tempFile.toPath(),StandardCopyOption.REPLACE_EXISTING);
			Files.setPosixFilePermissions(tempFile.toPath(), PosixFilePermissions.fromString("rwxrwxrwx"));
			return true;
		}
		catch (Exception e) 
		{
			logger.error("Can't add audio file '"+filename+"' to  script '"+ivrname+"' !",e);
			return false;
		}
	}

}
