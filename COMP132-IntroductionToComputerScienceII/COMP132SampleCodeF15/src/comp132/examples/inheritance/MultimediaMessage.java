package comp132.examples.inheritance;

/**
 * Class representing a Multimedia Message. This class is a sub-class of the
 * TextMessage class. It inherits methods related to the text portion of the
 * method, adds methods related to the multimedia content and overrides
 * inherited methods whose behavior needed to change.
 * 
 * @author Grant Braught
 * @author Dickinson College
 * @version Sep 13, 2009
 */
public class MultimediaMessage extends TextMessage {

    private String fileName;
    private int fileSize;

    public MultimediaMessage(long fromNumber, long toNumber, String msgText) {
        super(fromNumber, toNumber, msgText);
        fileName = "";
        fileSize = 0;
    }

    public void attachFile(String file, int size) {
        fileName = file;
        fileSize = size;
    }

    public String getFileName() {
        return fileName;
    }

    public String getMessageType() {
        return "Multimedia Message";
    }

    public int getMessageLength() {
        int textLen = super.getMessageLength();
        int totalLen = textLen + fileSize;
        return totalLen;
    }
}
