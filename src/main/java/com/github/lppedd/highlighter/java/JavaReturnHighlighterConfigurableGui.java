package com.github.lppedd.highlighter.java;

import javax.swing.*;

import org.jetbrains.annotations.NotNull;

import com.github.lppedd.highlighter.ReturnHighlighterBundle;
import com.intellij.ui.ContextHelpLabel;
import com.intellij.ui.components.JBCheckBox;
import com.intellij.ui.components.JBLabel;

/**
 * @author Edoardo Luppi
 */
public class JavaReturnHighlighterConfigurableGui {
  private JPanel rootPanel;
  private JBLabel info;
  private JPanel skipSimpleGettersPanel;
  private JBCheckBox isOnlyTopLevelReturns;
  private JBCheckBox isSkipSimpleGetters;

  public JavaReturnHighlighterConfigurableGui(final ReturnHighlighterBundle bundle) {
    this();
    finishUpComponents(bundle);
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

  @NotNull
  public Boolean isSkipSimpleGetters() {
    return isSkipSimpleGetters.isSelected();
  }

  public void setOnlyTopLevelReturns(@NotNull final Boolean value) {
    isOnlyTopLevelReturns.setSelected(value);
  }

  public void setSkipSimpleGetters(@NotNull final Boolean value) {
    isSkipSimpleGetters.setSelected(value);
  }

  private void finishUpComponents(final ReturnHighlighterBundle bundle) {
    info.setText(bundle.get("rh.settings.custom.java"));
    isOnlyTopLevelReturns.setText(bundle.get("rh.settings.custom.java.topLevel"));
    isSkipSimpleGetters.setText(bundle.get("rh.settings.custom.java.simpleGetters"));

    final String simpleGettersTooltip = bundle.get("rh.settings.custom.java.simpleGetters.tooltip");
    skipSimpleGettersPanel.add(ContextHelpLabel.create(simpleGettersTooltip));
  }
}
