/**
 * Copyright (c) 2016 European Organisation for Nuclear Research (CERN), All Rights Reserved.
 */

package org.minifx.workbench.domain;

import static com.google.common.collect.ImmutableList.copyOf;
import static java.util.Collections.singletonList;
import static org.minifx.workbench.util.MiniFxComponents.nodesFrom;

import java.util.ArrayList;
import java.util.List;

import org.minifx.workbench.css.MiniFxCssConstants;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

/**
 * Main JavaFX Pane for a MiniFx application. It has a toolbar for perspective switching at the top. The active
 * perspective is integrated in the center.
 */
public class MainPane extends BorderPane {

    private static final HBox DEFAULT_FILLER = new HBox();

    public MainPane(List<AbstractPerspectiveInstance> perspectives, List<ToolbarItem> toolbarItems) {
        this(perspectives, toolbarItems, DEFAULT_FILLER);
    }

    public MainPane(List<AbstractPerspectiveInstance> perspectives, List<ToolbarItem> toolbarItems, Node filler) {
        List<Node> perspectiveNodes = perspectiveButtons(copyOf(perspectives));
        List<Node> toolbarNodes = nodesFrom(copyOf(toolbarItems));

        this.setTop(createToolbar(perspectiveNodes, toolbarNodes, filler));

        if (!perspectiveNodes.isEmpty()) {
            ((ToggleButton) perspectiveNodes.get(0)).fire();
        }
    }

    private ToolBar createToolbar(List<Node> perspectiveNodes, List<Node> toolbarNodes, Node filler) {
        HBox toolbarBox = horizontalBoxOf(toolbarNodes, Pos.TOP_LEFT);
        HBox centralBox = horizontalBoxOf(singletonList(filler), Pos.TOP_CENTER);
        HBox perspectivesBox = horizontalBoxOf(perspectiveNodes, Pos.TOP_RIGHT);

        HBox.setHgrow(centralBox, Priority.ALWAYS);

        return new ToolBar(toolbarBox, centralBox, perspectivesBox);
    }

    private HBox horizontalBoxOf(List<Node> nodes, Pos alignment) {
        nodes.forEach(node -> node.getStyleClass().add(MiniFxCssConstants.TOOLBAR_BUTTON_CLASS));
        
        HBox box = new HBox();
        box.setSpacing(3);
        box.setAlignment(alignment);
        box.setFillHeight(true);
        box.getChildren().addAll(nodes);
        return box;
    }

    private List<Node> perspectiveButtons(List<AbstractPerspectiveInstance> perspectives) {
        List<Node> perspectiveButtons = new ArrayList<>();
        ToggleGroup toggleGroup = new ToggleGroup();
        for (AbstractPerspectiveInstance perspective : perspectives) {
            ToggleButton button = new ToggleButton(perspective.name(), perspective.graphic());
            button.setOnAction(evt -> setActive(perspective));
            button.getStyleClass().add(MiniFxCssConstants.PERSPECTIVE_BUTTON_CLASS);

            perspectiveButtons.add(button);
            toggleGroup.getToggles().add(button);
        }
        return perspectiveButtons;
    }

    private void setActive(AbstractPerspectiveInstance perspective) {
        perspective.getStyleClass().add(MiniFxCssConstants.MAIN_PANE_CLASS);
        this.setCenter(perspective);
    }

}