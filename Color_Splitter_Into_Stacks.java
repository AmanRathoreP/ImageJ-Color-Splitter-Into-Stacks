
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
        // Create the dialog
        GenericDialog gd = new GenericDialog("Split Image Into");
        gd.addMessage("Select a color space to split into:");

        String[] colorSpaces = { "HSV (HSB)", "RGB", "CMYK", "CIELAB" };
        gd.addChoice("Color Space:", colorSpaces, colorSpaces[0]); // acts like radio buttons
        gd.addCheckbox("Show original image", true); // extra checkbox
        gd.showDialog();

        // Exit if user cancels the dialog
        if (gd.wasCanceled())
            return;

        // Get selected color space
        String selectedSpace = gd.getNextChoice();
        boolean showOriginal = gd.getNextBoolean(); // checkbox for original image

        // Optionally show the original image
        if (showOriginal) {
            new ImagePlus(imp.getTitle(), imp.getStack()).show();
        }

        // Handle the selected color space
        switch (selectedSpace) {
            case "HSV (HSB)":
                IJ.showStatus("Splitting into HSV...");
                split_HSV(imp);
                break;
            case "RGB":
                IJ.showStatus("Splitting into RGB...");
                split_RGB(imp);
                break;
            case "CMYK":
                IJ.showStatus("Splitting into CMYK...");
                split_CMYK(imp);
                break;
            case "CIELAB":
                IJ.showStatus("Splitting into CIELAB...");
                // TODO: Implement split_CIELAB(imp);
                break;
        }

        IJ.showStatus("Splitting complete.");

    }

    /**
     * Splits the image into HSV channels (Hue, Saturation, Value).
     * 
     * @param imp Input RGB image or stack.
     */
    public void split_HSV(ImagePlus imp) {
        int w = imp.getWidth();
        int h = imp.getHeight();
        ImageStack hsbStack = imp.getStack();
        ImageStack hueStack = new ImageStack(w, h);
        ImageStack satStack = new ImageStack(w, h);
        ImageStack brightStack = new ImageStack(w, h);
        byte[] hue, s, b;
        ColorProcessor cp;
        int n = hsbStack.getSize();
        for (int i = 1; i <= n; i++) {
            IJ.showStatus(i + "/" + n);
            hue = new byte[w * h];
            s = new byte[w * h];
            b = new byte[w * h];
            cp = (ColorProcessor) hsbStack.getProcessor(1);
            cp.getHSB(hue, s, b);
            hsbStack.deleteSlice(1);
            // System.gc();
            hueStack.addSlice(null, hue);
            satStack.addSlice(null, s);
            brightStack.addSlice(null, b);
            IJ.showProgress((double) i / n);
        }
        String title = imp.getTitle();
        imp.hide();
        new ImagePlus(title + " (Hue)", hueStack).show();
        new ImagePlus(title + " (Saturation)", satStack).show();
        new ImagePlus(title + " (Value i.e. Brightness)", brightStack).show();
    }

    /**
     * Splits the image into RGB channels (Red, Green, Blue).
     * 
     * @param imp Input RGB image or stack.
     */
    public void split_RGB(ImagePlus imp) {
        int w = imp.getWidth();
        int h = imp.getHeight();
        ImageStack rgbStack = imp.getStack();
        ImageStack redStack = new ImageStack(w, h);
        ImageStack greenStack = new ImageStack(w, h);
        ImageStack blueStack = new ImageStack(w, h);
        byte[] r, g, b;
        ColorProcessor cp;
        int n = rgbStack.getSize();
        for (int i = 1; i <= n; i++) {
            IJ.showStatus(i + "/" + n);
            r = new byte[w * h];
            g = new byte[w * h];
            b = new byte[w * h];
            cp = (ColorProcessor) rgbStack.getProcessor(1);
            cp.getRGB(r, g, b);
            rgbStack.deleteSlice(1);
            // System.gc();
            redStack.addSlice(null, r);
            greenStack.addSlice(null, g);
            blueStack.addSlice(null, b);
            IJ.showProgress((double) i / n);
        }
        String title = imp.getTitle();
        try {
            imp.hide();
        } catch (Exception e) {
            // TODO: handle exception
        }
        new ImagePlus(title + " (Red)", redStack).show();
        new ImagePlus(title + " (Green)", greenStack).show();
        new ImagePlus(title + " (Blue)", blueStack).show();
    }

    /**
     * Splits the image into CMYK channels (Cyan, Magenta, Yellow, Black).
     * CMYK is approximated from RGB using a standard conversion formula.
     *
     * @param imp Input RGB image or stack.
     */
    public void split_CMYK(ImagePlus imp) {
        int w = imp.getWidth();
        int h = imp.getHeight();
        ImageStack rgbStack = imp.getStack();
        ImageStack cStack = new ImageStack(w, h);
        ImageStack mStack = new ImageStack(w, h);
        ImageStack yStack = new ImageStack(w, h);
        ImageStack kStack = new ImageStack(w, h);

        int n = rgbStack.getSize();
        for (int i = 1; i <= n; i++) {
            IJ.showStatus("Processing slice " + i + "/" + n);
            ColorProcessor cp = (ColorProcessor) rgbStack.getProcessor(1); // always get the first slice
            int[] pixels = (int[]) cp.getPixels();

            byte[] c = new byte[w * h];
            byte[] m = new byte[w * h];
            byte[] y = new byte[w * h];
            byte[] k = new byte[w * h];

            for (int j = 0; j < pixels.length; j++) {
                int r = (pixels[j] >> 16) & 0xff;
                int g = (pixels[j] >> 8) & 0xff;
                int b = pixels[j] & 0xff;

                float rf = r / 255f;
                float gf = g / 255f;
                float bf = b / 255f;

                float kf = 1 - Math.max(rf, Math.max(gf, bf));
                float cf = (1 - rf - kf) / (1 - kf + 1e-6f); // avoid divide by 0
                float mf = (1 - gf - kf) / (1 - kf + 1e-6f);
                float yf = (1 - bf - kf) / (1 - kf + 1e-6f);

                c[j] = (byte) (cf * 255);
                m[j] = (byte) (mf * 255);
                y[j] = (byte) (yf * 255);
                k[j] = (byte) (kf * 255);
            }

            cStack.addSlice(null, c);
            mStack.addSlice(null, m);
            yStack.addSlice(null, y);
            kStack.addSlice(null, k);

            rgbStack.deleteSlice(1); // remove processed slice
            IJ.showProgress((double) i / n);
        }

        String title = imp.getTitle();
        imp.hide();
        new ImagePlus(title + " (Cyan)", cStack).show();
        new ImagePlus(title + " (Magenta)", mStack).show();
        new ImagePlus(title + " (Yellow)", yStack).show();
        new ImagePlus(title + " (Black)", kStack).show();
    }

}