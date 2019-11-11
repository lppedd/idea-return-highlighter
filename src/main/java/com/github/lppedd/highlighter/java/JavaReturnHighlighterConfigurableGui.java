package com.github.lppedd.highlighter.java;

import javax.swing.*;

import org.jetbrains.annotations.NotNull;

import com.github.lppedd.highlighter.ReturnHighlighterBundle;
import com.intellij.ui.components.JBCheckBox;
import com.intellij.ui.components.JBLabel;

/**
 * @author Edoardo Luppi
 */
public class JavaReturnHighlighterConfigurableGui {
  private JPanel rootPanel;
  private JBLabel info;
  private JBCheckBox isOnlyTopLevelReturns;

  public JavaReturnHighlighterConfigurableGui(final ReturnHighlighterBundle bundle) {
    this();
    info.setText(bundle.get("rh.settings.custom.java"));
    isOnlyTopLevelReturns.setText(bundle.get("rh.settings.custom.java.topLevel"));
  }

  private JavaReturnHighlighterConfigurableGui() {}

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
}
