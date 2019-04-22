![GroupDocs.Viewer](https://raw.githubusercontent.com/groupdocs-viewer/groupdocs-viewer.github.io/master/resources/image/banner.png "GroupDocs.Viewer")
# GroupDocs.Viewer-for-Java-Spring Example
###### version 1.14.14

[![Build Status](https://travis-ci.org/groupdocs-viewer/GroupDocs.Viewer-for-Java-Spring.svg?branch=master)](https://travis-ci.org/groupdocs-viewer/GroupDocs.Viewer-for-Java-Spring)
[![Maintainability](https://api.codeclimate.com/v1/badges/e8ee774e4c4b2fad413a/maintainability)](https://codeclimate.com/github/groupdocs-viewer/GroupDocs.Viewer-for-Java-Spring/maintainability)
[![GitHub license](https://img.shields.io/github/license/groupdocs-viewer/GroupDocs.Viewer-for-Java-Spring.svg)](https://github.com/groupdocs-viewer/GroupDocs.Viewer-for-Java-Spring/blob/master/LICENSE)

## System Requirements
- Java 8 (JDK 1.8)
- Maven 3


## Document viewer online tool
This tool enables you to **view MS Office files (Word, Excel, PowerPoint)** and over 50 document formats right in your web browser. It is based on powerful and flexible [GroupDocs.Viewer for Java](https://products.groupdocs.com/viewer/java) API that requires no external dependencies. You can adjust settings to **view documents as images or as HTML5**. 

**Note** Without a license application will run in trial mode, purchase [GroupDocs.Viewer for Java license](https://purchase.groupdocs.com/order-online-step-1-of-8.aspx) or request [GroupDocs.Viewer for Java temporary license](https://purchase.groupdocs.com/temporary-license).


## Demo Video
[![Document viewer](https://img.youtube.com/vi/NnZaMNUC6o0/0.jpg)](https://www.youtube.com/watch?v=NnZaMNUC6o0)


## Features
- Responsive design
- Zoom in/out documents without quality loss in HTML mode
- Thumbnails
- Smooth page navigation
- Smooth document scrolling
- Preload pages for faster document rendering
- Multi-language support for displaying errors
- Display two or more pages side by side (when zooming out)
- Cross-browser support (Safari, Chrome, Opera, Firefox)
- Cross-platform support (Windows, Linux, MacOS)
- Clean, modern and intuitive design
- Easily switchable colour theme (create your own colour theme in 5 minutes)
- Mobile support (open application on any mobile device)
- Support over 50 documents and image formats including **DOCX**, **PDF**, **PPT**, **XLS**
- HTML and image modes
- Fully customizable navigation panel
- Open password protected documents
- Text searching & highlighting
- Download documents
- Upload documents
- Print document
- Rotate pages

## How to run

You can run this sample by one of following methods

#### Build from source

Download [source code](https://github.com/groupdocs-viewer/GroupDocs.Viewer-for-Java-Spring/archive/master.zip) from github or clone this repository.

```bash
git clone https://github.com/groupdocs-viewer/GroupDocs.Viewer-for-Java-Spring
cd GroupDocs.Viewer-for-Java-Spring
mvn clean spring-boot:run
## Open http://localhost:8080/viewer/ in your favorite browser.
```

#### Build war from source

Download [source code](https://github.com/groupdocs-viewer/GroupDocs.Viewer-for-Java-Spring/archive/master.zip) from github or clone this repository.

```bash
git clone https://github.com/groupdocs-viewer/GroupDocs.Viewer-for-Java-Spring
cd GroupDocs.Viewer-for-Java-Spring
mvn package -P war
## Deploy this war on any server
```

#### Binary release (with all dependencies)

Download [latest release](https://github.com/groupdocs-viewer/GroupDocs.Viewer-for-Java-Spring/releases/latest) from [releases page](https://github.com/groupdocs-viewer/GroupDocs.Viewer-for-Java-Spring/releases). 

**Note**: This method is **recommended** for running this sample behind firewall.

```bash
curl -J -L -o release.tar.gz https://github.com/groupdocs-viewer/GroupDocs.Viewer-for-Java-Spring/releases/download/1.14.14/release.tar.gz
tar -xvzf release.tar.gz
cd release
java -jar viewer-spring-1.14.14.jar configuration.yaml
## Open http://localhost:8080/viewer/ in your favorite browser.
```

#### Docker image
Use [docker](https://www.docker.com/) image.

```bash
mkdir DocumentSamples
mkdir Licenses
docker run -p 8080:8080 --env application.hostAddress=localhost -v `pwd`/DocumentSamples:/home/groupdocs/app/DocumentSamples -v `pwd`/Licenses:/home/groupdocs/app/Licenses groupdocs/viewer
## Open http://localhost:8080/viewer/ in your favorite browser.
```

### Configuration
For all methods above you can adjust settings in `configuration.yml`. By default in this sample will lookup for license file in `./Licenses` folder, so you can simply put your license file in that folder or specify relative/absolute path by setting `licensePath` value in `configuration.yml`.

#### Viewer configuration options

| Option                 | Type    |   Default value   | Description                                                                                                                                  |
| ---------------------- | ------- |:-----------------:|:-------------------------------------------------------------------------------------------------------------------------------------------- |
| **`filesDirectory`**   | String  | `DocumentSamples` | Files directory path. Indicates where uploaded and predefined files are stored. It can be absolute or relative path                          |
| **`fontsDirectory`**   | String  |                   | Path to custom fonts directory.                                                                                                              |
| **`defaultDocument`**  | String  |                   | Absolute path to default document that will be loaded automaticaly.                                                                          |
| **`preloadPageCount`** | Integer |        `0`        | Indicate how many pages from a document should be loaded, remaining pages will be loaded on page scrolling.Set `0` to load all pages at once |
| **`htmlMode`**         | Boolean |      `true`       | HTML rendering mode. Set `false` to view documents in image mode. Set `true` to view documents in HTML mode                                  | 
| **`zoom`**             | Boolean |      `true`       | Enable or disable Document zoom                                                                                                              |
| **`search`**           | Boolean |      `true`       | Enable or disable document search                                                                                                            |
| **`thumbnails`**       | Boolean |      `true`       | Enable thumbnails preview                                                                                                                    |
| **`rotate`**           | Boolean |      `true`       | Enable individual page rotation functionality                                                                                                |
| **`cache`**            | Boolean |      `true`       | Set true to enable cache                                                                                                                     |
| **`saveRotateState`**  | Boolean |      `true`       | If enabled it will save chages made by rotating individual pages to same file.                                                               |
| **`watermarkText`**    | String  |                   | Text which will be used as a watermark                                                                                                       |

**Note**: you can also set the above options using environment variables. For example to set rendering to image mode set `application.viewer.htmlMode` environment variable to `false`


## License
The MIT License (MIT). Please have a look at the LICENSE.md for more details

## GroupDocs Document Viewer on other platforms

- JAVA DropWizard [Document Viewer](https://github.com/groupdocs-viewer/GroupDocs.Viewer-for-Java-Dropwizard) 
- .NET MVC [Document viewer](https://github.com/groupdocs-viewer/GroupDocs.Viewer-for-.NET-MVC)
- .NET WebForms [Document viewer](https://github.com/groupdocs-viewer/GroupDocs.Viewer-for-.NET-WebForms)


## Resources
- **Website:** [www.groupdocs.com](http://www.groupdocs.com)
- **Product Home:** [GroupDocs.Viewer for Java](https://products.groupdocs.com/viewer/java)
- **Product API References:** [GroupDocs.Viewer for Java API](https://apireference.groupdocs.com/java/viewer)
- **Download:** [Download GroupDocs.Viewer for Java](http://downloads.groupdocs.com/viewer/java)
- **Documentation:** [GroupDocs.Viewer for Java Documentation](https://docs.groupdocs.com/display/viewerjava/Home)
- **Free Support Forum:** [GroupDocs.Viewer for Java Free Support Forum](https://forum.groupdocs.com/c/viewer)
- **Paid Support Helpdesk:** [GroupDocs.Viewer for Java Paid Support Helpdesk](https://helpdesk.groupdocs.com)
- **Blog:** [GroupDocs.Viewer for Java Blog](https://blog.groupdocs.com/category/groupdocs-viewer-product-family/)
