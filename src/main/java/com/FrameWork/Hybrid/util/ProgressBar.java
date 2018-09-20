package com.FrameWork.Hybrid.util;

import javax.swing.JFrame;
import javax.swing.JProgressBar;

@SuppressWarnings("serial")
public class ProgressBar extends JFrame {

	static JProgressBar jb;

	public ProgressBar() {
		jb = new JProgressBar(0, 2000);
		jb.setBounds(40, 40, 160, 30);
		jb.setValue(0);
		jb.setStringPainted(true);
		add(jb);
		setSize(250, 150);
		setLayout(null);
	}

	public void setProgress(String testCase, int value) {
		jb.setValue(value);
		jb.setString(testCase);
	}

}
