package com.groupdocs.ui.viewer;

import com.groupdocs.viewer.converter.options.HtmlOptions;
import com.groupdocs.viewer.converter.options.ImageOptions;
import org.springframework.util.StringUtils;

public class ViewerOptionsFactory {

    public static ImageOptions createCommonImageOptions(String password) {
        ImageOptions imageOptions = new ImageOptions();
        // set password for protected document
        if (!StringUtils.isEmpty(password)) {
            imageOptions.setPassword(password);
        }
        return imageOptions;
    }

    public static HtmlOptions createCommonHtmlOptions(String password) {
        HtmlOptions htmlOptions = new HtmlOptions();
        htmlOptions.setResourcesEmbedded(true);
        // set password for protected document
        if (!StringUtils.isEmpty(password)) {
            htmlOptions.setPassword(password);
        }
        return htmlOptions;
    }

    public static ImageOptions createImageOptions(int pageNumber, String password) {
        ImageOptions imageOptions = createCommonImageOptions(password);
        imageOptions.setPageNumber(pageNumber);
        imageOptions.setCountPagesToRender(1);
        return imageOptions;
    }

    public static HtmlOptions createHtmlOptions(int pageNumber, String password) {
        HtmlOptions htmlOptions = createCommonHtmlOptions(password);
        htmlOptions.setPageNumber(pageNumber);
        htmlOptions.setCountPagesToRender(1);
        return htmlOptions;
    }
}
