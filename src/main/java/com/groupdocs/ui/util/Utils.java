package com.groupdocs.ui.util;

import org.apache.commons.io.FilenameUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class Utils {

    public static final FileNameComparator FILE_NAME_COMPARATOR = new FileNameComparator();
    public static final FileTypeComparator FILE_TYPE_COMPARATOR = new FileTypeComparator();

    /**
     * Fill header HTTP response with file data
     */
    public static void addFileDownloadHeaders(HttpServletResponse response, String fileName, Long fileLength) {
        HttpHeaders fileDownloadHeaders = createFileDownloadHeaders(fileName, fileLength, MediaType.APPLICATION_OCTET_STREAM);
        for (Map.Entry<String, List<String>> entry : fileDownloadHeaders.entrySet()) {
            for (String value : entry.getValue()) {
                response.addHeader(entry.getKey(), value);
            }
        }
    }

    /**
     * Get headers for downloading files
     */
    private static HttpHeaders createFileDownloadHeaders(String fileName, Long fileLength, MediaType mediaType) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentDispositionFormData("attachment", fileName);
        httpHeaders.setContentType(mediaType);
        httpHeaders.set("Content-Description", "File Transfer");
        httpHeaders.set("Content-Transfer-Encoding", "binary");
        httpHeaders.setExpires(0);
        httpHeaders.setCacheControl("must-revalidate");
        httpHeaders.setPragma("public");
        if (fileLength != null) {
            httpHeaders.setContentLength(fileLength);
        }
        return httpHeaders;
    }


    /**
     * Rename file if exist
     * @param directory directory where files are located
     * @param fileName file name
     * @return new file with new file name
     */
    public static File getFreeFileName(String directory, String fileName){
        File file = null;
        try {
            File folder = new File(directory);
            File[] listOfFiles = folder.listFiles();
            for (int i = 0; i < listOfFiles.length; i++) {
                int number = i + 1;
                String newFileName = FilenameUtils.removeExtension(fileName) + "-Copy(" + number + ")." + FilenameUtils.getExtension(fileName);
                file = new File(directory + "/" + newFileName);
                if(file.exists()) {
                    continue;
                } else {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }


    /**
     * FileNameComparator
     * Compare and sort file names alphabetically
     *
     * @author Aspose Pty Ltd
     */
    static class FileNameComparator implements Comparator<File> {

        /**
         * Compare two file names
         *
         * @param file1
         * @param file2
         * @return int
         */
        @Override
        public int compare(File file1, File file2) {

            return String.CASE_INSENSITIVE_ORDER.compare(file1.getName(),
                    file2.getName());
        }
    }

    /**
     * FileTypeComparator
     * Compare and sort file types - folders first
     *
     * @author Aspose Pty Ltd
     */
    static class FileTypeComparator implements Comparator<File> {

        /**
         * Compare two file types
         *
         * @param file1
         * @param file2
         * @return
         */
        @Override
        public int compare(File file1, File file2) {

            if (file1.isDirectory() && file2.isFile()) {
                return -1;
            }
            if (file1.isDirectory() && file2.isDirectory()) {
                return 0;
            }
            if (file1.isFile() && file2.isFile()) {
                return 0;
            }
            return 1;
        }
    }
}
