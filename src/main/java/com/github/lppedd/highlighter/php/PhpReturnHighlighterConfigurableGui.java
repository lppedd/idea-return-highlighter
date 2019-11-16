package com.github.lppedd.highlighter.php;

import javax.swing.*;

import org.jetbrains.annotations.NotNull;

import com.github.lppedd.highlighter.ReturnHighlighterBundle;
import com.intellij.ui.components.JBLabel;

/**
 * @author Edoardo Luppi
 */
public class PhpReturnHighlighterConfigurableGui {
  private JPanel rootPanel;
  private JBLabel info;
  private JBLabel noOptionsAvailable;

  public PhpReturnHighlighterConfigurableGui(final ReturnHighlighterBundle bundle) {
    this();
    finishUpComponents(bundle);
  }

  private PhpReturnHighlighterConfigurableGui() {}

  @NotNull
  public JPanel getRootPanel() {
    return rootPanel;
  }

  private void finishUpComponents(final ReturnHighlighterBundle bundle) {
    info.setText(bundle.get("rh.settings.custom.php"));
    noOptionsAvailable.setText(bundle.get("rh.settings.custom.noOptionsAvailable"));
  }
}
