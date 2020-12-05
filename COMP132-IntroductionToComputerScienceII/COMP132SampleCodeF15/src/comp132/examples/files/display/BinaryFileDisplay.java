package comp132.examples.files.display;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.*;
import java.util.*;

import javax.swing.*;

public class BinaryFileDisplay extends JFrame {

	private static final long serialVersionUID = 1L;
	private JFrame me;
	private BinaryTextArea bta;
	private JTextField byteVal;
	private JTextField asciiChar;
	private JTextField shortVal;
	private JTextField intVal;
	private JTextField longVal;
	private JTextField floatVal;
	private JTextField doubleVal;
	private JTextArea UTFStringVal;

	public BinaryFileDisplay() {
		super("Binary File Display");
		me = this;

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Container mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		this.add(mainPanel);

		JButton load = new JButton("Load File");
		load.addActionListener(new LoadListener());
		mainPanel.add(load);

		bta = new BinaryTextArea(10, 52);
		bta.setEditable(false);
		bta.setDragEnabled(false);
		bta.addObserver(new SelectionObserver());
		JScrollPane jp = new JScrollPane(bta);
		mainPanel.add(jp);

		mainPanel.add(getByteBox());
		mainPanel.add(getShortBox());
		mainPanel.add(getIntBox());
		mainPanel.add(getLongBox());
		mainPanel.add(getFloatBox());
		mainPanel.add(getDoubleBox());
		mainPanel.add(getUTFStringBox());

		this.pack();
	}

	private Box getByteBox() {
		Box byteValBox = new Box(BoxLayout.X_AXIS);
		byteValBox.add(new JLabel("Byte: "));
		byteVal = new JTextField(5);
		byteVal.setEditable(false);
		byteVal.setMaximumSize(byteVal.getPreferredSize());
		byteValBox.add(byteVal);
		byteValBox.add(Box.createRigidArea(new Dimension(20, 0)));
		byteValBox.add(new JLabel("ASCII: "));
		asciiChar = new JTextField(2);
		asciiChar.setEditable(false);
		asciiChar.setMaximumSize(asciiChar.getPreferredSize());
		byteValBox.add(asciiChar);
		byteValBox.add(Box.createHorizontalGlue());
		return byteValBox;
	}

	private Box getShortBox() {
		Box shortValBox = new Box(BoxLayout.X_AXIS);
		shortValBox.add(new JLabel("Short: "));
		shortVal = new JTextField(5);
		shortVal.setEditable(false);
		shortVal.setMaximumSize(shortVal.getPreferredSize());
		shortValBox.add(shortVal);
		shortValBox.add(Box.createHorizontalGlue());
		return shortValBox;
	}

	private Box getIntBox() {
		Box intValBox = new Box(BoxLayout.X_AXIS);
		intValBox.add(new JLabel("Integer: "));
		intVal = new JTextField(10);
		intVal.setEditable(false);
		intVal.setMaximumSize(intVal.getPreferredSize());
		intValBox.add(intVal);
		intValBox.add(Box.createHorizontalGlue());
		return intValBox;
	}

	private Box getLongBox() {
		Box longValBox = new Box(BoxLayout.X_AXIS);
		longValBox.add(new JLabel("Long: "));
		longVal = new JTextField(15);
		longVal.setEditable(false);
		longVal.setMaximumSize(longVal.getPreferredSize());
		longValBox.add(longVal);
		longValBox.add(Box.createHorizontalGlue());
		return longValBox;
	}

	private Box getFloatBox() {
		Box floatValBox = new Box(BoxLayout.X_AXIS);
		floatValBox.add(new JLabel("Float: "));
		floatVal = new JTextField(10);
		floatVal.setEditable(false);
		floatVal.setMaximumSize(floatVal.getPreferredSize());
		floatValBox.add(floatVal);
		floatValBox.add(Box.createHorizontalGlue());
		return floatValBox;
	}

	private Box getDoubleBox() {
		Box doubleValBox = new Box(BoxLayout.X_AXIS);
		doubleValBox.add(new JLabel("Double: "));
		doubleVal = new JTextField(15);
		doubleVal.setEditable(false);
		doubleVal.setMaximumSize(doubleVal.getPreferredSize());
		doubleValBox.add(doubleVal);
		doubleValBox.add(Box.createHorizontalGlue());
		return doubleValBox;
	}

