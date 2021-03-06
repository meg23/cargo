/*
 * ========================================================================
 *
 * Codehaus CARGO, copyright 2004-2011 Vincent Massol, 2012-2017 Ali Tokmen.
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
package org.codehaus.cargo.container.configuration.script;

import java.util.Hashtable;
import java.util.Map;

import org.apache.tools.ant.types.FilterChain;
import org.codehaus.cargo.container.configuration.Configuration;
import org.codehaus.cargo.container.configuration.LocalConfiguration;
import org.codehaus.cargo.container.configuration.entry.Resource;
import org.codehaus.cargo.container.internal.util.ResourceUtils;
import org.codehaus.cargo.util.AntUtils;

/**
 * Implementation of general functionality for configuration script commands.
 */
public abstract class AbstractScriptCommand implements ScriptCommand
{

    /**
     * New line.
     */
    private static final String NEW_LINE = System.getProperty("line.separator");

    /**
     * Container configuration.
     */
    private Configuration configuration;

    /**
     * Path to configuration script resources.
     */
    private String resourcePath;

    /**
     * Resource utility class.
     */
    private ResourceUtils resourceUtils;

    /**
     * Ant utility class.
     */
    private AntUtils antUtils;

    /**
     * Sets configuration containing all needed information for building configuration scripts.
     *
     * @param configuration Container configuration.
     * @param resourcePath Path to configuration script resources.
     */
    public AbstractScriptCommand(Configuration configuration, String resourcePath)
    {
        this.configuration = configuration;
        this.resourcePath = resourcePath;
        this.resourceUtils = new ResourceUtils();
        this.antUtils = new AntUtils();
    }

    /**
     * @return Filtered script.
     */
    @Override
    public String readScript()
    {
        FilterChain filterChain = new FilterChain();
        antUtils.addTokensToFilterChain(filterChain, configuration.getProperties());

        Map<String, String> propertiesMap = new Hashtable<String, String>();
        addConfigurationScriptProperties(propertiesMap);
        antUtils.addTokensToFilterChain(filterChain, propertiesMap);

        return resourceUtils.readResource(resourcePath + getScriptRelativePath(), filterChain,
                "UTF-8") + NEW_LINE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isApplicable()
    {
        return true;
    }

    /**
     * @return Relative path to resource being read. 
     */
    protected abstract String getScriptRelativePath();

    /**
     * Add custom properties needed for configuration script filtering. 
     *
     * @param propertiesMap Map of additional custom properties.
     */
    protected void addConfigurationScriptProperties(Map<String, String> propertiesMap)
    {
    };

    /**
     * @param type Resource type.
     * @return Resource of defined type.
     */
    protected Resource findResource(String type)
    {
        Resource foundResource = null;
        for (Resource resource : ((LocalConfiguration) configuration).getResources())
        {
            if (type.equals(resource.getType()))
            {
                foundResource = resource;
            }
        }
        return foundResource;
    }

    /**
     * @return Container configuration.
     */
    protected Configuration getConfiguration()
    {
        return configuration;
    }
}
