/**
 * Copyright (c) 2016 European Organisation for Nuclear Research (CERN), All Rights Reserved.
 */

package org.minifx.workbench.examples.simpledemo;

import org.minifx.workbench.annotations.Icon;
import org.minifx.workbench.annotations.Name;
import org.minifx.workbench.domain.Perspective;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;

@Name("Dashboard")
@Icon(icon = FontAwesomeIcon.AMBULANCE, color = "green")
public interface DashboardPerspective extends Perspective {
    /* marker */
}
