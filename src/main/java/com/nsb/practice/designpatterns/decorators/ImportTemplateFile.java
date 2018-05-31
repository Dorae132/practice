package com.nsb.practice.designpatterns.decorators;

/**
 * 具体构件
 * @author niushuangbing
 *
 */
public class ImportTemplateFile implements TemplateFile {

    StringBuilder content = new StringBuilder();
    
    public ImportTemplateFile() {
        content.append("Title: this is an import template.");
    }
    
    public StringBuilder getContent() {
        return this.content;
    }

    public void fillContent() {
        System.out.println("ImportTemplateFile: i will do nothing.");
    }

}