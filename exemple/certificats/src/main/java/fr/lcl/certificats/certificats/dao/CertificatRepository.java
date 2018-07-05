package fr.lcl.certificats.certificats.dao;


import org.springframework.data.jpa.repository.JpaRepository;


import fr.lcl.certificats.certificats.model.Certificat;



public interface CertificatRepository extends JpaRepository<Certificat, String>{
	

}
