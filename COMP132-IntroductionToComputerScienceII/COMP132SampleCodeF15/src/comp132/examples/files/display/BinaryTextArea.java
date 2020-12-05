package comp132.examples.files.display;
import java.awt.event.*;
import java.util.*;

import javax.swing.JTextArea;

public class BinaryTextArea extends JTextArea implements MouseListener {

	private static final long serialVersionUID = 1L;

	private ArrayList<Observer> observers;

	public BinaryTextArea(int rows, int cols) {
		super(rows, cols);
		this.addMouseListener(this);
		observers = new ArrayList<Observer>();
	}

	public void addObserver(Observer obs) {
		observers.add(obs);
	}

	private void notifyObservers() {
		for (Observer obs : observers) {
			obs.update(null, null);
		}
	}

	private String getBinaryByte(byte b) {
		String binary = Integer.toBinaryString(0x000000FF & b);
		
		while (binary.length() < 8) {
			binary = "0" + binary;
		}
		if (binary.length() > 8) {
			binary = binary.substring(binary.length()-8);
		}
		
		return binary.substring(0, 4) + " " + binary.substring(4);
	}

	public void setText(byte[] bytes) {
		String binary = "";
		int address = 0;
		for (byte b : bytes) {
			binary = binary + getBinaryByte(b) + "  ";
			if ((address + 1) % 8 == 0) {
				binary = binary + "\n";
			}
			address++;
		}

		super.setText(binary.trim());
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		int start = getSelectionStart();
		int end = getSelectionEnd();
		String text = getText();
		
		if (end == text.length()) {
			end--;
		}
		if (start == text.length()) {
			start--;
		}
		
		if (start >= 0 && end < text.length()) {
			while (start > 0
					&& !(text.charAt(start) == ' ' && text.charAt(start - 1) == ' ')) {
				start--;
			}
			if (start != 0) {
				start++;
			}

			while (end < text.length()
					&& !(text.charAt(end) == ' ' && text.charAt(end + 1) == ' ')) {
				end++;
			}

			setSelectionStart(start);
			setSelectionEnd(end);
		}

		notifyObservers();
	}

	public byte getSelectedByte() {
		return 0;
	}

	public short getSelectedShort() {
		return 0;
	}

	public int getSelectedInt() {
		return 0;
	}

	public long getSelectedLong() {
		return 0;
	}

	public float getSelectedFloat() {
		return 0;
	}

	public double getSelectedDouble() {
		return 0;
	}

	public byte[] getSelectedBytes() {
		String sel = getSelectedText();
		if (sel != null) {
			String[] bytesStr = sel.split("  ");
			byte[] bytes = new byte[bytesStr.length];
			
			for (int i=0; i<bytesStr.length; i++) {
				if (bytesStr[i].charAt(0) == '\n') {
					bytesStr[i] = bytesStr[i].substring(1);
				}
				String b = bytesStr[i].substring(0,4) + bytesStr[i].substring(5);
				Short shortVal = Short.parseShort(b,2);
				bytes[i] = shortVal.byteValue();
			}
			
			return bytes;
		}
		else {
			return new byte[0];
		}
	}

	public int getNumBytesSelected() {
		byte[] bytes = getSelectedBytes();
		return bytes.length;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

}
