<div align="center">
  <h1>ImageJ Color Splitter Into Stacks - v1</h1>
</div>

A powerful and easy-to-use plugin for ImageJ and Fiji that splits an RGB image or image stack into multiple color spaces. It converts each channel into a grayscale image, allowing you to analyze the individual components of various color spaces including RGB, CMYK, CIELAB, and HSV.

## Table of Contents

- [Table of Contents](#table-of-contents)
- [Introduction](#introduction)
- [Features](#features)
- [Requirements](#requirements)
- [Installation](#installation)
  - [Via the ImageJ Official Website](#via-the-imagej-official-website)
  - [Via Custom Download](#via-custom-download)
- [Usage](#usage)
- [How It Works](#how-it-works)
  - [Color Spaces Explained](#color-spaces-explained)
- [Contributing](#contributing)
- [Demo](#demo)
  - [Original](#original)
  - [RGB](#rgb)
  - [HSV (HSB)](#hsv-hsb)
  - [CMYK](#cmyk)
  - [CIELAB](#cielab)
- [Author \& Contact](#author--contact)
- [License](#license)

## Introduction

The **ImageJ Color Splitter Into Stacks** plugin is designed for scientists, researchers, and imaging enthusiasts who need to break down complex RGB images into their constituent color channels. Whether you’re analyzing microscopy images or preparing data for further image processing, this plugin simplifies the process by converting the image into multiple color spaces. 

The plugin provides an interactive dialog to choose the desired color space and even offers the option to display the original image alongside the split channels.

## Features

- **Multi-Color Space Support:** Split images into HSV, RGB, CMYK, and CIELAB color channels.
- **Batch Processing:** Works with both single images and stacks.
- **User-Friendly Interface:** Simple dialog box for choosing color spaces and options.
- **Lightweight & Fast:** Optimized to handle large images/stacks with real-time progress updates.
- **Open Source:** Free to use, modify, and distribute under the MIT License.

## Requirements

- **ImageJ or Fiji:** Version 1.53+ (Fiji is recommended for an enhanced feature set).
- **Java:** Compatible Java runtime environment.
- **Operating System:** Cross-platform compatibility (Windows, macOS, Linux).

## Installation

### Via the ImageJ Official Website

1. **Download:** Navigate to the ImageJ website and download the latest version of the plugin file (`Color_Splitter_Into_Stacks.class`).
2. **Install:** Open ImageJ, then go to **Plugins > Install**.
3. **Select Plugin:** Browse to and select the downloaded `Color_Splitter_Into_Stacks.class` file.
4. **Restart (if necessary):** Some installations might require a restart of ImageJ.

### Via Custom Download

1. **Download:** Visit the [release section](https://github.com/AmanRathoreP/ImageJ-Color-Splitter-Into-Stacks/releases) of the project repository and download the latest `Color_Splitter_Into_Stacks.class` file.
2. **Install:** Open ImageJ and navigate to **Plugins > Install**.
3. **Select Plugin:** Choose the downloaded class file.
4. **Restart (if necessary):** Restart ImageJ if the plugin does not load immediately.

## Usage

1. **Launch ImageJ/Fiji:** Open the application.
2. **Load an Image or Stack:** Open your desired RGB image or image stack.
3. **Run the Plugin:** Navigate to **Plugins** and select **Color Splitter Into Stacks**.
4. **Select Options:**
   - **Color Space Choice:** Choose from “HSV (HSB)”, “RGB”, “CMYK”, or “CIELAB” via the provided dropdown.
   - **Display Option:** Optionally check the box if you don't want original image to disappear after stacks creation.
5. **Process:** Click OK and watch as the plugin processes the image, showing progress updates. The plugin will then display each channel as a separate grayscale image.

## How It Works

The plugin processes images by converting the input RGB data into the selected color space. Each conversion method (HSV, RGB, CMYK, CIELAB) is implemented to handle both single images and stacks efficiently. 

### Color Spaces Explained

- **HSV (HSB):**  
  Separates the image into Hue (color type), Saturation (vividness), and Value/Brightness.
  
- **RGB:**  
  Splits the image into its fundamental Red, Green, and Blue components, useful for understanding the primary color makeup.

- **CMYK:**  
  Converts the RGB data into Cyan, Magenta, Yellow, and Black channels using an approximated conversion formula—ideal for print-related workflows.

- **CIELAB:**  
  Transforms the image to the CIELAB color space, where:
  - **L*** represents lightness,
  - **a*** represents the green-red component,
  - **b*** represents the blue-yellow component.
  
  This conversion involves transforming sRGB to linear RGB, then to the XYZ color space, and finally to CIELAB. The output is scaled to a 0–255 range for compatibility with grayscale imaging.

## Contributing

Contributions are very welcome! Whether you are reporting a bug, suggesting new features, or improving the code, every contribution helps. To contribute:

- **File Bug Reports:** Provide detailed descriptions, including sample images or video recordings if possible.
- **Suggest Enhancements:** Share your ideas for new features or modifications.
- **Code Contributions:** Check the [open issues](issues.md) for tasks you can help with. Even if you’re not a coder, your comments and insights are valuable.

## Demo

Below are examples demonstrating how this plugin splits an image of a leopard into various color spaces. Each channel is shown as a grayscale image for clarity.

### Original
![Original](https://raw.githubusercontent.com/AmanRathoreP/AmanRathoreP/refs/heads/main/Files/Color%20Splitter%20Into%20Stacks%20examples/original.jpg)

---

### RGB
![RGB Split](https://raw.githubusercontent.com/AmanRathoreP/AmanRathoreP/refs/heads/main/Files/Color%20Splitter%20Into%20Stacks%20examples/RGB.jpg)
*Red, Green, and Blue channels shown side by side. Each channel highlights the corresponding grayscale contribution in the original image.*

---

### HSV (HSB)
![HSV Split](https://raw.githubusercontent.com/AmanRathoreP/AmanRathoreP/refs/heads/main/Files/Color%20Splitter%20Into%20Stacks%20examples/HSV(HSB).jpg)
*Hue, Saturation, and Value channels displayed in grayscale. Notice how the Value channel closely resembles a brightness map, while the Hue and Saturation channels capture different aspects of color information.*

---

### CMYK
![CMYK Split](https://raw.githubusercontent.com/AmanRathoreP/AmanRathoreP/refs/heads/main/Files/Color%20Splitter%20Into%20Stacks%20examples/CMYK.jpg)  
*Cyan, Magenta, Yellow, and Black channels. This is a common color model for printing, approximated here from the original RGB data.*

---

### CIELAB
![CIELAB Split](https://raw.githubusercontent.com/AmanRathoreP/AmanRathoreP/refs/heads/main/Files/Color%20Splitter%20Into%20Stacks%20examples/CEILAB.jpg)
*L*, a*, and b* channels. L* represents the lightness component, while a* and b* capture color-opponent dimensions (red–green and blue–yellow).*

---

Each of these splits is generated by running the **Color Splitter Into Stacks** plugin in ImageJ/Fiji and selecting the desired color space in the dialog box. The plugin automatically creates separate stacks for each channel.

## Author & Contact

- **Aman Rathore**
  - GitHub: [@AmanRathoreP](https://www.github.com/AmanRathoreP) | [@AmanRathoreM](https://www.github.com/AmanRathoreM)
  - Telegram: [@aman0864](https://t.me/aman0864)
  - Email: [aman.proj.rel@gmail.com](mailto:aman.proj.rel@gmail.com)
  - LinkedIn: [@amanrathorep](https://www.linkedin.com/in/amanrathorep/)
  - Instagram: [@aman__0864](https://www.instagram.com/aman__0864/)

Feel free to reach out if you have any questions, suggestions, or issues regarding the plugin.

## License

[MIT License](https://choosealicense.com/licenses/mit/)

Copyright (c) 2025, Aman Rathore

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.