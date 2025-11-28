package com.gestfood.gestfood.business.service;

import java.io.File;

import org.springframework.stereotype.Component;

import io.github.mojtabaJ.cwebp.WebpConverter;

@Component
public class ImageSevice {
    public File convertToWebp(File inputImage) {
        String inputPath = inputImage.getAbsolutePath();
        String outputPath = inputPath.substring(0, inputPath.lastIndexOf('.')) + ".webp";

        File webpFile = WebpConverter.imageFileToWebpFile(inputPath, outputPath, 80);
        return webpFile;
    }   
}
