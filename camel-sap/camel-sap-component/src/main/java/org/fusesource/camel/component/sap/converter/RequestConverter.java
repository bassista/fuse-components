/**
 * Copyright 2013 Red Hat, Inc.
 * 
 * Red Hat licenses this file to you under the Apache License, version
 * 2.0 (the "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied.  See the License for the specific language governing
 * permissions and limitations under the License.
 * 
 */
package org.fusesource.camel.component.sap.converter;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.camel.Converter;
import org.eclipse.emf.ecore.EObject;
import org.fusesource.camel.component.sap.model.rfc.Request;
import org.fusesource.camel.component.sap.model.rfc.impl.RequestImpl;
import org.fusesource.camel.component.sap.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A Type Converter for SAP Request objects.
 * 
 * @author William Collins <punkhornsw@gmail.com>
 *
 */
@Converter
public enum RequestConverter {
	INSTANCE;
	
	private static final Logger LOG = LoggerFactory.getLogger(RequestConverter.class);

	@Converter(allowNull = true)
	public static Request toRequest(String string) {
		try {
			EObject eObject = Util.unmarshal(string);
			
			if (RequestImpl.class.isInstance(eObject)) {
				return (RequestImpl) eObject;
			}
		} catch (IOException e) {
			// Ignore
			LOG.warn("Failed to convert String to Request", e);
		} 
		return null; 
	}

	@Converter(allowNull = true)
	public static Request toRequest(InputStream in) {
		try {
			EObject eObject = Util.fromInputStream(in);
			
			if (RequestImpl.class.isInstance(eObject)) {
				return (RequestImpl) eObject;
			}
		} catch (IOException e) {
			// Ignore
			LOG.warn("Failed to convert InputStream to Request", e);
		} 
		return null; 
	}

	@Converter(allowNull = true)
	public static Request toRequest(byte[] byteArray) {
		try {
			EObject eObject = Util.unmarshal(new String(byteArray));
			
			if (RequestImpl.class.isInstance(eObject)) {
				return (RequestImpl) eObject;
			}
		} catch (IOException e) {
			// Ignore
			LOG.warn("Failed to convert byte array to Request", e);
		} 
		return null; 
	}

	@Converter(allowNull = true)
	public static String toString(RequestImpl structure) {
		try {
			return Util.marshal(structure);
		} catch (IOException e) {
			LOG.warn("Failed to convert Request to String", e);
			return null;
		}
	}
	
	@Converter(allowNull = true)
	public static OutputStream toOutputStream(RequestImpl structure) {
		try {
			return Util.toOutputStream(structure);
		} catch (IOException e) {
			LOG.warn("Failed to convert Request to OutputStream", e);
			return null;
		}
	}
	
	@Converter(allowNull = true)
	public static InputStream toInputStream(RequestImpl structure) {
		try {
			return Util.toInputStream(structure);
		} catch (IOException e) {
			LOG.warn("Failed to convert Request to InputStream", e);
			return null;
		}
	}
	
}
