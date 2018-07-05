package fr.lcl.certificats.certificats.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.lcl.certificats.certificats.dao.CertificatRepository;
import fr.lcl.certificats.certificats.model.Certificat;
import fr.lcl.certificats.certificats.service.CertificatService;


@Controller
public class MainController {
	
	
	@Autowired
	private CertificatService certificatService;
	
	@Autowired
	  CertificatRepository certificatRepository;
	public CertificatService getCertificatService() {
		return certificatService;
	}

	public void setCertificatService(CertificatService certificatService) {
		this.certificatService = certificatService;
	}
	
	@RequestMapping("/")
	public String welcome(Map<String, Object> model) {
		certificatService = new CertificatService(certificatRepository);
		List<Certificat> totalCertificat = certificatService.findAll();
		model.put("totalCertificat", totalCertificat);
		model.put("nbreCertificat", totalCertificat.size());
		return "welcometemplate";
	}
	
	//	Insert Certificate

	/*@GetMapping("/certificates")
	public List<Certificat> findAll(){
	
		final List<Certificat> certificates = this.certificatService.findAll();
		for(Certificat c: certificates) {
			System.out.println("dn= " + c.getDn());
		}
		return certificates;
	}
	
	@PostMapping("/certificates")
	public String add() {
		Certificat certificat = new Certificat.Builder("dn3", "ff", new Date(), new Date(), CertificateUsage.JETONV3, CertificateType.PUBLIC)
				.commentaire("ceci est un commentaire").destinataire("destinataire").build();
		this.certificatService.save(certificat);
		return "";
	}*/
}
