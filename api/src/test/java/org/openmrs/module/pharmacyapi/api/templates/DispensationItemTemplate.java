/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
/*
 * Friends in Global Health - FGH © 2016
 */
package org.openmrs.module.pharmacyapi.api.templates;

import org.openmrs.module.pharmacyapi.api.dispensation.model.DispensationItem;
import org.openmrs.module.pharmacyapi.api.util.BaseTemplateLoader;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;

/**
 * @author Stélio Moiane
 */
public class DispensationItemTemplate implements BaseTemplateLoader {
	
	public static final String VALID = "VALID";
	
	public static final String NON_ARV = "NON_ARV";
	
	@Override
	public void load() {
		Fixture.of(DispensationItem.class).addTemplate(VALID, new Rule() {
			
			{
				this.add("orderUuid", this.random("1b92a25c-5747-471c-8275-3d150243d9c9",
				    "ed35ed32-bf2c-4cf4-ab7c-38c83a37c548", "0120ed22-f2e9-4818-b9bd-536ac9aec13a"));
				this.add("quantityDispensed", this.random(Double.class, this.range(1.0, 100.0)));
				this.add("dateOfNextPickUp", this.instant("now"));
				this.add("drugRegime", null);
				this.add("prescription", null);
			}
		});
		
		Fixture.of(DispensationItem.class).addTemplate(NON_ARV).inherits(VALID, new Rule() {
			
			{
				this.add("dateOfNextPickUp", null);
			}
		});
	}
}
