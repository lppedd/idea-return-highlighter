package com.github.lppedd.highlighter.java;

import javax.swing.*;

/**
 * @author Edoardo Luppi
 */
public class JavaReturnHighlighterConfigurableGui {
  private JPanel rootPanel;
  private JCheckBox isOnlyTopLevelReturns;

  public JPanel getRootPanel() {
    return rootPanel;
  }

  public Boolean isOnlyTopLevelReturns() {
    return isOnlyTopLevelReturns.isSelected();
  }

  public void setOnlyTopLevelReturns(final Boolean value) {
    isOnlyTopLevelReturns.setSelected(value);
  }
}
