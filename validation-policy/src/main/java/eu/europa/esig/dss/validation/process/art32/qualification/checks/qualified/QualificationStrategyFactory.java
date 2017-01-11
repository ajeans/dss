package eu.europa.esig.dss.validation.process.art32.qualification.checks.qualified;

import eu.europa.esig.dss.validation.process.art32.EIDASConstants;
import eu.europa.esig.dss.validation.process.art32.qualification.checks.qualified.cert.QualificationByCertificatePostEIDAS;
import eu.europa.esig.dss.validation.process.art32.qualification.checks.qualified.cert.QualificationByCertificatePreEIDAS;
import eu.europa.esig.dss.validation.process.art32.qualification.checks.qualified.tl.QualificationByTL;
import eu.europa.esig.dss.validation.reports.wrapper.CertificateWrapper;
import eu.europa.esig.dss.validation.reports.wrapper.TrustedServiceWrapper;

public final class QualificationStrategyFactory {

	private QualificationStrategyFactory() {
	}

	public static QualificationStrategy createQualificationFromCert(CertificateWrapper signingCertificate) {
		if (EIDASConstants.EIDAS_DATE.before(signingCertificate.getNotBefore())) {
			return new QualificationByCertificatePreEIDAS(signingCertificate);
		} else {
			return new QualificationByCertificatePostEIDAS(signingCertificate);
		}
	}

	public static QualificationStrategy createQualificationFromTL(TrustedServiceWrapper trustedService, CertificateWrapper signingCertificate,
			QualifiedStatus qualifiedStatusFromCert) {
		return new QualificationByTL(trustedService, signingCertificate, qualifiedStatusFromCert);
	}

}
