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
package org.openmrs.module.reporting.web.widget.handler;

import java.io.IOException;
import java.io.Writer;

import org.openmrs.annotation.Handler;
import org.openmrs.module.evaluation.parameter.Mapped;
import org.openmrs.module.evaluation.parameter.Parameterizable;
import org.openmrs.module.htmlwidgets.web.WidgetConfig;
import org.openmrs.module.htmlwidgets.web.handler.WidgetHandler;
import org.openmrs.util.HandlerUtil;

/**
 * FieldGenHandler for String Types
 */
@Handler(supports={Mapped.class}, order=50)
public class MappedHandler extends WidgetHandler {
	
	/** 
	 * @see WidgetHandler#render(WidgetConfig)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public void render(WidgetConfig config, Writer w) throws IOException {
		
		if (config.getGenericTypes() == null || config.getGenericTypes().length != 1) {
			throw new IllegalArgumentException("Invalid generic types.");
		}
		Class<? extends Parameterizable> parentType = (Class<? extends Parameterizable>) config.getType();
		Class<? extends Parameterizable> childType = (Class<? extends Parameterizable>)config.getGenericTypes()[0];
		WidgetHandler handler = HandlerUtil.getPreferredHandler(WidgetHandler.class, childType);
		if (handler == null) {
			throw new RuntimeException("No Preferred Handler found for: " + childType);
		}

		w.write("This will be a widget for a " + parentType.getSimpleName() + "&lt;" + childType.getSimpleName() + "&gt;");
	}
	
	/** 
	 * @see WidgetHandler#parse(String)
	 */
	@Override
	public Object parse(String input, Class<?> type) {
		throw new IllegalArgumentException("Not supported");
	}
}