package fr.lcl.certificats.domain;

public enum CertificateUsage {
	SSLCLIENT("SSLCLIENT"),
	SSLSERVER("SSLSERVER"),
	JETONV3("JETONV3");
	
	private String id;
	
	private CertificateUsage(final String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}
}
