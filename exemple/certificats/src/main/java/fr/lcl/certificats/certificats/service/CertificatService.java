package fr.lcl.certificats.certificats.service;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import fr.lcl.certificats.certificats.dao.CertificatRepository;
import fr.lcl.certificats.certificats.model.Certificat;
import fr.lcl.certificats.domain.CertificateType;


@Service
@Transactional
public class CertificatService {
	private final  CertificatRepository CertificatRepository;

	public CertificatService(CertificatRepository certificatRepository) {
		super();
		this.CertificatRepository = certificatRepository;
	} 


	public void save(Certificat certificat) {
		CertificatRepository.save(certificat);
	}

	public void saveAll(String chemin) {
		List <Certificat> certificats = parseCertificateDirectory(chemin);
		for (Certificat certificat : certificats) {
			save(certificat);
		}	
	}

	public List<Certificat> findAll() {
		return this.CertificatRepository.findAll();
	}
	

	public List<Certificat> listCertificates(String chemin) {
		return parseCertificateDirectory(chemin);

	}


	public Certificat findOne (String id) {
		return this.CertificatRepository.findOne(id);
	}

	private Certificat retrieveCertificate(String chemin, String name) {
		FileInputStream fils;
		Certificat certificat = null;
		try {
			fils = new FileInputStream(chemin + name);

			String extensionCertificat = name.substring(name.indexOf(".") + 1, name.length());
			if ("cer".equals(extensionCertificat)) {
				certificat = createCertificate(CertificateType.PUBLIC, (X509Certificate) getPublicCertificate(fils));
			}
			else {
				if ("p12".equals(extensionCertificat)) {
				certificat = createCertificate(CertificateType.PRIVATE, (X509Certificate) getPrivateCertificate(fils));
			   }  
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return certificat;
	}




	//Creer un objet de type Certificat
	private Certificat createCertificate(CertificateType typeCle, Certificate cert) {
		Certificat certificat;
		X509Certificate x509Certificate = (X509Certificate) cert;
		certificat = new Certificat();
		certificat.setDn(x509Certificate.getIssuerDN().toString());
		certificat.setCn(x509Certificate.getSubjectX500Principal().toString());
		certificat.setSatrtdate(x509Certificate.getNotBefore());
		certificat.setEnddate(x509Certificate.getNotAfter());
		certificat.setCle_public("");
		certificat.setCle_prive("");
		certificat.setPublicPrive(typeCle.toString());
		certificat.setType("V"+x509Certificate.getVersion());
		return certificat;
	}

	//Retourne un certificat publique
	private Certificate getPublicCertificate(FileInputStream fils) {
		CertificateFactory cf;
		Certificate  certificate = null;
		try {
			cf = CertificateFactory.getInstance("X.509");
			certificate = cf.generateCertificate(fils);
		} catch (CertificateException e) {
			e.printStackTrace();
		}
		return certificate;
	}

	// Retourne un certificat prive
	private X509Certificate getPrivateCertificate(FileInputStream fils) {
		KeyStore p12;
		X509Certificate x509Certificate = null;
		try {
			p12 = KeyStore.getInstance("pkcs12");
			p12.load(fils, "password".toCharArray());
			x509Certificate = (X509Certificate) p12.getCertificate("1");
		} 

		catch (KeyStoreException e1) {
			e1.printStackTrace();
		}

		catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} 

		catch (CertificateException e1) {
			e1.printStackTrace();
		}

		catch (IOException e1) {
			e1.printStackTrace();
		}

		return x509Certificate;
	}

	// parcours le chemin qui contient tous les certificats
	public List <Certificat> parseCertificateDirectory (String chemin) {
		System.out.println("chemin : " + chemin);
		List <Certificat> certifs = new ArrayList<>();
		File folder = new File(chemin);
		File[] listOfFiles = folder.listFiles();

		for (File file : listOfFiles) {
			
			if (file.isFile()) {
				System.out.println(file.getName());
				certifs.add(retrieveCertificate(chemin, file.getName()));
			}
		
						
		}
		return certifs;
	}
}
