package com.github.lppedd.highlighter.python;

import javax.swing.*;

import org.jetbrains.annotations.NotNull;

import com.github.lppedd.highlighter.ReturnHighlighterBundle;
import com.intellij.ui.components.JBCheckBox;
import com.intellij.ui.components.JBLabel;

/**
 * @author Edoardo Luppi
 */
public class PythonReturnHighlighterConfigurableGui {
  private JPanel rootPanel;
  private JBLabel info;
  private JBCheckBox isOnlyTopLevelReturns;

  public PythonReturnHighlighterConfigurableGui(@NotNull final ReturnHighlighterBundle bundle) {
    this();
    finishUpComponents(bundle);
  }

  private PythonReturnHighlighterConfigurableGui() {}

  @NotNull
  public JPanel getRootPanel() {
    return rootPanel;
  }

  @NotNull
  public Boolean isOnlyTopLevelReturns() {
    return isOnlyTopLevelReturns.isSelected();
  }

  public void setOnlyTopLevelReturns(@NotNull final Boolean value) {
    isOnlyTopLevelReturns.setSelected(value);
  }

  private void finishUpComponents(@NotNull final ReturnHighlighterBundle bundle) {
    info.setText(bundle.get("rh.settings.custom.python"));
    isOnlyTopLevelReturns.setText(bundle.get("rh.settings.custom.python.topLevel"));
  }
}
