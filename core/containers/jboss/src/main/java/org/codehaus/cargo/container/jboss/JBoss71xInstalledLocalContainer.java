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
package org.codehaus.cargo.container.jboss;


import org.codehaus.cargo.container.ContainerCapability;
import org.codehaus.cargo.container.configuration.LocalConfiguration;
import org.codehaus.cargo.container.jboss.internal.JBoss7xContainerCapability;

/**
 * JBoss 7.1.x series container implementation.
 * 
 * @version $Id$
 */
public class JBoss71xInstalledLocalContainer extends JBoss7xInstalledLocalContainer
{
    /**
     * JBoss 7.1.x series unique id.
     */
    public static final String ID = "jboss71x";

    /**
     * Capability of the JBoss container.
     */
    private static final ContainerCapability CAPABILITY = new JBoss7xContainerCapability();

    /**
     * {@inheritDoc}
     * @see JBoss7xInstalledLocalContainer#JBoss7xInstalledLocalContainer(LocalConfiguration)
     */
    public JBoss71xInstalledLocalContainer(LocalConfiguration configuration)
    {
        super(configuration);
    }

    /**
     * {@inheritDoc}
     * @see org.codehaus.cargo.container.Container#getId()
     */
    @Override
    public String getId()
    {
        return ID;
    }

    /**
     * {@inheritDoc}
     * @see org.codehaus.cargo.container.Container#getName()
     */
    @Override
    public String getName()
    {
        return "JBoss " + getVersion("7.1.x");
    }

    /**
     * {@inheritDoc}
     * @see org.codehaus.cargo.container.Container#getCapability()
     */
    @Override
    public ContainerCapability getCapability()
    {
        return CAPABILITY;
    }

}
