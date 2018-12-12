package com.dialer.contactschecker.controllerrest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.dialer.contactschecker.model.SipProviders;
import com.dialer.contactschecker.service.ISipProvidersService;
import com.dialer.contactschecker.util.AppConstants;

@RestController
public class SipProvidersController {
	
	private static Logger logger = LoggerFactory.getLogger(SipProvidersController.class);
	
	@Autowired
	private ISipProvidersService  sipProvidersService;
	
	 //------------------- Create SIP --------------------------------------------------------//
	@CrossOrigin(origins = AppConstants.CLIENT_ORIGIN)
    @RequestMapping(value = "api/sipprovider/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void>  createSipProvider(@RequestBody SipProviders sipProvider,    UriComponentsBuilder ucBuilder){
    	
    	/*** AFTER WEBSERVICE INSERT HERE ***/
		
		SipProviders currentSip = sipProvidersService.add(sipProvider);
		 
		if(null == currentSip) {
	         return new ResponseEntity<Void>(HttpStatus.EXPECTATION_FAILED);			
		}
		
		 HttpHeaders headers = new HttpHeaders();
	        headers.setLocation(ucBuilder.path("api/sipprovider/add").buildAndExpand(sipProvider.getPVD_ID()).toUri());
	        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
		
	}
    
    
     //------------------- Update SIP --------------------------------------------------------//
	@CrossOrigin(origins = AppConstants.CLIENT_ORIGIN)
    @RequestMapping(value = "api/sipprovider/{id}/update", method = RequestMethod.PUT)
    public ResponseEntity<SipProviders> updateSipProvider(@PathVariable("id") String id,@RequestBody SipProviders sipProvider) {
	        
		SipProviders currentSip =  sipProvidersService.getById(id);
         
        if (null == currentSip) {
        	logger.info("User with id {} can't be found",id);
            
            return new ResponseEntity<SipProviders>(HttpStatus.NOT_FOUND);
        }
        
        
        currentSip = sipProvidersService.update(sipProvider);
 
        return new ResponseEntity<SipProviders>(currentSip, HttpStatus.OK);
    }

   //-------------------Retrieve All SipProvider--------------------------------------------------------//  
	@CrossOrigin(origins = AppConstants.CLIENT_ORIGIN)
    @RequestMapping(value = "api/sipprovider/getall", method = RequestMethod.GET)
    public ResponseEntity<List<SipProviders>> listAllSipProvider() {
    	
        List<SipProviders> sipProviders = sipProvidersService.getAll();
        
        if(sipProviders.isEmpty()){
            return new ResponseEntity<List<SipProviders>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        
        return new ResponseEntity<List<SipProviders>>(sipProviders, HttpStatus.OK);
    }
    
    //------------------- Delete a SipProviders --------------------------------------------------------// 
	@CrossOrigin(origins = AppConstants.CLIENT_ORIGIN)
    @RequestMapping(value = "api/sipprovider/{id}/delete", method = RequestMethod.DELETE)
    public ResponseEntity<SipProviders> deleteUser(@PathVariable("id") String id) {
        System.out.println("Fetching & Deleting User with id " + id);
 
        SipProviders currentSip =  sipProvidersService.getById(id);
        
        if (null == currentSip) {
            System.out.println("Unable to delete. User with id " + id + " not found");
            return new ResponseEntity<SipProviders>(HttpStatus.NOT_FOUND);
        }
 
        sipProvidersService.delete(id);
        return new ResponseEntity<SipProviders>(currentSip,HttpStatus.NO_CONTENT);
    }
   
}
