package com.dialer.contactschecker.dao;

import java.util.List;

import com.dialer.contactschecker.model.VocalScript;

public interface VocalScriptDao {
	
	public VocalScript add(VocalScript vocalScript);

	public VocalScript update(VocalScript vocalScript);

	public VocalScript delete(VocalScript vocalScript, String recordingpath);

	public List<VocalScript> getAll();

	public VocalScript getById(String vocalScriptId);
}
