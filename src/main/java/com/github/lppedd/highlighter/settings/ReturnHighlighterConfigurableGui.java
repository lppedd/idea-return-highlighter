package com.github.lppedd.highlighter.settings;

import java.awt.*;

import javax.swing.*;

import org.jetbrains.annotations.NotNull;

import com.github.lppedd.highlighter.ReturnHighlighterBundle;
import com.intellij.ui.components.JBLabel;
import com.intellij.util.ui.JBUI;
import com.intellij.util.ui.JBUI.Borders;

/**
 * @author Edoardo Luppi
 */
public class ReturnHighlighterConfigurableGui {
  private JPanel rootPanel;
  private JPanel listPanel;

  @NotNull
  public JPanel getRootPanel() {
    return rootPanel;
  }

  @NotNull
  public JPanel getListPanel() {
    return listPanel;
  }

  private void createUIComponents() {
    final JPanel rootPanel = new JPanel();
    rootPanel.setLayout(new BorderLayout());
    rootPanel.add(
        new JBLabel(ReturnHighlighterBundle.INSTANCE.get("rh.settings.custom.global")),
        BorderLayout.NORTH
    );

    final JPanel listPanel = buildListPanel();
    rootPanel.add(listPanel, BorderLayout.WEST);

    this.rootPanel = rootPanel;
    this.listPanel = listPanel;
  }

  private JPanel buildListPanel() {
    final JPanel listPanel = new JPanel();
    listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
    listPanel.setBorder(Borders.emptyLeft(10));
    listPanel.add(Box.createRigidArea(JBUI.size(0, 10)));
    return listPanel;
  }
}
