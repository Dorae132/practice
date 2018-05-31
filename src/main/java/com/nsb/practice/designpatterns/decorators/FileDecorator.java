package com.nsb.practice.designpatterns.decorators;

public abstract class FileDecorator implements TemplateFile {

    TemplateFile templateFile;

    public FileDecorator(TemplateFile templateFile) {
        this.templateFile = templateFile;
    }
}