	private Box getUTFStringBox() {
		Box asciiStringBox = new Box(BoxLayout.X_AXIS);
		asciiStringBox.add(new JLabel("UTF8 UNICODE String: "));
		UTFStringVal = new JTextArea(3, 52);
		UTFStringVal.setEditable(false);
		JScrollPane jp = new JScrollPane(UTFStringVal);
		asciiStringBox.add(jp);
		return asciiStringBox;
	}

	private class SelectionObserver implements Observer {
		@Override
		public void update(Observable o, Object arg) {
			byte[] bytes = bta.getSelectedBytes();
			if (bytes.length == 1) {
				byteVal.setText("" + bytes[0]);
				asciiChar.setText(new String(bytes));
			} else {
				byteVal.setText("");
				asciiChar.setText("");
			}

			if (bytes.length == 2) {
				ByteBuffer bb = ByteBuffer.allocate(2);
				bb.put(bytes[0]);
				bb.put(bytes[1]);
				short val = bb.getShort(0);
				shortVal.setText("" + val);
			} else {
				shortVal.setText("");
			}

			if (bytes.length == 4) {
				ByteBuffer bb = ByteBuffer.allocate(4);
				bb.put(bytes[0]);
				bb.put(bytes[1]);
				bb.put(bytes[2]);
				bb.put(bytes[3]);
				int val = bb.getInt(0);
				intVal.setText("" + val);
			} else {
				intVal.setText("");
			}

			if (bytes.length == 4) {
				ByteBuffer bb = ByteBuffer.allocate(4);
				bb.put(bytes[0]);
				bb.put(bytes[1]);
				bb.put(bytes[2]);
				bb.put(bytes[3]);
				float val = bb.getFloat(0);
				floatVal.setText("" + val);
			} else {
				floatVal.setText("");
			}

			if (bytes.length == 8) {
				ByteBuffer bb = ByteBuffer.allocate(8);
				bb.put(bytes[0]);
				bb.put(bytes[1]);
				bb.put(bytes[2]);
				bb.put(bytes[3]);
				bb.put(bytes[4]);
				bb.put(bytes[5]);
				bb.put(bytes[6]);
				bb.put(bytes[7]);
				long val = bb.getLong(0);
				longVal.setText("" + val);
			} else {
				longVal.setText("");
			}

			if (bytes.length == 8) {
				ByteBuffer bb = ByteBuffer.allocate(8);
				bb.put(bytes[0]);
				bb.put(bytes[1]);
				bb.put(bytes[2]);
				bb.put(bytes[3]);
				bb.put(bytes[4]);
				bb.put(bytes[5]);
				bb.put(bytes[6]);
				bb.put(bytes[7]);
				double val = bb.getDouble(0);
				doubleVal.setText("" + val);
			} else {
				doubleVal.setText("");
			}

			UTFStringVal.setText(new String(bytes));
		}
	}

	private class LoadListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String currentDirectory = System.getProperty("user.dir");
			JFileChooser jfc = new JFileChooser(currentDirectory);
			int returnVal = jfc.showOpenDialog(me);

			if (returnVal == JFileChooser.APPROVE_OPTION) {

				byteVal.setText("");
				asciiChar.setText("");
				shortVal.setText("");
				intVal.setText("");
				longVal.setText("");
				floatVal.setText("");
				doubleVal.setText("");
				UTFStringVal.setText("");

				File file = jfc.getSelectedFile();

				FileInputStream fis = null;
				try {
					fis = new FileInputStream(file);
					byte[] data = new byte[fis.available()];
					fis.read(data);
					fis.close();
					bta.setText(data);
				} catch (FileNotFoundException e1) {
					JOptionPane.showInternalMessageDialog(me,
							"Could not open file: " + file.getAbsolutePath(),
							"File Error", JOptionPane.ERROR_MESSAGE);
				} catch (IOException e1) {
					JOptionPane.showInternalMessageDialog(me,
							"Error reading file: " + file.getAbsolutePath(),
							"File Error", JOptionPane.ERROR_MESSAGE);
				} finally {
					if (fis != null) {
						try {
							fis.close();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				}
			}
		}
	}

	public static void main(String[] args) {
		BinaryFileDisplay bfd = new BinaryFileDisplay();
		bfd.setVisible(true);
	}
}
