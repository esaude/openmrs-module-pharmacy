/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.pharmacyapi.api.pharmacyheuristic.service;

import java.util.List;

import org.openmrs.Drug;
import org.openmrs.Encounter;
import org.openmrs.EncounterType;
import org.openmrs.Obs;
import org.openmrs.Order;
import org.openmrs.Patient;
import org.openmrs.api.APIException;
import org.openmrs.api.context.Context;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.pharmacyapi.api.common.util.MappedEncounters;
import org.openmrs.module.pharmacyapi.api.pharmacyheuristic.dao.PharmacyHeuristicDAO;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class PharmacyHeuristicServiceImpl extends BaseOpenmrsService implements PharmacyHeuristicService {
	
	private PharmacyHeuristicDAO pharmacyHeuristicDAO;
	
	@Override
	public void setPharmacyHeuristicDAO(PharmacyHeuristicDAO pharmacyHeuristicDAO) {
		this.pharmacyHeuristicDAO = pharmacyHeuristicDAO;
	}
	
	@Override
	public EncounterType getEncounterTypeByPatientAge(Patient patient) {
		
		if (patient != null) {
			
			return Context.getEncounterService().getEncounterTypeByUuid(
			    patient.getAge() < 15 ? MappedEncounters.ARV_FOLLOW_UP_CHILD : MappedEncounters.ARV_FOLLOW_UP_ADULT);
		}
		
		throw new APIException("Cannot find encounterType for non given patient");
	}
	
	@Override
	public Drug findDrugByOrderUuid(String uuid) {
		return this.pharmacyHeuristicDAO.findDrugByOrderUuid(uuid);
	}
	
	@Override
	public Encounter findEncounterByPatientAndEncounterTypeAndOrder(Patient patient, EncounterType encounterType, Order order) {
		return this.pharmacyHeuristicDAO.findEncounterByPatientAndEncounterTypeAndOrder(patient, encounterType, order);
	}
	
	@Override
	public List<Obs> findObsByOrder(Order order) {
		
		return this.pharmacyHeuristicDAO.findObsByOrder(order);
	}
}
