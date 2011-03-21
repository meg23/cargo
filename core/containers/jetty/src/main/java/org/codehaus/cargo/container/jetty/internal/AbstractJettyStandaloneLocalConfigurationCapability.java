/*
 * ========================================================================
 *
 * Codehaus CARGO, copyright 2004-2011 Vincent Massol.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * ========================================================================
 */
package org.codehaus.cargo.container.jetty.internal;

import java.util.HashMap;
import java.util.Map;

import org.codehaus.cargo.container.jetty.JettyPropertySet;
import org.codehaus.cargo.container.property.GeneralPropertySet;
import org.codehaus.cargo.container.property.ServletPropertySet;
import org.codehaus.cargo.container.spi.configuration.AbstractStandaloneLocalConfigurationCapability;

/**
 * Capabilities of the Jetty's
 * {@link org.codehaus.cargo.container.jetty.internal.AbstractJettyStandaloneLocalConfiguration}
 * configuration for installed containers.
 * 
 * @version $Id$
 */
public abstract class AbstractJettyStandaloneLocalConfigurationCapability
        extends AbstractStandaloneLocalConfigurationCapability
{
    /**
     * Configuration-specific supports Map.
     */
    private Map<String, Boolean> supportsMap;

    /**
     * Initialize the configuration-specific supports Map.
     */
    public AbstractJettyStandaloneLocalConfigurationCapability()
    {
        super();

        this.supportsMap = new HashMap<String, Boolean>();

        this.supportsMap.put(GeneralPropertySet.PROTOCOL, Boolean.FALSE);
        this.supportsMap.put(GeneralPropertySet.HOSTNAME, Boolean.FALSE);

        this.supportsMap.put(ServletPropertySet.PORT, Boolean.FALSE);
        this.supportsMap.put(ServletPropertySet.USERS, Boolean.FALSE);

        this.supportsMap.put(JettyPropertySet.SESSION_PATH, Boolean.TRUE);
        this.supportsMap.put(JettyPropertySet.USE_FILE_MAPPED_BUFFER, Boolean.TRUE);
    }

    /**
     * {@inheritDoc}
     * @see AbstractStandaloneLocalConfigurationCapability#getPropertySupportMap()
     */
    @Override
    protected Map<String, Boolean> getPropertySupportMap()
    {
        return this.supportsMap;
    }
}