/**
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2007 Sun Microsystems Inc. All Rights Reserved
 *
 * The contents of this file are subject to the terms
 * of the Common Development and Distribution License
 * (the License). You may not use this file except in
 * compliance with the License.
 *
 * You can obtain a copy of the License at
 * https://opensso.dev.java.net/public/CDDLv1.0.html or
 * opensso/legal/CDDLv1.0.txt
 * See the License for the specific language governing
 * permission and limitations under the License.
 *
 * When distributing Covered Code, include this CDDL
 * Header Notice in each file and include the License file
 * at opensso/legal/CDDLv1.0.txt.
 * If applicable, add the following below the CDDL Header,
 * with the fields enclosed by brackets [] replaced by
 * your own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
 *
 * $Id: SessionHAPropertiesViewBean.java,v 1.2 2008/06/25 05:43:12 qcheng Exp $
 *
 */

package com.sun.identity.console.session;

import com.iplanet.jato.model.ModelControlException;
import com.iplanet.jato.view.View;
import com.iplanet.jato.view.event.DisplayEvent;

import com.sun.web.ui.model.CCPageTitleModel;
import com.sun.web.ui.view.pagetitle.CCPageTitle;

import com.sun.identity.console.session.model.SMProfileModel;

public class SessionHAPropertiesViewBean
        extends SessionHAPropertiesBase {

    public static final String DEFAULT_DISPLAY_URL =
            "/console/session/SessionHAProperties.jsp";

    private boolean initialized;

    /**
     * Creates a authentication domains view bean.
     */
    public SessionHAPropertiesViewBean() {
        super(SESSION_HA_PROPERTIES);
        setDefaultDisplayURL(DEFAULT_DISPLAY_URL);
    }

    protected void initialize() {
        if (!initialized) {
            String sessionAttribute = (String) getPageSessionAttribute(SESSION_HA_PROPERTIES);
            if (sessionAttribute != null) {
                initialized = true;
                createPageTitleModel();
                registerChildren();
                super.initialize();
            }
        }
        super.registerChildren();
    }

    protected void registerChildren() {
        if (ptModel != null) {
            ptModel.registerChildren(this);
        }
    }

    protected View createChild(String name) {
        View view = null;
        if (ptModel == null) {
            createPageTitleModel();
        }
        if (name.equals(PAGETITLE)) {
            view = new CCPageTitle(this, ptModel, name);
        } else if ((ptModel != null) && ptModel.isChildSupported(name)) {
            view = ptModel.createChild(this, name);
        } else {
            view = super.createChild(name);
        }

        return view;
    }

    public void beginDisplay(DisplayEvent event)
            throws ModelControlException {
        super.beginDisplay(event);
        SMProfileModel model = (SMProfileModel) getModel();
        if (model != null) {

            // TODO

            // Set our Sub-Tabs and current position, relative to one.
            addSessionsTab(model, 2);
        }
    }

    private void createPageTitleModel() {
        ptModel = new CCPageTitleModel(
                getClass().getClassLoader().getResourceAsStream(
                        "com/sun/identity/console/simplePageTitle.xml"));
    }


}
