package com.github.lppedd.highlighter.javascript;

import javax.swing.*;

import org.jetbrains.annotations.NotNull;

import com.github.lppedd.highlighter.ReturnHighlighterBundle;
import com.intellij.ui.components.JBCheckBox;
import com.intellij.ui.components.JBLabel;

/**
 * @author Edoardo Luppi
 */
public class JavaScriptReturnHighlighterConfigurableGui {
  private JPanel rootPanel;
  private JBLabel info;
  private JBCheckBox isOnlyTopLevelReturns;

  public JavaScriptReturnHighlighterConfigurableGui(final ReturnHighlighterBundle bundle) {
    this();
    info.setText(bundle.get("rh.settings.custom.javascript"));
    isOnlyTopLevelReturns.setText(bundle.get("rh.settings.custom.javascript.topLevel"));
  }

  private JavaScriptReturnHighlighterConfigurableGui() {}

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
