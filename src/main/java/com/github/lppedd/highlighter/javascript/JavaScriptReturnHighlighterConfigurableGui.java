package com.github.lppedd.highlighter.javascript;

import javax.swing.*;

import org.jetbrains.annotations.NotNull;

import com.intellij.ui.components.JBCheckBox;
import com.intellij.ui.components.JBLabel;

/**
 * @author Edoardo Luppi
 */
public class JavaScriptReturnHighlighterConfigurableGui {
  private JPanel rootPanel;
  private JBLabel info;
  private JBCheckBox isOnlyTopLevelReturns;

  @NotNull
  public JPanel getRootPanel() {
    return rootPanel;
  }

  @NotNull
  public JBLabel getInfo() {
    return info;
  }

  @NotNull
  public JBCheckBox getOnlyTopLevelReturns() {
    return isOnlyTopLevelReturns;
  }

  @NotNull
  public Boolean isOnlyTopLevelReturns() {
    return isOnlyTopLevelReturns.isSelected();
  }

  public void setOnlyTopLevelReturns(@NotNull final Boolean value) {
    isOnlyTopLevelReturns.setSelected(value);
  }
}
