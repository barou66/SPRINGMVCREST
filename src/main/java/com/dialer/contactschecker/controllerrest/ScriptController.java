package com.dialer.contactschecker.controllerrest;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import com.dialer.contactschecker.model.VocalScript;
import com.dialer.contactschecker.service.IVocalScriptService;
import com.dialer.contactschecker.util.AppConstants;
import com.dialer.contactschecker.util.AppUtils;

@RestController
public class ScriptController {

	private static Logger logger = LoggerFactory.getLogger(ScriptController.class);

	@Autowired
	private IVocalScriptService vocalScriptService;

	// ---------------------------------------------------- Create VocalScript
	// --------------------------------------------------------//
	@CrossOrigin(origins = AppConstants.CLIENT_ORIGIN)
	@RequestMapping(value = "api/vocalscript/add", method = RequestMethod.POST)
	public ResponseEntity<Void> createVocalScript(@RequestParam("vscDialPlanNumber") int vscDialPlanNumber,
			@RequestParam("vscName") String vscName, @RequestParam("vscType") int vscType,
			@RequestParam("vscScript") String vscScript,
			@RequestParam(name = "file", required = false) MultipartFile file, 
			UriComponentsBuilder ucBuilder) {

		if (null != file) {
			try {
				File currentFile = AppUtils.convert(file);
				vocalScriptService.addMessageAudio(currentFile,vscName,currentFile.getName());
			} catch (IOException e) {
				logger.error("Can't Convert MultipartFile To File {}", e);
				return new ResponseEntity("", HttpStatus.EXPECTATION_FAILED);
			}

		}
		
		VocalScript vocalScript = new VocalScript();
		vocalScript.setVscDialPlanNumber(vscDialPlanNumber);
		vocalScript.setVscName(vscName);
		vocalScript.setVscType(vscType);
		vocalScript.setVscScript(vscScript);

		/*** AFTER WEBSERVICE INSERT HERE ***/

		VocalScript currentvocalScript = vocalScriptService.add(vocalScript);

		if (null == currentvocalScript) {
			return new ResponseEntity<Void>(HttpStatus.EXPECTATION_FAILED);
		}

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("api/sipprovider/add").buildAndExpand("****addS**").toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);

	}
	
	/*@CrossOrigin(origins = AppConstants.CLIENT_ORIGIN)
	@RequestMapping(value = "api/vocalscript/addmessage", method = RequestMethod.POST)
	public ResponseEntity<Void> addMessageAudio(@RequestParam("vscName") String vscName@RequestParam(name = "file", required = false) MultipartFile file,
			UriComponentsBuilder ucBuilder) {
		
		
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("api/messageaudio/add").buildAndExpand("**messageaudio***").toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
		
	}*/

	

	// ------------------------ Retrieve All VocalScript
	// ------------------------------//
	@CrossOrigin(origins = AppConstants.CLIENT_ORIGIN)
	@RequestMapping(value = "api/vocalscript/getall", method = RequestMethod.GET)
	public ResponseEntity<List<VocalScript>> listAllVocalScript() {

		List<VocalScript> vocalScripts = vocalScriptService.getAll();

		if (vocalScripts.isEmpty()) {
			return new ResponseEntity<List<VocalScript>>(HttpStatus.NO_CONTENT);// You many decide to return
																				// HttpStatus.NOT_FOUND
		}

		return new ResponseEntity<List<VocalScript>>(vocalScripts, HttpStatus.OK);
	}

}
