package com.nsb.practice.designpatterns.decorators;

public class FileAutherDecorator extends FileDecorator {

    public FileAutherDecorator(TemplateFile templateFile) {
        super(templateFile);
    }

    public StringBuilder getContent() {
        return this.templateFile.getContent();
    }

    public void fillContent() {
        //先用上一个装饰器处理，再用自己的逻辑处理
        this.templateFile.fillContent();
        
        StringBuilder content = this.templateFile.getContent();
        content.append("\r\nAuther: kingsley");
    }

}
