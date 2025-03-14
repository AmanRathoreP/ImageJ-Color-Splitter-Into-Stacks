
/**
 * =============================================================================
 * Title       : Multi-Color Space Splitter Plugin for ImageJ
 * Description : Splits an RGB image or stack into multiple color spaces (RGB, 
 *               CMYK, CIELAB, HSV) and displays each channel as a grayscale image.
 * 
 * Author      : Aman Rathore
 * Date        : March 14, 2025
 * License     : MIT
 * ImageJ Ver  : Compatible with ImageJ 1.53+ or Fiji
 * 
 * =============================================================================
 */

import ij.*;
import ij.plugin.filter.PlugInFilter;
import ij.process.*;
import ij.gui.*;
import java.awt.*;

public class Color_Splitter_Into_Stacks implements PlugInFilter {
    ImagePlus imp;

    /**
     * Called once when the plugin is initialized. Determines the type of images
     * this plugin supports.
     * 
     * @param arg Optional arguments passed from ImageJ (not used here).
     * @param imp The current image being processed.
     * @return Flags indicating supported image types.
     */
    public int setup(String arg, ImagePlus imp) {
        this.imp = imp;
        return DOES_RGB + NO_UNDO;
    }

    /**
     * Main method executed when the plugin is run. Shows a dialog box with
     * checkboxes
     * for selecting multiple color spaces and calls the respective split methods.
     * 
     * @param ip The image processor of the current image (unused here since we
     *           access full stack).
     */
    public void run(ImageProcessor ip) {
        // Display dialog with color space options
        GenericDialog gd = new GenericDialog("Split Image Into");
        gd.addMessage("Select color spaces to split into:");
        gd.addCheckbox("HSV(HSB)", true);
        gd.addCheckbox("RGB", false);
        gd.addCheckbox("CMYK", false);
        gd.addCheckbox("CIELAB", false);
        gd.showDialog();

        // Exit if user cancels the dialog
        if (gd.wasCanceled())
            return;

        // Process selected color spaces
        if (gd.getNextBoolean()) {
            IJ.showStatus("Splitting into HSV...");
            // TODO: Implement split_HSV(imp);
        }

        if (gd.getNextBoolean()) {
            IJ.showStatus("Splitting into RGB...");
            // TODO: Implement split_RGB(imp);
        }

        if (gd.getNextBoolean()) {
            IJ.showStatus("Splitting into CMYK...");
            // TODO: Implement split_CMYK(imp);
        }

        if (gd.getNextBoolean()) {
            IJ.showStatus("Splitting into CIELAB...");
            // TODO: Implement split_CIELAB(imp);
        }

        IJ.showStatus("Splitting complete.");
    }

    /**
     * Splits the image into HSV channels (Hue, Saturation, Value).
     * 
     * @param imp Input RGB image or stack.
     */
    public void split_HSV(ImagePlus imp) {
        // TODO: Write code to split HSV
    }

}
