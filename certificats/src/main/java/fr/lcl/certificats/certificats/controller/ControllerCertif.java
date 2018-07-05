package fr.lcl.certificats.certificats.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.lcl.certificats.certificats.model.Certificat;
import fr.lcl.certificats.certificats.service.CertificatService;

@RestController
public class ControllerCertif {

	@Autowired
	CertificatService certificatService;

	@RequestMapping(value="/Afficher", method=RequestMethod.GET)
	public List <Certificat> afficherCertif(){
		
		return certificatService.listCertificates("C:\\Users\\Dell\\Desktop\\certificat\\");
	}

	@RequestMapping(value="/SauvegarderCerticats", method=RequestMethod.GET)
	public void sauvegarderCerticats(){
		
		certificatService.saveAll("C:\\Users\\Dell\\Desktop\\certificat\\");
	}
}
