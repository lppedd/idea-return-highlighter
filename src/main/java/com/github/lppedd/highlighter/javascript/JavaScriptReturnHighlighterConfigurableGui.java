package com.github.lppedd.highlighter.javascript;

import javax.swing.*;

/**
 * @author Edoardo Luppi
 */
public class JavaScriptReturnHighlighterConfigurableGui {
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
