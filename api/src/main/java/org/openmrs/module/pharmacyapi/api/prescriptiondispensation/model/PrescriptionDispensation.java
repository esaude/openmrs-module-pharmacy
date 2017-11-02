/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
/**
 * 
 */
package org.openmrs.module.pharmacyapi.api.prescriptiondispensation.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.openmrs.Encounter;
import org.openmrs.Patient;
import org.openmrs.module.pharmacyapi.api.common.model.BaseOpenmrsMetadataWrapper;
import org.openmrs.module.pharmacyapi.api.prescriptiondispensation.dao.PrescriptionDispensationDAO;

/**
 *
 */
@NamedQueries(value = {
        @NamedQuery(name = PrescriptionDispensationDAO.QUERY_NAME.findByUuid, query = PrescriptionDispensationDAO.QUERY.findByUuid),
        @NamedQuery(name = PrescriptionDispensationDAO.QUERY_NAME.findByPrescription, query = PrescriptionDispensationDAO.QUERY.findByPrescription),
        @NamedQuery(name = PrescriptionDispensationDAO.QUERY_NAME.findByPatientUuid, query = PrescriptionDispensationDAO.QUERY.findByPatientUuid),
        @NamedQuery(name = PrescriptionDispensationDAO.QUERY_NAME.findByDispensationEncounter, query = PrescriptionDispensationDAO.QUERY.findByDispensationEncounter),
        @NamedQuery(name = PrescriptionDispensationDAO.QUERY_NAME.findByFila, query = PrescriptionDispensationDAO.QUERY.findByFila),
        @NamedQuery(name = PrescriptionDispensationDAO.QUERY_NAME.findLastByPrescription, query = PrescriptionDispensationDAO.QUERY.findLastByPrescription) })
@Entity
@Table(name = "phm_prescription_dispensation")
public class PrescriptionDispensation extends BaseOpenmrsMetadataWrapper {
	
	private static final long serialVersionUID = 5970480895598422427L;
	
	@Id
	@GeneratedValue
	@Column(name = "prescription_dispensation_id")
	private Integer prescriptionDispensationId;
	
	@ManyToOne
	@JoinColumn(name = "prescription_id")
	private Encounter prescription;
	
	@ManyToOne
	@JoinColumn(name = "dispensation_id")
	private Encounter dispensation;
	
	@ManyToOne
	@JoinColumn(name = "fila_id")
	private Encounter fila;
	
	@ManyToOne
	@JoinColumn(name = "patient_id")
	private Patient patient;
	
	public PrescriptionDispensation(Patient patient, Encounter prescription, Encounter dispensation) {
		
		this.patient = patient;
		this.prescription = prescription;
		this.dispensation = dispensation;
	}
	
	public PrescriptionDispensation() {
		
	}
	
	@Override
	public Integer getId() {
		
		return this.prescriptionDispensationId;
	}
	
	@Override
	public void setId(Integer id) {
		this.prescriptionDispensationId = id;
	}
	
	public Encounter getPrescription() {
		return prescription;
	}
	
	public void setPrescription(Encounter prescription) {
		this.prescription = prescription;
	}
	
	public Encounter getDispensation() {
		return dispensation;
	}
	
	public void setDispensation(Encounter dispensation) {
		this.dispensation = dispensation;
	}
	
	public Patient getPatient() {
		return patient;
	}
	
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	
	public Encounter getFila() {
		return fila;
	}
	
	public void setFila(Encounter fila) {
		this.fila = fila;
	}
}
