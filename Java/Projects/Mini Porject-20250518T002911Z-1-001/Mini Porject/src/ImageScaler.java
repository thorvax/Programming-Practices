import javax.swing.*;
import java.awt.*;

public class ImageScaler {
    public static Image getScaledImage(String imagePath, double scaleX, double scaleY) {
        // Load the original image
        ImageIcon originalIcon = new ImageIcon(imagePath);

        // Ensure the image is loaded correctly
        int originalWidth = originalIcon.getIconWidth();
        int originalHeight = originalIcon.getIconHeight();

        if (originalWidth <= 0 || originalHeight <= 0) {
            System.out.println("Error: Image not found. Check the file path.");
            return null;
        }

        // Scale width and height using the given scale factors
        int newWidth = (int)scaleX;
        int newHeight = (int)scaleY;

        // Return the scaled image
        return originalIcon.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
    }

}