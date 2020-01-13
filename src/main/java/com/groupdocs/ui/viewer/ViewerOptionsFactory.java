package com.groupdocs.ui.viewer;

import com.groupdocs.viewer.converter.options.HtmlOptions;
import com.groupdocs.viewer.converter.options.ImageOptions;
import com.groupdocs.viewer.converter.options.RenderOptions;
import com.groupdocs.viewer.domain.Watermark;
import com.groupdocs.viewer.domain.WatermarkPosition;
import org.springframework.util.StringUtils;

import java.awt.*;

public class ViewerOptionsFactory {

    public static ImageOptions createCommonImageOptions(String password, String watermark) {
        ImageOptions imageOptions = new ImageOptions();
        fillCommonFields(password, watermark, imageOptions);
        return imageOptions;
    }

    private static void fillCommonFields(String password, String watermark, RenderOptions options) {
        if (!StringUtils.isEmpty(password)) {
            options.setPassword(password);
        }
        if (!StringUtils.isEmpty(watermark)) {
            Watermark wm = new Watermark(watermark);
            wm.setColor(new Color(Color.BLUE.getRed(), Color.BLUE.getGreen(), Color.BLUE.getBlue(), 100));
            wm.setPosition(WatermarkPosition.Diagonal);
            wm.setWidth(100);
            options.setWatermark(wm);
        }
        options.getCellsOptions().setShowGridLines(true);
        options.setRenderComments(true);
    }

    public static HtmlOptions createCommonHtmlOptions(String password, String watermark) {
        HtmlOptions htmlOptions = new HtmlOptions();
        htmlOptions.setEmbedResources(true);
        fillCommonFields(password, watermark, htmlOptions);
        return htmlOptions;
    }

    public static ImageOptions createImageOptions(int pageNumber, String password, String watermark) {
        ImageOptions imageOptions = createCommonImageOptions(password, watermark);
        imageOptions.setPageNumber(pageNumber);
        imageOptions.setCountPagesToRender(1);
        return imageOptions;
    }

    public static HtmlOptions createHtmlOptions(int pageNumber, String password, String watermark) {
        HtmlOptions htmlOptions = createCommonHtmlOptions(password, watermark);
        htmlOptions.setPageNumber(pageNumber);
        htmlOptions.setCountPagesToRender(1);
        return htmlOptions;
    }
}
