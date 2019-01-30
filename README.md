![GroupDocs.Viewer](https://raw.githubusercontent.com/groupdocs-viewer/groupdocs-viewer.github.io/master/resources/image/banner.png "GroupDocs.Viewer")
# GroupDocs.Viewer-for-Java-Spring Example
###### version 1.14.6

[![Build Status](https://travis-ci.org/groupdocs-viewer/GroupDocs.Viewer-for-Java-Spring.svg?branch=master)](https://travis-ci.org/groupdocs-viewer/GroupDocs.Viewer-for-Java-Spring)
[![Maintainability](https://api.codeclimate.com/v1/badges/e8ee774e4c4b2fad413a/maintainability)](https://codeclimate.com/github/groupdocs-viewer/GroupDocs.Viewer-for-Java-Spring/maintainability)

## System Requirements
- Java 8 (JDK 1.8)
- Maven 3


## Description
GroupDocs.Viewer is a native, simple, fully configurable and optimized web application which allows you to manipulate documents without requiring any other commercial application through GroupDocs APIs.

**Note** Without a license application will run in trial mode, purchase [GroupDocs.Viewer for Java license](https://purchase.groupdocs.com/order-online-step-1-of-8.aspx) or request [GroupDocs.Viewer for Java temporary license](https://purchase.groupdocs.com/temporary-license).


## Demo Video
https://www.youtube.com/watch?v=NnZaMNUC6o0


## Features
- Clean, modern and intuitive design
- Easily switchable colour theme (create your own colour theme in 5 minutes)
- Responsive design
- Mobile support (open application on any mobile device)
- Support over 50 documents and image formats
- HTML and image modes
- Fully customizable navigation panel
- Open password protected documents
- Text searching & highlighting
- Download documents
- Upload documents
- Print document
- Rotate pages
- Zoom in/out documents without quality loss in HTML mode
- Thumbnails
- Smooth page navigation
- Smooth document scrolling
- Preload pages for faster document rendering
- Multi-language support for displaying errors
- Display two or more pages side by side (when zooming out)
- Cross-browser support (Safari, Chrome, Opera, Firefox)
- Cross-platform support (Windows, Linux, MacOS)

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
curl -J -L -o release.tar.gz https://github.com/groupdocs-viewer/GroupDocs.Viewer-for-Java-Spring/releases/download/1.14.6/release.tar.gz
tar -xvzf release.tar.gz
cd release
java -jar viewer-spring-1.14.6.jar configuration.yaml
## Open http://localhost:8080/viewer/ in your favorite browser.
```

#### Docker image
Use [docker](https://www.docker.com/) image.

```bash
mkdir DocumentSamples
mkdir Licenses
docker run -p 8080:8080 -v `pwd`/DocumentSamples:/home/groupdocs/app/DocumentSamples -v `pwd`/Licenses:/home/groupdocs/app/Licenses groupdocs/viewer-for-java-spring
## Open http://localhost:8080/viewer/ in your favorite browser.
```

#### Configuration
For all methods above you can adjust settings in `configuration.yml`. By default in this sample will lookup for license file in `./Licenses` folder, so you can simply put your license file in that folder or specify relative/absolute path by setting `licensePath` value in `configuration.yml`. 



## Resources
- **Website:** [www.groupdocs.com](http://www.groupdocs.com)
- **Product Home:** [GroupDocs.Viewer for Java](https://products.groupdocs.com/viewer/java)
- **Product API References:** [GroupDocs.Viewer for Java API](https://apireference.groupdocs.com/java/viewer)
- **Download:** [Download GroupDocs.Viewer for Java](http://downloads.groupdocs.com/viewer/java)
- **Documentation:** [GroupDocs.Viewer for Java Documentation](https://docs.groupdocs.com/display/viewerjava/Home)
- **Free Support Forum:** [GroupDocs.Viewer for Java Free Support Forum](https://forum.groupdocs.com/c/viewer)
- **Paid Support Helpdesk:** [GroupDocs.Viewer for Java Paid Support Helpdesk](https://helpdesk.groupdocs.com)
- **Blog:** [GroupDocs.Viewer for Java Blog](https://blog.groupdocs.com/category/groupdocs-viewer-product-family/)
