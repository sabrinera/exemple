package fr.lcl.certificats.certificats.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

import fr.lcl.certificats.domain.CertificateType;
import fr.lcl.certificats.domain.CertificateUsage;

@Entity(name="certificat")
public class Certificat {

	@Id
	  private String dn;
	  private String cn;
	  @Temporal(TemporalType.TIMESTAMP)
	  @Column(name="datedebut")
	  @JsonFormat(pattern="dd.MM.yyyy")
	  private Date startdate;
	  @Column(name="datefin")
	  @JsonFormat(pattern="dd.MM.yyyy")
	  private Date enddate;
	  private String type;
	  private String cle_public;
	  private String cle_prive;
	  private String commentaire;
	  private String passeword;
	  private String destinataire;
	  @Column(name="public_prive")
	  private String publicPrive;
	
	  
	  public String getDn() {
		return dn;
		
	}
	public void setDn(String dn) {
		this.dn = dn;
	}
	public String getCn() {
		return cn;
	}
	public void setCn(String cn) {
		this.cn = cn;
	}
	public Date getStartdate() {
		return startdate;
	}
	
	public void setSatrtdate(Date startdate) {
		this.startdate = startdate;
	}
	
	public Date getEnddate() {
		return enddate;
	}
	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCle_public() {
		return cle_public;
	}
	public void setCle_public(String cle_public) {
		this.cle_public = cle_public;
	}
	public String getCle_prive() {
		return cle_prive;
	}
	public void setCle_prive(String cle_prive) {
		this.cle_prive = cle_prive;
	}
	public String getCommentaire() {
		return commentaire;
	}
	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}
	public String getPasseword() {
		return passeword;
	}
	public void setPasseword(String passeword) {
		this.passeword = passeword;
	}
	public String getDestinataire() {
		return destinataire;
	}
	public void setDestinataire(String destinataire) {
		this.destinataire = destinataire;
	}
	
	
	
	public String getPublicPrive() {
		return publicPrive;
	}
	public void setPublicPrive(String publicPrive) {
		this.publicPrive = publicPrive;
	}
	public Certificat() {
	}
	
	
	public Certificat(String dn, String cn, Date startdate, Date enddate, String type, String cle_public,
			String cle_prive, String commentaire, String passeword, String destinataire) {
		super();
		this.dn = dn;
		this.cn = cn;
		this.startdate = startdate;
		this.enddate = enddate;
		this.type = type;
		this.cle_public = cle_public;
		this.cle_prive = cle_prive;
		this.commentaire = commentaire;
		this.passeword = passeword;
		this.destinataire = destinataire;
	}
	  
	public Certificat(Builder builder) {
		super();
		this.dn = builder.dn;
		this.cn = builder.cn;
		this.startdate = builder.startdate;
		this.enddate = builder.enddate;
		this.type = builder.certificateUsage.getId();
		this.cle_public = "";
		this.cle_prive = "";
		this.publicPrive = builder.certificateType.name();
		
		this.commentaire = builder.commentaire;
		this.destinataire = builder.destinataire;
		this.passeword = builder.passeword;
		
		if(CertificateType.PRIVATE.equals(builder.certificateType)) {
			if(builder.passeword == null) {
				throw new IllegalStateException("Vous devez ajouter un mot de passe quand le certificat est de type private");
			}
		}
	}

	public static class Builder {
		  private String dn;
		  private String cn;
		  private Date startdate;
		  private Date enddate;
		  private String type;
		  private CertificateUsage certificateUsage;
		  private CertificateType certificateType;
		  private String commentaire;
		  private String passeword;
		  private String destinataire;
		  
		  public Builder(String dn, String cn, Date startDate, Date endDate, CertificateUsage certificateUsage, CertificateType certificateType) {
			  this.dn = dn;
			  this.cn = cn;
			  this.startdate = startDate;
			  this.enddate = endDate;
			  this.certificateUsage = certificateUsage;
			  this.certificateType = certificateType;
		  }
		  
		  public Builder commentaire(final String commentaire) {
			  this.commentaire = commentaire;
			  return this;
		  }
		  
		  public Builder password(final String password) {
			  this.passeword = password;
			  return this;
		  }
		  
		  public Builder destinataire(final String destinataire) {
			  this.destinataire = destinataire;
			  return this;
		  }
		  
		  public Certificat build() {
			  return new Certificat(this);
		  }

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}
		
/*	@Override
		public String toString() {
		return "certificat{"+
		"id="+ cn +
		",DN='"+ dn +'\''+
		",startdate=" +startdate+ '}'; */
		}
	}
	
	  

