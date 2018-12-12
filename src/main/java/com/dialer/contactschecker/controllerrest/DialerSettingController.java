package com.dialer.contactschecker.controllerrest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dialer.contactschecker.model.DialerSetting;
import com.dialer.contactschecker.model.SipProviders;
import com.dialer.contactschecker.service.IDialerSettingService;
import com.dialer.contactschecker.util.AppConstants;

@RestController
public class DialerSettingController {
	
	@Autowired
	private IDialerSettingService dialerSettingService;
	
	@CrossOrigin(origins = AppConstants.CLIENT_ORIGIN)
	@RequestMapping(value = "api/dialer/update", method = RequestMethod.PUT)
	public ResponseEntity<DialerSetting> updateDialerSetting(@RequestBody DialerSetting dialerSetting) {
	
		boolean statut = dialerSettingService.updateDialerSettings(dialerSetting);
		
		if (statut) {
			// logger.info("User Login is: {}",username);
			 return new ResponseEntity<DialerSetting>(HttpStatus.OK);
		}

		return new ResponseEntity<DialerSetting>(HttpStatus.NO_CONTENT);
	}
	
	@CrossOrigin(origins = AppConstants.CLIENT_ORIGIN)
    @RequestMapping(value = "api/dialer/getsettings", method = RequestMethod.GET)
    public ResponseEntity<DialerSetting> getDialerSetting() {
    	
		DialerSetting ds = dialerSettingService.getDialerSettings();
        
        if(null == ds){
            return new ResponseEntity<DialerSetting>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        
        return new ResponseEntity<DialerSetting>(ds, HttpStatus.OK);
    }

}
