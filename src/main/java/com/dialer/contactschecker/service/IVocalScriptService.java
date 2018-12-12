package com.dialer.contactschecker.service;

import java.io.File;
import java.util.List;

import com.dialer.contactschecker.model.VocalScript;

public interface IVocalScriptService {
	
	public VocalScript add(VocalScript vocalScript);

	public VocalScript update(VocalScript vocalScript);

	public VocalScript delete(VocalScript vocalScript, String recordingpath);

	public List<VocalScript> getAll();

	public VocalScript getById(String vocalScriptId);
	
	public boolean addMessageAudio(File soundfile,String ivrname,String filename) ;
}
