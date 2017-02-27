/**
 * Copyright (c) 2016 European Organisation for Nuclear Research (CERN), All Rights Reserved.
 */

package org.minifx.workbench.conf.fullyconfigured;

import static org.minifx.workbench.domain.PerspectivePos.BOTTOM;

import org.minifx.workbench.annotations.Icon;
import org.minifx.workbench.annotations.Name;
import org.minifx.workbench.annotations.Shown;
import org.minifx.workbench.domain.AbstractFxBorderPaneView;
import org.springframework.stereotype.Component;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.scene.control.Label;

@Component
@Shown(in = Perspective1.class, at = BOTTOM)
@Name("Bottom A with Icon")
@Icon(icon = FontAwesomeIcon.AREA_CHART)
public class P1BottomA extends AbstractFxBorderPaneView {

    public P1BottomA () {
        this.setCenter(new Label("New label"));
        
    }
    
}
