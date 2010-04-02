/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
package org.openmrs.module.reporting.cohort.definition.evaluator;

import org.openmrs.Cohort;
import org.openmrs.annotation.Handler;
import org.openmrs.api.context.Context;
import org.openmrs.module.reporting.cohort.definition.CohortDefinition;
import org.openmrs.module.reporting.cohort.definition.SqlCohortDefinition;
import org.openmrs.module.reporting.cohort.query.service.CohortQueryService;
import org.openmrs.module.reporting.evaluation.EvaluationContext;

/**
 * Evaluates a sql query and returns a cohort
 */
@Handler(supports={SqlCohortDefinition.class})
public class SqlCohortDefinitionEvaluator implements CohortDefinitionEvaluator {

	/**
	 * Default Constructor
	 */
	public SqlCohortDefinitionEvaluator() {}
	
	/**
     * @see CohortDefinitionEvaluator#evaluateCohort(CohortDefinition, EvaluationContext)
     */
    public Cohort evaluate(CohortDefinition cohortDefinition, EvaluationContext evaluationContext) {
    	Cohort cohort = new Cohort();
    	SqlCohortDefinition sqlCohortDefinition = (SqlCohortDefinition) cohortDefinition;    	

    	cohort = Context.getService(CohortQueryService.class).executeSqlQuery(
    			sqlCohortDefinition.getQueryDefinition().getQueryString(), 
    			evaluationContext.getParameterValues());
    		
    	return cohort;
    }
}